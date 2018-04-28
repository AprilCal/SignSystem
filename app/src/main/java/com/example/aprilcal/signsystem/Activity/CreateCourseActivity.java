package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aprilcal.signsystem.Busi.CourseBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Course;

import java.sql.Date;

public class CreateCourseActivity extends AppCompatActivity {

    private EditText course_name_edit_text;
    private EditText course_total_number_edit_text;
    private Button create_course_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        create_course_button = (Button)findViewById(R.id.create_course_button);
        course_name_edit_text = (EditText)findViewById(R.id.course_name_edit_text);
        course_total_number_edit_text = (EditText)findViewById(R.id.course_total_number_edit_text);

        create_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(course_name_edit_text.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "课程名称不可以为空", Toast.LENGTH_SHORT).show();
                }
                else if(course_total_number_edit_text.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "请输入课程人数", Toast.LENGTH_SHORT).show();
                }
                else{
                    Course course = new Course();
                    course.setCourseName(course_name_edit_text.getText().toString());
                    course.setTeacherID(1);
                    course.setCreateDate(1000);
                    course.setTotalNumber(Integer.valueOf(course_total_number_edit_text.getText().toString()));
                    //TODO modify insert;
                    int courseID = CourseBusi.createCourse(getApplicationContext(),course);
                    Intent in = new Intent();
                    in.putExtra("courseID",courseID);
                    Toast.makeText(getApplicationContext(), "课程创建成功，添加人员名单", Toast.LENGTH_SHORT).show();
                    in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.AddStudentActivity");
                    startActivity(in);
                    finish();
                }
            }
        });
    }
}
