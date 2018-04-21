package com.example.aprilcal.signsystem.Client;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by AprilCal on 2018/4/13.
 */

//TODO abstract , template method; like below;
    /*
        public abstract class AbstractConnectThread implements Runnable{
            private Socket socket;
            private OutputStream outputStream;
            private InputStream inputStream;

            public AbstractConnectedThread()
        }

        public class ConcreteConnectThread implements Runnable extends AbstractConnectThread{
            public ConcreteConnectThread(String ip, int port, Sign sign){

            }
            @Override
            public void init(){
            }
        }
     */
public class ConnectThread extends Thread {
    private OutputStream outputStream;
    private InputStream inputStream;
    private String signInfo;

    //TODO for test, delete after test;
    private Context context;

    public ConnectThread(Socket socket,String signInfo,Context context) {
        this.signInfo = signInfo;
        this.context = context;
        try {
            this.outputStream = socket.getOutputStream();
            this.inputStream = socket.getInputStream();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            outputStream.write("Are u teacher".getBytes());
            Log.d("send","Are u teacher");
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                bytes = inputStream.read(buffer);
                if (bytes > 0) {
                    Log.d("receive",new String(buffer));
                    final byte[] data = new byte[bytes];
                    System.arraycopy(buffer, 0, data, 0, bytes);
                    String answer = new String(data);
                    if(answer.equals("y")){
                        outputStream.write("".getBytes());
                        break;
                    }
                }
            }
            outputStream.write(signInfo.getBytes());
            while (true) {
                bytes = inputStream.read(buffer);
                if (bytes > 0) {
                    final byte[] data = new byte[bytes];
                    System.arraycopy(buffer, 0, data, 0, bytes);
                    String answer = new String(data);
                    Toast.makeText(context.getApplicationContext(), answer, Toast.LENGTH_SHORT).show();
                    if(answer.equals("success")){
                        Toast.makeText(context.getApplicationContext(), "sign success", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
            //TODO broadcast sign success;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
