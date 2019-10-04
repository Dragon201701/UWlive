package com.uwlive.main.ui.login;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginThread1 extends Thread {
    int port;
    LoginThread1(int port){
        this.port = port;
    }
    public void run() {
        Socket socket = null;
        String message = "Hello, World!";
        try {
            /*s = new Socket("45.32.69.221", 10086);
            String message = "Hello, World!";
            OutputStream os = s.getOutputStream();
            os.write(message.getBytes());
            s.close();*/
            socket = new Socket();
            socket.connect(new InetSocketAddress("45.32.69.221", 10086));
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "ISO-8859-1"));
            OutputStream out = socket.getOutputStream();
            out.write(message.getBytes());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException. Test Failed.");
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
