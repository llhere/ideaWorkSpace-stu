package me;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ChenServer{

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3,5,1000, TimeUnit.MICROSECONDS,new LinkedBlockingQueue<>(5));


    public static void main(String[] args) throws IOException {

        //创建Selector
        Selector selector = Selector.open();

        //创建ServerSocketChannel并设置为非阻塞模式
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 8080));

        //注册到Selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //开始处理事件
        for (;;){

            //阻塞,有新连接会解除阻塞
            selector.select();

            //遍历所有选择键
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            //处理任务
            while (selectionKeyIterator.hasNext()){

                //获取Channel的时间
                SelectionKey selectionKey = selectionKeyIterator.next();

                //如果是新来的链接
                if (selectionKey.isAcceptable()){

                    System.out.println("有新的连接，当前线程数量:" + threadPool.getActiveCount());
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                //如果是读取操作
                else if (selectionKey.isReadable()){

                    //开始读取
                    SocketChannel SocketChannel = (SocketChannel) selectionKey.channel();

                    //处理时把channel取消注册，防止其他线程再次处理
                    selectionKey.cancel();

                    //交给线程池完成计算任务
                    threadPool.execute(() -> {
                        //申请大小为1024字节缓冲区,默认写操作
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        try {
                            //把数据写入byteBuffer
                            SocketChannel.read(byteBuffer);

                            //转换为读操作
                            byteBuffer.flip();

                            //获取接收的数据
                            String request = new String(byteBuffer.array());
                            System.out.println("收到新数据，当前线程数："  + threadPool.getActiveCount() + "，请求内容：" + request);

                            // 响应结果 200
                            String response = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello World";
                            ByteBuffer wrap = ByteBuffer.wrap(response.getBytes());
                            //写出数据
                            SocketChannel.write(wrap);
                            System.out.println(Thread.currentThread().getName() + " 服务器线程处理完毕，当前线程数：" + threadPool.getActiveCount());
                        } catch (IOException e) {
                            selectionKey.cancel(); // 取消事件订阅
                            e.printStackTrace();
                        }

                    });
                }
                selectionKeyIterator.remove();
            }
            selectionKeys.clear();
            selector.selectNow();
        }


    }


}