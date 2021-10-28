package NIOServer;


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

public class NIOSelector {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,2, TimeUnit.SECONDS,new LinkedBlockingQueue<>(2));

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while (true){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.configureBlocking(false);
                    selectionKey.cancel();
                    threadPoolExecutor.execute(() -> {
                        try {
                            ByteBuffer request = ByteBuffer.allocate(1024);
                            socketChannel.read(request);
                            request.flip();
                            byte[] content = new byte[request.position()];
                            request.get(content);
                            System.out.println(new String(content));

                            String response = "HTTP/1.1 200 OK\r\nContent-length: 12\r\n\r\nHello world!";
                            socketChannel.write(ByteBuffer.wrap(response.getBytes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                }
                iterator.remove();
            }
            selectionKeys.clear();
            selector.selectNow();
        }


    }

}
