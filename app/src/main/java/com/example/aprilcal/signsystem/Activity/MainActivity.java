package com.example.aprilcal.signsystem.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Dao.WiFiHelper;
import com.example.aprilcal.signsystem.Dialog.DialogUtils;
import com.example.aprilcal.signsystem.Network.NetworkHelper;
import com.example.aprilcal.signsystem.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;

public class MainActivity extends AppCompatActivity {

    public static final int STUDENT_LOGIN_SUCCESS = 1;
    public static final int TEACHER_LOGIN_SUCCESS = 2;
    public static final int LOGIN_FAILED = 3;
    public static final int NETWORK_UNREACHABLE = 4;
    public static final int CONNECTION_REFUSED = 5;
    public static final int UNKONWN_ERROR = 6;
    public static final int MAC_ERROR = 7;

    private static final String sharedPreferenceName = "identity";

    private Button teacher_sign_up_button;
    private Button signin_button;
    private Button signup_button;

    private EditText count_text;
    private EditText password_text;

    private RadioButton student_radiobutton;
    private RadioButton teacher_radiobutton;

    private Dialog mDialog;
    private Dialog blackDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin_button = (Button) findViewById(R.id.SignInButton);
        signup_button = (Button) findViewById(R.id.SignUpButton);
        teacher_sign_up_button = (Button) findViewById(R.id.main_teacher_sign_up_button);

        student_radiobutton = (RadioButton) findViewById(R.id.student_radiobutton);
        teacher_radiobutton = (RadioButton) findViewById(R.id.teacher_radiobutton);

        count_text = (EditText) findViewById(R.id.CountText);
        password_text = (EditText) findViewById(R.id.PasswordText);

        //TODO remove
        count_text.setText("15527358829");
        password_text.setText("123456");

        ifLoged();

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teacher_radiobutton.isChecked()) {
                    NetworkHelper.TeacherLoginRequest(getApplicationContext(), handler, count_text.getText().toString(), password_text.getText().toString());
                    blackDialog = DialogUtils.createLoadingDialog(MainActivity.this, "登录中...");
                    mHandler.sendEmptyMessageDelayed(1, 6000);
                } else {
                    String mac = new WiFiHelper(getApplicationContext()).getMacAddress();
                    NetworkHelper.StudentLoginRequest(getApplicationContext(), handler, count_text.getText().toString(), password_text.getText().toString(),mac);
                    blackDialog = DialogUtils.createLoadingDialog(MainActivity.this, "登录中...");
                    mHandler.sendEmptyMessageDelayed(1, 6000);
                }
            }
        });

        student_radiobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher_radiobutton.setChecked(false);
            }
        });
        teacher_radiobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_radiobutton.setChecked(false);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.SignUpActivity");
                startActivity(in);
            }
        });

        teacher_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.TeacherSignUpActivity");
                startActivity(in);
            }
        });
    }

    private void studentLoginSuccess(Message msg) {
        SharedPreferences sp = getSharedPreferences("identity", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        //get data
        int studentID = msg.getData().getInt("studentID");
        String schoolID = msg.getData().getString("schoolID");
        String studentName = msg.getData().getString("studentName");
        String tel = msg.getData().getString("tel");
        String mail = msg.getData().getString("mail");
        String school = msg.getData().getString("school");
        String token = msg.getData().getString("token");

        //set data
        edit.putString("identity", "student");
        edit.putInt("studentID", studentID);
        edit.putString("schoolID", schoolID);
        edit.putString("studentName", studentName);
        edit.putString("tel", tel);
        edit.putString("mail", mail);
        edit.putString("school", school);
        edit.putString("token", token);
        Log.d("token",token);
        edit.commit();

        DialogUtils.closeDialog(blackDialog);
        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
        Intent in = new Intent();
        in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ClientMainActivity");
        startActivity(in);
        finish();
    }

    private void teacherLoginSuccess(Message msg) {
        SharedPreferences sp = getSharedPreferences("identity", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        //get data
        int teacherID = msg.getData().getInt("teacherID");
        String teacherName = msg.getData().getString("teacherName");
        String teacher_tel = msg.getData().getString("tel");
        String teacher_mail = msg.getData().getString("mail");
        String teacher_school = msg.getData().getString("school");
        String token = msg.getData().getString("token");
        //set data
        edit.putString("identity", "teacher");
        edit.putInt("teacherID", teacherID);
        edit.putString("teacherName", teacherName);
        edit.putString("tel", teacher_tel);
        edit.putString("mail", teacher_mail);
        edit.putString("school", teacher_school);
        edit.putString("token", token);
        edit.commit();

        DialogUtils.closeDialog(blackDialog);
        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
        Intent in = new Intent();
        in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ServerMainActivity");
        startActivity(in);
        finish();
    }

    //TODO remove
    private void loginFailed() {

    }

    //TODO remove
    private void macError(){
        Toast.makeText(getApplicationContext(), "检测到更换设备，请注销账号后重新注册", Toast.LENGTH_SHORT).show();
    }

    /**
     * Check if user has signed up before;
     * If identity equals null, do nothing;
     * If identity equals student, jump to student mainpage;
     * If identity equals teacher, jump to teacher mainpage;
     */
    private void ifLoged() {
        SharedPreferences sp = getSharedPreferences(sharedPreferenceName, MODE_PRIVATE);
        String identity = sp.getString("identity", "");
        if (identity.equals("teacher")) {
            Intent in = new Intent();
            in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ServerMainActivity");
            startActivity(in);
            finish();
        } else if (identity.equals("student")) {
            Intent in = new Intent();
            in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.ClientMainActivity");
            startActivity(in);
            finish();
        } else {
            return;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STUDENT_LOGIN_SUCCESS:
                    studentLoginSuccess(msg);
                    break;
                case TEACHER_LOGIN_SUCCESS:
                    teacherLoginSuccess(msg);
                    break;
                case LOGIN_FAILED:
                    loginFailed();
                    break;
                case MAC_ERROR:
                    //macError();
                    DialogUtils.closeDialog(blackDialog);
                    Toast.makeText(getApplicationContext(), "检测到更换设备，请注销账号后重新注册", Toast.LENGTH_SHORT).show();
                    break;
                case NETWORK_UNREACHABLE:
                    DialogUtils.closeDialog(blackDialog);
                    Toast.makeText(getApplicationContext(), "无网络", Toast.LENGTH_SHORT).show();
                    break;
                case CONNECTION_REFUSED:
                    DialogUtils.closeDialog(blackDialog);
                    Toast.makeText(getApplicationContext(), "登录超时，请稍后重试", Toast.LENGTH_SHORT).show();
                    break;
                case UNKONWN_ERROR:
                    DialogUtils.closeDialog(blackDialog);
                    Toast.makeText(getApplicationContext(), "未知错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogUtils.closeDialog(blackDialog);
                    break;
            }
        }
    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}