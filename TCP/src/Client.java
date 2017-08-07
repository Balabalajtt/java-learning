import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 江婷婷 on 2017/8/7.
 */
public class Client {
    public static void main (String[] args) throws Exception {

        //创建服务端对象,并指明连接的主机和端口
        Socket socket = new Socket("localhost", 8889);

        //接受来自服务端的数据
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();

        socket.close();
    }
}
