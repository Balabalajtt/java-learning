import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by 江婷婷 on 2017/8/7.
 */
public class Send {
    public static void main (String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(10010);//端口不能相同
        String string = "你好，我是皮皮猪";

        //报包模式传输数据
        DatagramPacket datagramPacket;
        datagramPacket = new DatagramPacket(string.getBytes(), string.getBytes().length, InetAddress.getLocalHost(), 10020);//注意长度是字节数


        datagramSocket.send(datagramPacket);

        datagramSocket.close();

    }
}
