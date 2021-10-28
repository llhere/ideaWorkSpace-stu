package com.chat.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSendMessage implements Runnable{

    private BufferedReader console;
    private DataOutputStream dos;
    private boolean isRunning = true;
    private String userName;

    ClientSendMessage(){
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    //带参构造
    public ClientSendMessage(Socket socket,String userName){

        this();
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            this.userName = userName;
            sendMessage(this.userName);
        } catch (IOException e) {
            CloseUtil.closeAll(isRunning,console,dos);
        }
    }


    //写出控制台输入的信息
    void sendMessage(String msg){

        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            CloseUtil.closeAll(isRunning,console,dos);
        }
    }

    //线程
    @Override
    public void run() {

        while(isRunning){
            try {
                sendMessage(console.readLine());
            } catch (IOException e) {
                CloseUtil.closeAll(isRunning,console,dos);
            }
        }
    }
}
