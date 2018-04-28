package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.aprilcal.signsystem.R;

public class MainActivity extends AppCompatActivity {

    private Button signin_button;
    private Button signup_button;
    private Button pass_button;

    private EditText count_text;
    private EditText password_text;

    private RadioButton student_radiobutton;
    private RadioButton teacher_radiobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin_button = (Button) findViewById(R.id.SignInButton);
        signup_button = (Button) findViewById(R.id.SignUpButton);
        pass_button = (Button) findViewById(R.id.PassButton);

        student_radiobutton=(RadioButton)findViewById(R.id.student_radiobutton);
        teacher_radiobutton=(RadioButton)findViewById(R.id.teacher_radiobutton);

        count_text = (EditText) findViewById(R.id.CountText);
        password_text = (EditText) findViewById(R.id.PasswordText);

        signin_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(teacher_radiobutton.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("identity",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("teacherID",1);
                    edit.commit();

                    Intent in = new Intent();
                    in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ServerMainActivity");
                    startActivity(in);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("identity",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    //TODO data resourse;
                    edit.putInt("studentID",1);
                    edit.putString("schoolID","0121410870922");
                    edit.putString("studentName","王承禹");
                    edit.putString("tel","15527358829");
                    edit.putString("mail","747998045@qq.com");
                    edit.commit();
                    Intent in = new Intent();
                    in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ClientMainActivity");
                    startActivity(in);
                    finish();
                }
            }
        });

        student_radiobutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                teacher_radiobutton.setChecked(false);
            }
        });
        teacher_radiobutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                student_radiobutton.setChecked(false);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.SignUpActivity");
                startActivity(in);
            }
        });

        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teacher_radiobutton.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent();
                    in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ServerMainActivity");
                    startActivity(in);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent();
                    in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ClientMainActivity");
                    startActivity(in);
                    finish();
                }
            }
        });
    }
}