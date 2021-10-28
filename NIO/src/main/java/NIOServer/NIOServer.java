package NIOServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();
            if (null != socketChannel){
                socketChannel.configureBlocking(false);
                ByteBuffer request = ByteBuffer.allocate(1024);
                while (socketChannel.isOpen() && socketChannel.read(request) != -1){
                    if (request.position() > 0) break;
                }
                request.flip();
                byte[] content = new byte[request.limit()];
                request.get(content);
                System.out.println(new String(content));

                String response = "HTTP/1.1  200 OK\r\nContent-length: 11\r\n\r\nHello world";
                ByteBuffer wrap = ByteBuffer.wrap(response.getBytes());
                while (wrap.hasRemaining()){
                    socketChannel.write(wrap);
                }


            }

        }

    }

}
