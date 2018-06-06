package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Adaper.SignItemAdaper;
import com.example.aprilcal.signsystem.Busi.CourseBusi;
import com.example.aprilcal.signsystem.Busi.ElectiveBusi;
import com.example.aprilcal.signsystem.Busi.SignBusi;
import com.example.aprilcal.signsystem.Busi.SignInBusi;
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

    private void refreshSignList(){
        List<Sign> newSignList = SignBusi.getAllSignByCourseID(getApplicationContext(),course.getCourseID());
        signList.clear();
        for(Sign sign : newSignList){
            signList.add(sign);
        }
        signItemAdaper.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        refreshSignList();
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
        sign_list_view.setOnCreateContextMenuListener(this);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1000, 0, "删除签到");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = (ContextMenu.ContextMenuInfo) item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = (int)info.id;
        int signID = signList.get(id).getSignID();
        switch (item.getItemId()) {
            case 1000:
                SignBusi.removeSignBySignID(getApplicationContext(),signID);
                SignInBusi.removeAllSignInBySignID(getApplicationContext(),signID);
                refreshSignList();
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        boolean b = super.onContextItemSelected(item);
        return super.onContextItemSelected(item);
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
                refreshSignList();
                Toast.makeText(getApplicationContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.teacher_course_del_item:
                /* Delete all sign & sign in inside the course.*/
                List<Sign> toBeDeletedSignList = SignBusi.getAllSignByCourseID(getApplicationContext(),course.getCourseID());
                for(Sign sign : toBeDeletedSignList){
                    SignInBusi.removeAllSignInBySignID(getApplicationContext(),sign.getSignID());
                    SignBusi.removeSignBySignID(getApplicationContext(),sign.getSignID());
                }
                ElectiveBusi.removeElectiveByCourseID(getApplicationContext(),course.getCourseID());
                CourseBusi.deleteCourseByCourseID(getApplicationContext(),course.getCourseID());
                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                finish();
                return true;
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
