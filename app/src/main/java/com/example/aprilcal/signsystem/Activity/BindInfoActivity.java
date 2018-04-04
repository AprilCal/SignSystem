package com.example.aprilcal.signsystem.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aprilcal.signsystem.R;

public class BindInfoActivity extends AppCompatActivity {

    private Button bind_button;
    private Button bind_cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_info);

        bind_button = (Button)findViewById(R.id.bind_button);
        bind_cancel_button = (Button)findViewById(R.id.bind_cancel_button);

        bind_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "绑定成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        bind_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
