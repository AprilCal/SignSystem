package com.example.aprilcal.signsystem.Server;

import android.util.Log;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Handler;

/**
 * Created by AprilCal on 2018/4/9.
 */

public class ServerLintenThread implements Runnable {
    private ServerSocket serverSocket = null;
    private Handler handler;
    private int port;
    private Socket socket;

    public ServerLintenThread(int port) {
        this.port = port;
        //this.handler = handler;
        try {
            serverSocket = new ServerSocket(port);
            Log.d("","service start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (true){
            try {
                Log.d("server start listen:",String.valueOf(12345));
                socket = serverSocket.accept();
                Log.d("there is a connection:", socket.getInetAddress().toString());
                new CreateHandleThread(socket).start();
                //Message message = Message.obtain();
                //message.what = MainActivity.DEVICE_CONNECTING;
                //handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
