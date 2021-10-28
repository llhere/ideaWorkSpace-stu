package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class MyClient {

    public static void main(String[] args) throws IOException {

        //创建客户端 指定端口
        DatagramSocket client = new DatagramSocket(6666);

        //准备发送数据内容
        String msg = "Hello World";
        byte[] data = msg.getBytes();

        //打包
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));

        //发送
        client.send(packet);
        client.close();
    }
}
