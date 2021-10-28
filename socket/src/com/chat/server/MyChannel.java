package com.chat.server;

import com.chat.client.CloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//聊天通道
public class MyChannel implements Runnable{

    //存放MyChannel
    private static Map<MyChannel,String> allChannelMap = new HashMap<MyChannel,String>();
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isRunning = true;

    //初始化流
    public MyChannel(Socket client){

        try {
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());

            //进入聊天室
            String userName = dis.readUTF();
            send("Welcome to chatting");
            sendAllClient(userName + "进入聊天室",true);

            //读入时把自己加入到集合中
            allChannelMap.put(this,userName);

        } catch (IOException e) {
            CloseUtil.closeAll(isRunning,dis); allChannelMap.remove(this);
        }
    }

    //读取数据
    String receive (){

        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(isRunning,dis); allChannelMap.remove(this);
        }
        return msg;
    }

    //发送数据
    void send(String msg){

        try {
            dos.writeUTF("服务器 -- > " + msg);
            dos.flush();
        } catch (IOException e) {
            CloseUtil.closeAll(isRunning,dis); allChannelMap.remove(this);
        }
    }

    //发送所有的client
    void sendAllClient(String msg,boolean firstChat){


        //获取迭代器
        Iterator<Map.Entry<MyChannel, String>> iterator = allChannelMap.entrySet().iterator();

        //私聊
        if(msg.startsWith("@") && msg.contains(":")){

            //获取使用名和发送的消息
            String userName = msg.substring(1,msg.indexOf(":"));
            msg = msg.substring(msg.indexOf(":"));

            while(iterator.hasNext()){

                //获取socket的channel
                Map.Entry<MyChannel, String> next = iterator.next();

                //发送给@的人
                if(next.getValue().equals(userName)) next.getKey().send(userName + "悄悄的对你说：" + msg);
                return;
            }

            return;
        }

        //群发
        //获取是谁在说话
        String userName = allChannelMap.get(this);

        //发送信息
        while(iterator.hasNext()){

            //获取socket的channel
            MyChannel channel = iterator.next().getKey();

            //消息不发送给本人
            if(this == channel) continue;

            //给其他人发送
            if(firstChat)
                channel.send(msg);
            else
                channel.send(userName + "发送了消息：" + msg);
        }

    }

    @Override
    public void run() {
        while(isRunning){
            sendAllClient(receive(),false);
        }
    }
}