package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.aprilcal.signsystem.Busi.ElectiveBusi;
import com.example.aprilcal.signsystem.Busi.SignInBusi;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Course;
import com.example.aprilcal.signsystem.vo.Elective;
import com.example.aprilcal.signsystem.vo.Sign;
import com.example.aprilcal.signsystem.vo.SignIn;

import java.util.List;

public class SignDetailActivity extends AppCompatActivity {

    private TextView textView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_detail);

        intent = getIntent();

        textView = (TextView) findViewById(R.id.test_text_view);

        Log.d("sign detail signID",String.valueOf(intent.getIntExtra("signID",0)));

        List<SignIn> absentList = SignInBusi.getAllAbsentStudentBySignID(getApplicationContext(), intent.getIntExtra("signID",0));
        List<SignIn> signInList = SignInBusi.getAllPresentStudentBySignID(getApplicationContext(),intent.getIntExtra("signID",0));
        textView.append("\n");
        for(SignIn signIn : absentList){
            textView.append("\n"+signIn.getSchoolID()+" "+signIn.getStudentName());
        }
        textView.append("\n\n以下是到场人员名单：");
        textView.append("\n");
        for(SignIn signIn : signInList){
            textView.append("\n"+signIn.getSchoolID()+" "+signIn.getStudentName());
        }
    }
}
