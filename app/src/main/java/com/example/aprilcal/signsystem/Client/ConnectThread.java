package com.example.aprilcal.signsystem.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by jhon on 2018/4/13.
 */

public class ConnectThread extends Thread {
    private OutputStream outputStream;
    private InputStream inputStream;

    public ConnectThread(Socket socket) {
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
            outputStream.write("are u teacher".getBytes());
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                bytes = inputStream.read(buffer);
                if (bytes > 0) {
                    final byte[] data = new byte[bytes];
                    System.arraycopy(buffer, 0, data, 0, bytes);

                    String answer = new String(data);
                    if(answer.equals("y")){
                        break;
                    }
                    else{
                        continue;
                    }
                }
            }

        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
