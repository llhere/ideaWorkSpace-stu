package com.chat;

import com.chat.server.MyChannel;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {

        //创建服务端
        ServerSocket server = new ServerSocket(8888);

        //获取一个socket通道
        while(true){
            new Thread(new MyChannel(server.accept())).start();
        }

    }

}
