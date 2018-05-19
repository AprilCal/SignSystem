package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Adaper.ElectiveItemAdaper;
import com.example.aprilcal.signsystem.Busi.ElectiveBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Elective;
import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private ListView add_student_list_view;
    private EditText add_student_name_edit_text;
    private EditText add_student_id_edit_text;
    private Button add_student_add_button;
    private Button add_student_end_button;

    private List<Elective> electiveList;
    private ElectiveItemAdaper electiveItemAdaper;

    private Intent intent;
    int courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        add_student_list_view = (ListView)findViewById(R.id.add_student_list_view);
        add_student_id_edit_text = (EditText)findViewById(R.id.add_student_id_edit_text);
        add_student_name_edit_text = (EditText)findViewById(R.id.add_student_name_edit_text);
        add_student_add_button = (Button)findViewById(R.id.add_student_add_button);
        add_student_end_button = (Button)findViewById(R.id.add_student_end_button);

        intent = getIntent();
        courseID = intent.getIntExtra("courseID",0);

        electiveList = new ArrayList<Elective>();
        electiveList = ElectiveBusi.getAllElectiveByCourseID(getApplicationContext(), courseID);

        electiveItemAdaper = new ElectiveItemAdaper(AddStudentActivity.this,R.layout.elective_list_item, electiveList);
        add_student_list_view.setAdapter(electiveItemAdaper);




        add_student_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String schoolID = add_student_id_edit_text.getText().toString();
                String studentName = add_student_name_edit_text.getText().toString();
                for(Elective elective : electiveList){
                    if(elective.getSchoolID().equals(schoolID)){
                        Toast.makeText(getApplicationContext(), "该学号重复", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                electiveList.add(new Elective(0,schoolID,courseID,studentName,0,0));
                electiveItemAdaper.notifyDataSetChanged();
            }
        });

        add_student_end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElectiveBusi.elect(getApplicationContext(),electiveList);
                Toast.makeText(getApplicationContext(), "添加人员成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
