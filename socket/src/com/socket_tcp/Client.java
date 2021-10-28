package com.socket_tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        //创建Socket
        Socket socket = new Socket("localhost",8888);

        //接收数据
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        String each = dis.readUTF();

        System.out.println(each);

    }
}
