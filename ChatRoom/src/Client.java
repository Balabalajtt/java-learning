import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by 江婷婷 on 2017/9/6.
 */

public class Client {
    private String name;



    public static void main (String[] args) {

        Client client = new Client();
        Socket socket = null;
        try {
            socket = new Socket("localhost", 9898);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        InitWindow initWindow = new InitWindow(client, socket);
        initWindow.signInWindow();


    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }
}

class InitWindow extends JFrame{

    private String msg;
    private String name = null;
    private Client client;
    private Socket socket;

    InitWindow(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
    }

    public String getName () {
        return this.name;
    }

    void signInWindow() {

        JFrame signIn;
        JTextArea word, idText;
        signIn = new JFrame();
        signIn.setTitle("登录");
        word = new JTextArea("请输入ID：", 1, 1);
        word.setEditable(false);
        idText = new JTextArea(10, 1);
        JButton signInButton = new JButton("登录");

        signIn.getContentPane().add(BorderLayout.NORTH, word);
        signIn.getContentPane().add(BorderLayout.CENTER, idText);
        signIn.getContentPane().add(BorderLayout.SOUTH, signInButton);

        signIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signIn.setSize(300, 150);
        signIn.setVisible(true);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idText.getText().equals("") && idText.getText() != null) {
                    client.setName(idText.getText());
                    name = idText.getText();
                    client.setName(name);

                    signIn.setVisible(false);
                    try {
                        chatWindow();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }



    private void chatWindow() throws InterruptedException {


        JFrame frame;
        JTextArea inputText, outputText;
        JMenuBar jMenuBar;
        JScrollPane jScrollPane;

        frame = new JFrame();
        frame.setTitle(name);
        inputText = new JTextArea(10, 33);
        outputText = new JTextArea(10, 33);
        outputText.setEditable(false);
        JButton button = new JButton("发送");

//        Thread sendThread = new Thread(new Send(socket, client, inputText));
        Send send = new Send(socket, client, inputText);
        new Thread(new Receive(socket, client, outputText)).start();

        jMenuBar = new JMenuBar();
        this.setJMenuBar(jMenuBar);
        jScrollPane = new JScrollPane(outputText);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.add(jScrollPane);

        inputText.setLineWrap(true);
        outputText.setLineWrap(true);

        JTextArea textAreaOutput;
        JPanel panelOutput;

        panelOutput = new JPanel();
        panelOutput.add(new JScrollPane(outputText));

        button.addActionListener(e -> {
            // TODO Auto-generated method stub
            msg = inputText.getText();

            send.send(msg);
            Date time = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String t = format.format(time);
            outputText.append("我: " + t + "\n" + msg + "\n\n");
            inputText.setText("");
        });

        JPanel in = new JPanel();
        in.add(inputText);
        in.setBackground(Color.lightGray);
        JPanel out = new JPanel();
        out.add(outputText);
        in.setBackground(Color.lightGray);

        frame.getContentPane().add(BorderLayout.NORTH, out);
        frame.getContentPane().add(BorderLayout.CENTER, in);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

}



class Send {
    private DataOutputStream dataOutputStream;
    private String name;
    private JTextArea inputText;
    private Client client;

    public Send(Socket socket, Client client, JTextArea inputText) {

        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.name = client.getName();
            this.send(name);

            this.inputText = inputText;
            this.client = client;

        } catch (IOException e) {
            try {
                dataOutputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    public void send(String msg) {
        try {
            if (msg != null && !msg.equals("")) {
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            }
        } catch (IOException e) {
            try {
                dataOutputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}



class Receive implements Runnable {
    private DataInputStream dataInputStream;
    private boolean isRunning = true;
    private Client client;
    private JTextArea outputText;
    private Socket socket;
    public Receive(Socket socket, Client client, JTextArea outputText) {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            this.client = client;
            this.outputText = outputText;
            this.socket = socket;
        } catch (IOException e) {
            isRunning = false;
            try {
                dataInputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String receive() {
        String msg = "";
        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            isRunning = false;
            try {
                dataInputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return msg;
    }

    @Override
    public void run() {
        while (isRunning) {
            outputText.append(receive());
        }
    }
}









