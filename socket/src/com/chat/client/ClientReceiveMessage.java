package com.chat.client;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiveMessage implements Runnable{

    private DataInputStream dis;
    private boolean isRunning = true;

    public ClientReceiveMessage(Socket socket){

        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            CloseUtil.closeAll(isRunning,dis);
        }
    }

    //控制台输出发送的消息
    String writeReceiveMessage(){
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            CloseUtil.closeAll(isRunning,dis);
        }
        return msg;
    }
    
    @Override
    public void run() {

        while(isRunning){
            System.out.println(writeReceiveMessage());
        }
    }
}
