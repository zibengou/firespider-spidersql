package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.Gen;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xiaotong.shi on 2017/9/14.
 */
public abstract class Action {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            HttpClient client = HttpClient.New(url);
            client.getOutputStream();
//            client.getOutputStream();
//             in =  new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line, res = "";
//            while ((line = in.readLine()) != null) {
//                res += line;
//            }
//            System.out.println(res);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    Gen execute(Gen param) {

        return null;
    }
}
