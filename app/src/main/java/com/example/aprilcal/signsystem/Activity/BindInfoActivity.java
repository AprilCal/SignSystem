package com.example.aprilcal.signsystem.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.aprilcal.signsystem.R;

public class BindInfoActivity extends AppCompatActivity {

    private EditText bind_info_school_id_edit_text;
    private EditText bind_info_studetn_name_edit_text;
    private EditText bind_info_school_name_edit_text;
    private Button bind_info_bind_button;
    private Button bind_cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_info);

        bind_info_bind_button = (Button)findViewById(R.id.bind_info_bind_button);
        bind_cancel_button = (Button)findViewById(R.id.bind_info_cancel_button);
        bind_info_school_id_edit_text = (EditText)findViewById(R.id.bind_info_school_id_edit_text);
        bind_info_studetn_name_edit_text = (EditText)findViewById(R.id.bind_info_student_name_edit_text);
        bind_info_school_name_edit_text = (EditText)findViewById(R.id.bind_info_school_name_edit_text);

        bind_info_bind_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "绑定成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        bind_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
