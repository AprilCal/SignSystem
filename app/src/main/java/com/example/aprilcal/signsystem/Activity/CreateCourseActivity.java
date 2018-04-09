package com.example.aprilcal.signsystem.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                Course course = new Course();
                course.setCourseName(course_name_edit_text.getText().toString());
                course.setTeacherID(1);
                course.setCreateDate(1000);
                course.setTotalNumber(Integer.valueOf(course_total_number_edit_text.getText().toString()));
                if(CourseBusi.createCourse(getApplicationContext(),course)){
                    Toast.makeText(getApplicationContext(), "创建成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
