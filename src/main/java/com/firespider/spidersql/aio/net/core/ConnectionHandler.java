package com.firespider.spidersql.aio.net.core;

import java.nio.channels.CompletionHandler;

/**
 * Created by stone on 2017/9/17.
 */
public class ConnectionHandler implements CompletionHandler<Void, Session> {

    @Override
    public void completed(Void result, Session session) {
//        if(session.isSSL()){
//            session.doSSLHandShake();
//        }else {
//            session.writeToChannel();
//        }
        session.writeToChannel();
    }

    @Override
    public void failed(Throwable exc, Session session) {
        session.handleFail();
    }
}
