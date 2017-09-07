import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/9/4.
 */
public class Server {
    private List<MyChannel> list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(9898);
        while (true) {
            Socket client = server.accept();
            MyChannel myChannel = new MyChannel(client);
            list.add(myChannel);
            new Thread(myChannel).start();
        }
    }

    private class MyChannel implements Runnable {
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private boolean isRunning = true;
        private String name;

        public MyChannel(Socket client) {
            try {
                dataInputStream = new DataInputStream(client.getInputStream());
                dataOutputStream = new DataOutputStream(client.getOutputStream());
                this.name = dataInputStream.readUTF();
                this.send("欢迎进入聊天室\n\n");
                this.sendOthers(this.name + "进入了聊天室\n\n");

            } catch (IOException e) {
                isRunning = false;
                try {
                    dataInputStream.close();
                    dataOutputStream.close();
                } catch (IOException e1) {}
            }
        }

        private String receive() {
            String msg = "";
            try {
                Date time = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String t = format.format(time);
                msg = this.name + ": " + t + "\n" + dataInputStream.readUTF() + "\n\n";
            } catch (IOException e) {
                isRunning = false;
                try {
                    dataInputStream.close();
                } catch (IOException e1) {}
                list.remove(this);
                sendOthers(this.name + "离开了聊天室");
            }
            return msg;
        }

        private void send(String msg) {
            if (msg == null || msg == "") {
                return;
            }
            try {
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            } catch (IOException e) {
                isRunning = false;
                try {
                    dataOutputStream.close();
                } catch (IOException e1) {}
                list.remove(this);
                sendOthers(this.name + "离开了聊天室");
            }
        }

        private void sendOthers(String msg) {
            for (MyChannel others : list) {
                if (others == this) {
                    break;
                }
            }
            for (MyChannel others : list) {
                if (others == this) {
                    continue;
                }
                others.send(msg);
            }
        }


        @Override
        public void run() {
            while (isRunning) {
                sendOthers(receive());
            }
        }
    }
}
