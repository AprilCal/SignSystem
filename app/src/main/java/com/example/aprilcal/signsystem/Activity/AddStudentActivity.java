package com.example.aprilcal.signsystem.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Adaper.ElectiveItemAdaper;
import com.example.aprilcal.signsystem.Busi.ElectiveBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Elective;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private ListView add_student_list_view;
    private EditText add_student_name_edit_text;
    private EditText add_student_id_edit_text;
    private Button add_student_add_button;
    private Button add_student_end_button;
    private Button add_student_import_button;

    private List<Elective> electiveList;
    private List<Elective> retreatList;

    private ElectiveItemAdaper electiveItemAdaper;

    private Intent intent;
    int courseID;

    private void refreshElectiveList(){
        electiveItemAdaper.notifyDataSetChanged();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1000, 0, "删除成员");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = (ContextMenu.ContextMenuInfo) item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = (int)info.id;
        switch (item.getItemId()) {
            case 1000:
                retreatList.add(electiveList.get(id));
                electiveList.remove(id);
                refreshElectiveList();
                break;
            default:
                break;
        }
        boolean b = super.onContextItemSelected(item);
        return super.onContextItemSelected(item);
    }

    private boolean isInfoEmpty(){
        if(add_student_id_edit_text.getText().toString().equals("")){
            return true;
        }
        if(add_student_name_edit_text.getText().toString().equals("")){
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        add_student_list_view = (ListView)findViewById(R.id.add_student_list_view);
        add_student_id_edit_text = (EditText)findViewById(R.id.add_student_id_edit_text);
        add_student_name_edit_text = (EditText)findViewById(R.id.add_student_name_edit_text);
        add_student_add_button = (Button)findViewById(R.id.add_student_add_button);
        add_student_end_button = (Button)findViewById(R.id.add_student_end_button);
        add_student_import_button = (Button)findViewById(R.id.add_student_import_button);

        intent = getIntent();
        courseID = intent.getIntExtra("courseID",0);

        electiveList = new ArrayList<Elective>();
        electiveList = ElectiveBusi.getAllElectiveByCourseID(getApplicationContext(), courseID);
        retreatList = new ArrayList<Elective>();

        add_student_list_view.setOnCreateContextMenuListener(this);
        electiveItemAdaper = new ElectiveItemAdaper(AddStudentActivity.this,R.layout.elective_list_item, electiveList);
        add_student_list_view.setAdapter(electiveItemAdaper);

        add_student_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isInfoEmpty()){
                    String schoolID = add_student_id_edit_text.getText().toString();
                    String studentName = add_student_name_edit_text.getText().toString();
                    addElectiveToListWithNotification(new Elective(0,schoolID,courseID,studentName,0,0));
                }
            }
        });

        add_student_end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElectiveBusi.batchRetreat(getApplicationContext(),retreatList);
                ElectiveBusi.elect(getApplicationContext(),electiveList);
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }
        });

        add_student_import_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(Intent.createChooser(intent, "请选择导入方式"),0);
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });
    }

    public void addElectiveToListWithNotification(Elective elective){
        for(Elective e : electiveList){
            if(e.getSchoolID().equals(elective.getSchoolID())){
                Toast.makeText(getApplicationContext(), "有学号重复，请检查", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        electiveList.add(elective);
        refreshElectiveList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (requestCode == 0) {
            Uri uri = data.getData();
            File f = new File(uri.getPath());
            int length = (int) f.length();
            String path = "/storage/emulated/0/"+uri.getPath().substring(16);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"GBK"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    Log.d("line",line);
                    String[] info = line.split(" ");
                    Log.d("info",info[0]);
                    Log.d("name",info[1]);
                    addElectiveToListWithNotification(new Elective(0,info[0],courseID,info[1],0,0));
                }
                super.onActivityResult(requestCode, resultCode, data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
