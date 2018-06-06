package com.example.aprilcal.signsystem.Server;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.aprilcal.signsystem.Activity.SignActivity;
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
    private Handler handler;
    private InputStream inputStream;
    private OutputStream outputStream;

    public CreateHandleThread(Socket socket, Handler handler) {
        this.socket = socket;
        this.handler = handler;
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
                    if(new String(data).equals("Are u teacher")){
                        outputStream.write("y".getBytes());
                        break;
                    }
                }
            }
            while(true){
                bytes = inputStream.read(buffer);
                if(bytes>0){
                    final byte[] data = new byte[bytes];
                    System.arraycopy(buffer, 0, data, 0, bytes);
                    String infos = new String(data);
                    Log.d("stuInfo",infos);
                    // infos = "studentID,schoolID,studentName";
                    String [] infoDetail = infos.split(",");
                    int studentID = Integer.valueOf(infoDetail[0]);
                    String schoolID = infoDetail[1];
                    String studentName = infoDetail[2];
                    //Notify UI;
                    Message message = Message.obtain();
                    message.what = SignActivity.SIGN_INC;
                    Bundle bundle = new Bundle();
                    bundle.putInt("studentID", studentID);
                    bundle.putString("schoolID", schoolID);
                    bundle.putString("studentName", studentName);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    outputStream.write("success".getBytes());
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
