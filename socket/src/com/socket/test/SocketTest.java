package com.socket.test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class SocketTest {


    public static void main(String[] args) throws UnknownHostException {

        //使用getLocalHost方法创建InetAddress对象
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());    //返回本机IP地址
        System.out.println(addr.getAddress());        //返回计算机名（域名）

        //根据域名得到InetAddress对象
        /*addr = InetAddress.getByName("www.163.com");
        System.out.println(addr.getLocalHost());
        System.out.println(addr.getHostName());*/

        InetSocketAddress inetAddr = new InetSocketAddress("localhost", 8080);
        System.out.println(inetAddr.getHostName());
        System.out.println(inetAddr.getPort());

        addr = inetAddr.getAddress();
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

    }

}
