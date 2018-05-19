package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Adaper.SignItemAdaper;
import com.example.aprilcal.signsystem.Busi.CourseBusi;
import com.example.aprilcal.signsystem.Busi.SignBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Course;
import com.example.aprilcal.signsystem.vo.Sign;
import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    private Button start_sign_button;
    private Menu menu;
    private ListView sign_list_view;
    private SignItemAdaper signItemAdaper;
    private static List<Sign> signList = new ArrayList<Sign>();
    private Course course;

    private void initCourse(){
        final Intent intent = getIntent();
        course = new Course();
        course.setCourseID(intent.getIntExtra("courseID",0));
        course.setCourseName(intent.getStringExtra("courseName"));
        course.setTeacherID(intent.getIntExtra("teacherID",0));
        course.setTotalNumber(intent.getIntExtra("totalNumber",0));
        course.setBackup(intent.getIntExtra("backup",0));
        course.setCreateDate(intent.getIntExtra("createDate",0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initCourse();

        start_sign_button = (Button)findViewById(R.id.start_sign_button);

        start_sign_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent();
                in.putExtra("courseID",course.getCourseID());
                in.putExtra("courseName",course.getCourseName());
                in.putExtra("totalNumber",course.getTotalNumber());
                in.putExtra("teacherID",course.getTeacherID());
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.SignActivity");
                startActivity(in);
            }
        });

        signList = SignBusi.getAllSignByCourseID(getApplicationContext(),course.getCourseID());
        signItemAdaper = new SignItemAdaper(CourseDetailActivity.this,R.layout.sign_list_item,signList);
        sign_list_view = (ListView) findViewById(R.id.sign_list_view);
        sign_list_view .setAdapter(signItemAdaper);
        Toast.makeText(getApplicationContext(), String.valueOf(signList.size()), Toast.LENGTH_SHORT).show();

        sign_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.putExtra("courseID",course.getCourseID());
                in.putExtra("courseName",course.getCourseName());
                in.putExtra("totalNumber",course.getTotalNumber());
                in.putExtra("signID",signList.get((int)id).getSignID());
                in.putExtra("actualNumber", signList.get((int)id).getActualNumber());
                in.putExtra("signDate", signList.get((int)id).getSignDate());
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.SignDetailActivity");
                startActivity(in);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Initialize menu item. */
        getMenuInflater().inflate(R.menu.teacher_course_function_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.teacher_course_sync_item:
                Toast.makeText(getApplicationContext(),"同步成功",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.teacher_course_fresh_item:
                Toast.makeText(getApplicationContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.teacher_course_del_item:
                CourseBusi.deleteCourseByCourseID(getApplicationContext(),course.getCourseID());
                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                finish();
            case R.id.teacher_course_member_item:
                Intent in = new Intent();
                in.putExtra("courseID",course.getCourseID());
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.AddStudentActivity");
                startActivity(in);
                return true;
            default:
                return false;
        }
    }
}
