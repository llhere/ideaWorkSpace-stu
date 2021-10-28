package com.chat;

import com.chat.client.ClientReceiveMessage;
import com.chat.client.ClientSendMessage;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        //创建客户端
        Socket socket = new Socket("localhost", 8888);

        //控制台输入用户名  判断是否为空
        System.out.println("请输入您的用户名");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String userName = console.readLine();
        if(null == userName || userName.equals("")) return;

        //输入信息发送控制台输入的信息
        new Thread(new ClientSendMessage(socket,userName)).start();

       //控制台打印服务器返回来的信息
        new Thread(new ClientReceiveMessage(socket)).start();

    }
}
