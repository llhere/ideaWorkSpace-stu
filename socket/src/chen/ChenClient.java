package chen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChenClient {

    public static void main(String[] args) throws IOException {

        //获取控制台输入的内容
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();

        //与服务端创建连接
        Socket socket = new Socket("127.0.0.1", 8888);

        //把内容发送给服务端
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(content);

        //获取客户端返回的结果
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        System.out.println(dis.readUTF());


    }

}
