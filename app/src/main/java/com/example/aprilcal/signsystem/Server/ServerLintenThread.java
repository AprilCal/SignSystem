package com.example.aprilcal.signsystem.Server;

import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by AprilCal on 2018/4/9.
 */

public class ServerLintenThread implements Runnable {
    private ServerSocket serverSocket = null;
    private Handler handler;
    private int port;
    private Socket socket;

    private volatile boolean cancelled = false;

    public void cancel() {
        cancelled = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerLintenThread(int port, Handler handler) {
        this.port = port;
        this.handler = handler;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (!cancelled){
            try {
                Log.d("server start listen:",String.valueOf(12345));
                socket = serverSocket.accept();
                Log.d("there is a connection:", socket.getInetAddress().toString());
                new CreateHandleThread(socket,handler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
