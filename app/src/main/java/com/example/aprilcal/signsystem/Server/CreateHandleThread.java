package com.example.aprilcal.signsystem.Server;

import java.net.Socket;

/**
 * Created by AprilCal on 2018/4/9.
 */

public class CreateHandleThread implements Runnable {
    private Socket socket;

    public CreateHandleThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){

    }
}
