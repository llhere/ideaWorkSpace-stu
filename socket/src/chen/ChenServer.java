package chen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChenServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            Socket accept = serverSocket.accept();

            System.out.println("检测到一个新的连接" + accept.toString());

            //接收数据
            DataInputStream dis = new DataInputStream(accept.getInputStream());
            String content = dis.readUTF();

            //根据就收内容返回响应内容
            DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
            if("1".equals(content))
                dos.writeUTF("一");
            else if("2".equals(content))
                dos.writeUTF("二");
            else
                dos.writeUTF("请输入1或2");
            dos.flush();
        }


    }

}
