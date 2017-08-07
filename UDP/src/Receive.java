import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by 江婷婷 on 2017/8/7.
 */
public class Receive {
    //先运行接收端
    public static void main (String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket(10020);//注意端口冲突

        byte[] buffer = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, 1024);
        datagramSocket.receive(datagramPacket);

        String string = new String(datagramPacket.getData());
        System.out.println(string);

        datagramSocket.close();
    }
}
