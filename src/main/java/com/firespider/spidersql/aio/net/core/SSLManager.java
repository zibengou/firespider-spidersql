package com.firespider.spidersql.aio.net.core;


import org.voovan.tools.ByteBufferChannel;
import org.voovan.tools.TEnv;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;

/**
 * SSL管理器
 *
 * @author helyho
 *         <p>
 *         Voovan Framework.
 *         WebSite: https://github.com/helyho/Voovan
 *         Licence: Apache v2 License
 */
public class SSLManager {
    private KeyManagerFactory keyManagerFactory;
    private TrustManagerFactory trustManagerFactory;
    private SSLContext context;
    private SSLEngine engine;
    private boolean needClientAuth;

    boolean handShakeDone = false;

    private ByteBuffer appBuffer, netBuffer,readBuffer;

    /**
     * 构造函数
     * 默认使用客户端认证
     *
     * @param protocol 协议类型
     * @throws NoSuchAlgorithmException 无可用协议异常
     */
    public SSLManager(String protocol, String host, int port) throws SSLException {
        this(protocol, host, port, false);
    }

    /**
     * 构造函数
     *
     * @param protocol      协议类型
     * @param useClientAuth 是否使用客户端认证, true:双向认证, false: 单向认证
     * @throws SSLException SSL 异常
     */
    public SSLManager(String protocol, String host, int port, boolean useClientAuth) throws SSLException {
        this.needClientAuth = useClientAuth;
        createSSLEngine(protocol, host, port);
        initBuf();
    }

    /**
     * 获取 SSLEngine
     *
     * @return SSLEngine 对象
     */
    public SSLEngine getSSLEngine() {
        return engine;
    }

    /**
     * 读取管理证书
     *
     * @param manageCertFile 证书地址
     * @param certPassword   证书密码
     * @param keyPassword    密钥
     * @throws SSLException SSL 异常
     */
    public void loadCertificate(String manageCertFile, String certPassword, String keyPassword) throws SSLException {

        FileInputStream certFIS = null;
        try {
            keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            trustManagerFactory = TrustManagerFactory.getInstance("SunX509");

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            certFIS = new FileInputStream(manageCertFile);
            keystore.load(certFIS, certPassword.toCharArray());

            keyManagerFactory.init(keystore, keyPassword.toCharArray());
            trustManagerFactory.init(keystore);
        } catch (CertificateException | IOException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
            throw new SSLException("Init SSLContext Error: " + e.getMessage(), e);
        } finally {
            if (certFIS != null) {
                try {
                    certFIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化
     *
     * @param protocol 协议名称 SSL/TLS
     * @throws SSLException SSL 异常
     */
    private synchronized void init(String protocol) throws SSLException {
        try {
            context = SSLContext.getInstance(protocol);
            if (keyManagerFactory != null && trustManagerFactory != null) {
                context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            } else {
                context.init(null, null, null);
            }
            //NoSuchAlgorithmException | KeyManagementException |
        } catch (Exception e) {

            throw new SSLException("Init SSLContext Error: " + e.getMessage(), e);
        }

    }

    private void initBuf() {
        SSLSession sslSession = engine.getSession();
        int netBufferMax = sslSession.getPacketBufferSize();
        int appBufferMax = sslSession.getApplicationBufferSize();
        this.netBuffer = ByteBuffer.allocate(netBufferMax);
        this.appBuffer = ByteBuffer.allocate(appBufferMax);
        this.readBuffer = ByteBuffer.allocate(netBufferMax);
    }

    /**
     * 构造SSLEngine
     *
     * @throws SSLException SSL 异常
     */
    private synchronized void createSSLEngine(String protocol, String ipAddress, int port) throws SSLException {
        init(protocol);
        engine = context.createSSLEngine(ipAddress, port);
        engine.setUseClientMode(true);
        engine.setNeedClientAuth(needClientAuth);
    }

    public void wrap(ByteBuffer in, ByteBuffer out) throws SSLException, ExecutionException, InterruptedException {
        out.clear();
        in.flip();
        engine.wrap(in, out);
        out.flip();
    }

    /***
     * 解析服务器数据
     * ! MAC OS JDK1.8 SSLEngine unwrap 方法有异常，待解决
     * @param in
     * @param out
     * @throws SSLException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void unwrap(ByteBuffer in, ByteBuffer out) throws SSLException, ExecutionException, InterruptedException {
        out.clear();
        while(in.hasRemaining()) {
            in.flip();
            engine.unwrap(in, out);
        }
    }

    public boolean doHandShake(Session session) throws IOException, ExecutionException, InterruptedException {

        engine.beginHandshake();
        int handShakeCount = 0;
        SSLEngineResult.HandshakeStatus handshakeStatus = engine.getHandshakeStatus();
        while (!handShakeDone && handShakeCount < 20) {
            handShakeCount++;
            switch (handshakeStatus) {
                case NEED_TASK:
                    handshakeStatus = runDelegatedTasks();
                    break;
                case NEED_WRAP:
                    handshakeStatus = doHandShakeWarp(session);
                    break;
                case NEED_UNWRAP:
                    handshakeStatus = doHandShakeUnwarp(session);
                    break;
                case FINISHED:
                    handshakeStatus = engine.getHandshakeStatus();
                    break;
                case NOT_HANDSHAKING:
                    handShakeDone = true;
                    break;
                default:
                    break;
            }
//			TEnv.sleep(1);
        }
        return handShakeDone;
    }

    /**
     * 处理握手 Unwarp;
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    private SSLEngineResult.HandshakeStatus doHandShakeUnwarp(Session session) throws IOException, ExecutionException, InterruptedException {
        int length;
        do {
            readBuffer.clear();
            length = session.getSocketChannel().read(readBuffer).get();
            unwrap(readBuffer, appBuffer);
            session.getReadFromChannelMessage().put(appBuffer.array(), 0, appBuffer.position());
        } while (length >= readBuffer.capacity());
        //如果有 HandShake Task 则执行
        return runDelegatedTasks();
    }

    /**
     * 处理握手 Warp;
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    private SSLEngineResult.HandshakeStatus doHandShakeWarp(Session session) throws IOException, ExecutionException, InterruptedException {
        wrap(session.getWriteToChannelMessage().getBuffer(), netBuffer);
        session.getSocketChannel().write(netBuffer).get();
        //如果有 HandShake Task 则执行
        return runDelegatedTasks();
    }

    /**
     * 执行委派任务
     *
     * @throws Exception
     */
    private SSLEngineResult.HandshakeStatus runDelegatedTasks() {
        if (engine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_TASK) {
            Runnable runnable;
            while ((runnable = engine.getDelegatedTask()) != null) {
                runnable.run();
            }
        }
        return engine.getHandshakeStatus();
    }


    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }
}

