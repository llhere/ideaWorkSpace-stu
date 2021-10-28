package com.study.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TEST1259606144 {

    public static void main(String[] args) throws Exception {
        //获取连接
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
        // 没连接上,则一直等待
        while (!socketChannel.finishConnect()) {
            Thread.yield();
        }
        // 发送内容
        String msg = "123";
        String response = "GET / HTTP/1.1\r\n\r\n" + msg.length();
        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        // 读取响应数据
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
            // 超过0字节认为请求已结束
            if (requestBuffer.position() > 0) break;
        }
        //开始读取数据
        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        String[] requestMsgArray = new String(content).split("\r\n");
        for (String server : requestMsgArray) {
            if (server.contains("Server")){
                System.out.println("QQ：1259606144  解析到百度服务器Server类型是：" + server.substring("Server:".length()));
                break;
            }
        }
        //关闭连接
        socketChannel.close();
    }

}
