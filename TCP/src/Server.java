import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 江婷婷 on 2017/8/7.
 */
public class Server {
    public static void main (String[] args) throws Exception {

        //创建服务端对象并指定端口
        ServerSocket serverSocket = new ServerSocket(8889);
        System.out.println("服务端准备好了...");

        boolean accept = true;
        while (accept) {
            //接受连接该服务端的客户端对象
            Socket client = serverSocket.accept();
            System.out.println(client.getInetAddress());

            //给客户端传递数据
            String data = "你好，我是皮皮猪";
            PrintStream outputStream = new PrintStream(client.getOutputStream());
            outputStream.println(data);
            outputStream.close();
        }

        serverSocket.close();
    }

}
