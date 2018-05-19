package com.example.aprilcal.signsystem.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aprilcal.signsystem.Dao.WiFiHelper;
import com.example.aprilcal.signsystem.Network.NetworkHelper;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Student;

public class SignUpActivity extends AppCompatActivity {

    public static final int SIGN_UP_SUCCESS = 1;
    public static final int SIGN_UP_FAILED = 2;

    private Button student_sign_up_button;
    private EditText student_sign_up_tel_edit_text;
    private EditText student_sign_up_mail_edit_text;
    private EditText student_sign_up_password_edit_text;
    private EditText student_sign_up_confirm_password_edit_text;
    private EditText student_sign_up_studetn_name_edit_text;
    private EditText student_sign_up_school_id_edit_text;
    private EditText student_sign_up_school_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewForAllComponent();

        student_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setTel(student_sign_up_tel_edit_text.getText().toString());
                student.setMail(student_sign_up_mail_edit_text.getText().toString());
                student.setSchoolID(student_sign_up_school_id_edit_text.getText().toString());
                student.setStudentName(student_sign_up_studetn_name_edit_text.getText().toString());
                student.setPassword(student_sign_up_password_edit_text.getText().toString());
                student.setSchool(student_sign_up_school_edit_text.getText().toString());
                student.setMac(new WiFiHelper(getApplicationContext()).getMacAddress());

                if(!ifAnyEditTextEmpty()&&ifPasswordConsistent()){
                    NetworkHelper.StudentSignUpActivity(getApplicationContext(),handler,student);
                }

            }
        });
    }

    private void findViewForAllComponent(){
        student_sign_up_button = (Button)findViewById(R.id.student_sign_up_button);

        student_sign_up_tel_edit_text = (EditText)findViewById(R.id.student_sign_up_tel_edit_text);
        student_sign_up_mail_edit_text = (EditText)findViewById(R.id.student_sign_up_mail_edit_text);
        student_sign_up_password_edit_text = (EditText)findViewById(R.id.student_sign_up_password_edit_text);
        student_sign_up_confirm_password_edit_text = (EditText)findViewById(R.id.student_sign_up_confirm_password_edit_text);
        student_sign_up_studetn_name_edit_text = (EditText)findViewById(R.id.student_sign_up_student_name_edit_text);
        student_sign_up_school_id_edit_text = (EditText)findViewById(R.id.student_sign_up_school_id_edit_text);
        student_sign_up_school_edit_text = (EditText)findViewById(R.id.student_sign_up_school_edit_text);

        student_sign_up_tel_edit_text.setText("13963175832");
        student_sign_up_school_edit_text.setText("武汉理工");
        student_sign_up_mail_edit_text.setText("747998045@qq.com");
        student_sign_up_school_id_edit_text.setText("0121410870922");
        student_sign_up_studetn_name_edit_text.setText("严小威");
        student_sign_up_password_edit_text.setText("123456");
        student_sign_up_confirm_password_edit_text.setText("123456");
    }

    boolean ifPasswordConsistent(){
        if(!student_sign_up_password_edit_text.getText().toString().equals(student_sign_up_confirm_password_edit_text.getText().toString())){
            Toast.makeText(getApplicationContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    boolean ifAnyEditTextEmpty(){
        if(student_sign_up_tel_edit_text.getText().toString().equals("")){
            return true;
        }
        if(student_sign_up_mail_edit_text.getText().toString().equals("")){
            return true;
        }
        if(student_sign_up_school_id_edit_text.getText().toString().equals("")){
            return true;
        }
        if(student_sign_up_school_edit_text.getText().toString().equals("")){
            return true;
        }
        if(student_sign_up_password_edit_text.getText().toString().equals("")){
            return true;
        }
        if(student_sign_up_confirm_password_edit_text.getText().equals("")){
            return true;
        }
        if(student_sign_up_studetn_name_edit_text.getText().toString().equals("")){
            return true;
        }
        return false;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SIGN_UP_SUCCESS:
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case SIGN_UP_FAILED:
                    //TODO show detail info;
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
