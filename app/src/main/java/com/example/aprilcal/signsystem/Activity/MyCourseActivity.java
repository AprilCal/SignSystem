package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.aprilcal.signsystem.Adaper.CourseItemAdaper;
import com.example.aprilcal.signsystem.Busi.CourseBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Course;
import java.util.ArrayList;
import java.util.List;

public class MyCourseActivity extends AppCompatActivity {

    private View create_view;
    private ListView course_list_view;
    private CourseItemAdaper courseItemAdaper;
    private static List<Course> courseList = new ArrayList<Course>();

    private void refreshCourseList(){
        List<Course> newCourseList = CourseBusi.getAllCourse(this.getApplicationContext());
        courseList.clear();
        for(Course course : newCourseList){
            courseList.add(course);
        }
        courseItemAdaper.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        refreshCourseList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course);

        courseList = CourseBusi.getAllCourse(this.getApplicationContext());

        create_view = (View)findViewById(R.id.creat_view);
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

        courseItemAdaper = new CourseItemAdaper(MyCourseActivity.this,R.layout.course_list_item,courseList);
        course_list_view = (ListView) findViewById(R.id.course_list_view);
        course_list_view.setAdapter(courseItemAdaper);

        course_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.putExtra("courseID",courseList.get((int)id).getCourseID());
                in.putExtra("courseName",courseList.get((int)id).getCourseName());
                in.putExtra("totalNumber",courseList.get((int)id).getTotalNumber());
                in.putExtra("teacherID",courseList.get((int)id).getTeacherID());
                in.putExtra("createDate",courseList.get((int)id).getCreateDate());
                in.putExtra("backup",courseList.get((int)id).isBackup());
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.CourseDetailActivity");
                startActivity(in);
            }
        });
    }
}
