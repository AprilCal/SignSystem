package com.example.aprilcal.signsystem.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aprilcal.signsystem.R;

public class SignUpActivity extends AppCompatActivity {

    private Button sign_up_button;
    private EditText sign_up_tel_edit_text;
    private EditText sign_up_mail_edit_text;
    private EditText sign_up_password_edit_text;
    private EditText sign_up_confirm_password_edit_text;
    private EditText sign_up_studetn_name_edit_text;
    private EditText sign_up_school_id_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_up_button = (Button) findViewById(R.id.sign_up_button);

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
