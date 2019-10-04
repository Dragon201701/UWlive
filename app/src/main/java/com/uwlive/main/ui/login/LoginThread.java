package com.uwlive.main.ui.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginThread implements Runnable {
    private Socket socket;
    private static final int SERVERPORT = 10086;
    private static final String SERVER_IP = "45.32.69.221";
    String username;
    String password;
    public void LoginThread(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Override
    public void run() {
        //Socket socket = null;
        String message = "Hello, World!";
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
            socket = new Socket(serverAddr, SERVERPORT);
            //OutputStream os = socket.getOutputStream();
            //os.write(message.getBytes());
            //socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException. Test Failed.");
        }
    }
    public boolean Login(String username, String password){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println("Hello, " + username + " " + password + "!");
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    /*public boolean Close(){
        try{
            socket.close();
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/
}
