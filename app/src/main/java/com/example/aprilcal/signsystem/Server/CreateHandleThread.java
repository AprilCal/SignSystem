package com.example.aprilcal.signsystem.Server;

import android.util.Log;

import com.example.aprilcal.signsystem.vo.Sign;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by AprilCal on 2018/4/9.
 */

//TODO modify Thread; should implements Runnable , use Executer.
public class CreateHandleThread extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public CreateHandleThread(Socket socket) {
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            byte[] buffer = new byte[1024];
            int bytes;
            while(true){
                bytes = inputStream.read(buffer);
                if(bytes>0){
                    final byte[] data = new byte[bytes];
                    System.arraycopy(buffer, 0, data, 0, bytes);
                    Log.d("receive",new String(data));
                    if(new String(buffer).equals("Are u teacher")){
                        outputStream.write("y".getBytes());
                        break;
                    }
                }
            }
            while(true){
                bytes = inputStream.read(buffer);
                if(bytes>0){
                    //Sign sign = new Sign();
                    //TODO deal with sign info;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
