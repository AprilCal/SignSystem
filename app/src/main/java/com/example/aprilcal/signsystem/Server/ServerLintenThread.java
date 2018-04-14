package com.example.aprilcal.signsystem.Server;

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

    public ServerLintenThread(int port, Handler handler) {
        this.port = port;
        this.handler = handler;
        try {
            serverSocket=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (true){
            try {
                socket = serverSocket.accept();
                //Message message = Message.obtain();
                //message.what = MainActivity.DEVICE_CONNECTING;
                //handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
