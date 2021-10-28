package com.socket_tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp面向链接
 */
public class Server {

    public static void main(String[] args) throws IOException {

        //创建客户端
        ServerSocket server = new ServerSocket(8888);

        //接受客户链接   阻塞式
        Socket socket = server.accept();

        System.out.println("一个客户端创建链接");

        //发送数据
        String msg = "欢迎使用";
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(msg);
        dos.flush();
    }
}
