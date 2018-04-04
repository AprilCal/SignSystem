package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.aprilcal.signsystem.R;

public class MyCourseActivity extends AppCompatActivity {

    View create_view;
    TextView course_detial_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);

        create_view = (View)findViewById(R.id.creat_view);
        course_detial_text = (TextView)findViewById(R.id.course_detial_text1);

        create_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.CreateCourseActivity");
                startActivity(in);
            }
        });

        course_detial_text.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.CourseDetailActivity");
                startActivity(in);
            }
        });
    }
}
