package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aprilcal.signsystem.R;

public class ServerMainActivity extends AppCompatActivity {

    private Button create_button;
    private Button course_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_main);

        create_button = (Button)findViewById(R.id.create_button);
        course_button = (Button)findViewById(R.id.course_button);

        create_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent();
                //in.putExtra("user_name",user_name);
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.CreateCourseActivity");
                startActivity(in);
            }
        });
        course_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent in = new Intent();
                //in.putExtra("user_name",user_name);
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.MyCourseActivity");
                startActivity(in);
            }
        });
    }
}

