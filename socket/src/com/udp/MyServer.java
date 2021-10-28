package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * udp非面向链接
 */
public class MyServer {

    public static void main(String[] args) throws IOException {

        //创建服务端
        DatagramSocket server = new DatagramSocket(8888);

        //创建接收的容器
        byte[] continer = new byte[1024];

        //打包
        DatagramPacket packet = new DatagramPacket(continer, continer.length);

        //获取接收数据
        server.receive(packet);

        //获取数据  处理路数据
        byte[] data = packet.getData();
        System.out.println(new String(data,0,data.length));

    }
}
