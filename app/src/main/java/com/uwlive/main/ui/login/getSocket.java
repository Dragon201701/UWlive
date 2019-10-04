package com.uwlive.main.ui.login;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class getSocket extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        String message = "Hello World!";
        Socket socket;
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("45.32.69.221", 10086));
            OutputStream out = socket.getOutputStream();
            out.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
