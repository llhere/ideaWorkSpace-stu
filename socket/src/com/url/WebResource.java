package com.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 网路资源 ：源代码（网络爬虫原理）
 */
public class WebResource {

    public static void main(String[] args) throws IOException {

        //获取url地址
        URL url = new URL("http://localhost:8080/NXDZBD/services/AgriculturePolicyWebService?wsdl");

        //获取转换流
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String msg = "";
        while((msg = br.readLine()) != null){
            System.out.println(msg);
        }

        br.close();

    }

}
