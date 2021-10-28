package tony;

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

public class TonyNioHttpServer {


    // 定义线程池
    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(25, 25, 25,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));

    public static void main(String[] args) throws Exception {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(8080));
        System.out.println("NIO启动:" + 8080);
        Selector selector = Selector.open();
        SelectionKey selectionKey = socketChannel.register(selector, 0); selectionKey.interestOps(selectionKey.OP_ACCEPT);
        while (true) {
            selector.select(1000);
            Set<SelectionKey> selected = selector.selectedKeys();            // 开始处理
            Iterator<SelectionKey> iter = selected.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    System.out.println("有新的连接啦，当前线程数量:" + threadPoolExecutor.getActiveCount());
                    SocketChannel chan = socketChannel.accept();
                    chan.configureBlocking(false);
                    chan.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannels = (SocketChannel) key.channel();
                    key.cancel();
                    socketChannel.configureBlocking(false);
                    threadPoolExecutor.execute(() -> {
                        try {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            socketChannels.read(byteBuffer);
                            byteBuffer.flip(); // 转为读模式
                            String request = new String(byteBuffer.array());
                            System.out.println("收到新数据，当前线程数："  + threadPoolExecutor.getActiveCount() + "，请求内容：" + request);
                            // 给一个当前时间作为返回值,随意返回，不是Http的协议
                            byteBuffer.clear();
                            ByteBuffer wrap = ByteBuffer.wrap(("tony" + System.currentTimeMillis()).getBytes());
                            socketChannels.write(wrap);
                            wrap.clear();
                            socketChannel.configureBlocking(false);
                            // 注册一个新监听。 表示希望收到该连接上OP_READ事件的通知
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } catch (Exception e) {}
                        System.out.println(Thread.currentThread().getName() + " 服务器线程处理完毕，当前线程数：" + threadPoolExecutor.getActiveCount());
                    });
                }
                iter.remove();
            }
            selected.clear();
            selector.selectNow();
        }
    }
}
