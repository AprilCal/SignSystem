package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aprilcal.signsystem.Busi.SignBusi;
import com.example.aprilcal.signsystem.Dao.WiFiHelper;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.Server.ServerLintenThread;
import com.example.aprilcal.signsystem.vo.Sign;

public class SignActivity extends AppCompatActivity {

    private Button start_sign_button;
    private Button end_sign_button;
    private WiFiHelper wiFiHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        wiFiHelper = new WiFiHelper(this.getApplicationContext());

        start_sign_button = (Button) findViewById(R.id.start_sign_button);
        end_sign_button = (Button) findViewById(R.id.end_sign_button);

        start_sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wiFiHelper.openAp("AprilCal","123456789", getApplicationContext());
                final Intent intent = getIntent();
                //TODO fill field of sign;
                SignBusi.sign(getApplicationContext(),new Sign());
                ServerLintenThread serverLintenThread = new ServerLintenThread(12345);
                Thread listenThread = new Thread(serverLintenThread);
                listenThread.start();
                //wiFiHelper.getConnectCount();
            }
        });
        end_sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wiFiHelper.closeAp();
            }
        });
    }
}
