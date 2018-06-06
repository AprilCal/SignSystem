package com.example.aprilcal.signsystem.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Network.NetworkHelper;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Teacher;
import java.util.regex.Pattern;

public class TeacherSignUpActivity extends AppCompatActivity {

    public static final int SIGN_UP_SUCCESS = 1;
    public static final int SIGN_UP_FAILED = 2;
    public static final int USER_ALREADY_EXIST = 3;

    private EditText teacher_sign_up_tel_edit_text;
    private EditText teacher_sign_up_mail_edit_text;
    private EditText teacher_sign_up_teacher_name_edit_text;
    private EditText teacher_sign_up_school_edit_text;
    private EditText teacher_sign_up_password_edit_text;
    private EditText teacher_sign_up_confirm_password_edit_text;
    private Button teacher_sign_up_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);

        teacher_sign_up_tel_edit_text = (EditText) findViewById(R.id.teacher_sign_up_tel_edit_text);
        teacher_sign_up_mail_edit_text = (EditText) findViewById(R.id.teacher_sign_up_mail_edit_text);
        teacher_sign_up_teacher_name_edit_text = (EditText) findViewById(R.id.teacher_sign_up_teacher_name_edit_text);
        teacher_sign_up_school_edit_text = (EditText) findViewById(R.id.teacher_sign_up_school_edit_text);
        teacher_sign_up_password_edit_text = (EditText) findViewById(R.id.teacher_sign_up_password_edit_text);
        teacher_sign_up_confirm_password_edit_text = (EditText) findViewById(R.id.teacher_sign_up_confirm_password_edit_text);

        teacher_sign_up_button = (Button) findViewById(R.id.teacher_sign_up_button);

        teacher_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher teacher = new Teacher();
                teacher.setTel(teacher_sign_up_tel_edit_text.getText().toString());
                teacher.setMail(teacher_sign_up_mail_edit_text.getText().toString());
                teacher.setTeacherName(teacher_sign_up_teacher_name_edit_text.getText().toString());
                teacher.setSchool(teacher_sign_up_school_edit_text.getText().toString());
                teacher.setPassword(teacher_sign_up_password_edit_text.getText().toString());

                if(!ifAnyEditTextEmpty()&&ifPasswordConsistent()&&isFormatValid()){
                    NetworkHelper.TeacherSignUpRequest(getApplicationContext(),handler,teacher);
                }
            }
        });
    }

    boolean isFormatValid(){
        //TODO modify regex
        String REGEX_MAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        String REGEX_TEL = "^1[0-9]{10}$";

        if(!Pattern.matches(REGEX_TEL,teacher_sign_up_tel_edit_text.getText().toString())){
            Toast.makeText(getApplicationContext(), "号码格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Pattern.matches(REGEX_MAIL,teacher_sign_up_mail_edit_text.getText().toString())) {
            Toast.makeText(getApplicationContext(), "邮箱格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    boolean ifPasswordConsistent(){
        if(!teacher_sign_up_password_edit_text.getText().toString().equals(teacher_sign_up_confirm_password_edit_text.getText().toString())){
            Toast.makeText(getApplicationContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    boolean ifAnyEditTextEmpty(){
        boolean flag = false;
        if(teacher_sign_up_tel_edit_text.getText().toString().equals("")){
            flag = true;
        }
        if(teacher_sign_up_mail_edit_text.getText().toString().equals("")){
            flag = true;
        }
        if(teacher_sign_up_teacher_name_edit_text.getText().toString().equals("")){
            flag = true;
        }
        if(teacher_sign_up_school_edit_text.getText().toString().equals("")){
            flag = true;
        }
        if(teacher_sign_up_password_edit_text.getText().toString().equals("")){
            flag = true;
        }
        if(teacher_sign_up_confirm_password_edit_text.getText().equals("")){
            flag = true;
        }

        if(flag){
            Toast.makeText(getApplicationContext(), "所有信息不可以为空", Toast.LENGTH_SHORT).show();
            return flag;
        }
        else{
            return flag;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SIGN_UP_SUCCESS:
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case USER_ALREADY_EXIST:
                    Toast.makeText(getApplicationContext(), "手机或邮箱已被注册", Toast.LENGTH_SHORT).show();
                    break;
                case SIGN_UP_FAILED:
                    //TODO show detail info;
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
