package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aprilcal.signsystem.Adaper.CourseItemAdaper;
import com.example.aprilcal.signsystem.Busi.CourseBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Course;

import java.util.ArrayList;
import java.util.List;

public class MyCourseActivity extends AppCompatActivity {

    private View create_view;
    private TextView course_detial_text;
    private ListView course_list_view;
    private CourseItemAdaper courseItemAdaper;
    private static List<Course> courseList = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);

        courseList = CourseBusi.getAllCourse(this.getApplicationContext());


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
        courseItemAdaper = new CourseItemAdaper(MyCourseActivity.this,R.layout.course_list_item,courseList);
        course_list_view = (ListView) findViewById(R.id.course_list_view);
        course_list_view.setAdapter(courseItemAdaper);
    }
}
