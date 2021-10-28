package com.url;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://www.baidu.com:80/index.html#first?username=chen");

        System.out.println("协议:" + url.getProtocol());
        System.out.println("域名:" + url.getHost());
        System.out.println("端口:" + url.getPort());
        System.out.println("资源:" + url.getFile());
        System.out.println("相对路径 :" + url.getPath());
        System.out.println("锚点:" + url.getRef());  //'#'后为锚点，一般是固定值，有锚点参数为null，只有参数锚点为null
        System.out.println("参数:" + url.getQuery());


    }

}
