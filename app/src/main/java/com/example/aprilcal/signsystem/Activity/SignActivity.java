package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.aprilcal.signsystem.Busi.ElectiveBusi;
import com.example.aprilcal.signsystem.Busi.SignBusi;
import com.example.aprilcal.signsystem.Busi.SignInBusi;
import com.example.aprilcal.signsystem.Dao.WiFiHelper;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.Server.ServerLintenThread;
import com.example.aprilcal.signsystem.vo.Elective;
import com.example.aprilcal.signsystem.vo.Sign;
import com.example.aprilcal.signsystem.vo.SignIn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignActivity extends AppCompatActivity {

    // increase actual number;
    public static final int SIGN_INC = 1;
    private static int actualNumber = 0;

    private List<Elective> electiveList;
    private List<SignIn> signInList;
    private List<SignIn> absentList;

    private TextView sign_detail_total_number_text_view;
    private TextView sign_detail_actual_number_text_view;
    private TextView sign_detail_title_text_view;

    private Intent intent;
    private int signID;

    private Button start_sign_button;
    private Button end_sign_button;
    private WiFiHelper wiFiHelper;

    private ServerLintenThread serverLintenThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        intent = getIntent();
        actualNumber = 0;
        signInList = new ArrayList<SignIn>();
        absentList = new ArrayList<SignIn>();

        sign_detail_total_number_text_view = (TextView) findViewById(R.id.sign_detail_total_number_text_view);
        sign_detail_actual_number_text_view = (TextView) findViewById(R.id.sign_detail_actual_number_text_view);
        sign_detail_title_text_view = (TextView) findViewById(R.id.sign_detail_title_text_view);

        sign_detail_total_number_text_view.setText(String.valueOf(intent.getIntExtra("totalNumber",0)));
        sign_detail_actual_number_text_view.setText("0");
        sign_detail_title_text_view.setText(intent.getStringExtra("courseName"));

        wiFiHelper = new WiFiHelper(this.getApplicationContext());

        start_sign_button = (Button) findViewById(R.id.start_sign_button);
        end_sign_button = (Button) findViewById(R.id.end_sign_button);

        start_sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int courseID = intent.getIntExtra("courseID",0);
                int teacherID = intent.getIntExtra("teacherID",0);
                long signDate = new Date().getTime();
                int totalNumber = intent.getIntExtra("totalNumber",0);
                int backup = 0;
                int deleted = 0;
                signID = SignBusi.sign(getApplicationContext(),new Sign(courseID,teacherID,signDate,totalNumber,actualNumber,backup,deleted));
                serverLintenThread = new ServerLintenThread(12345,handler);
                Thread listenThread = new Thread(serverLintenThread);
                listenThread.start();
            }
        });

        end_sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wiFiHelper.closeAp();
                //TODO shut all handle thread;
                SignBusi.endSign(getApplicationContext(),signID,actualNumber);
                serverLintenThread.cancel();

                //TODO refactor
                electiveList = ElectiveBusi.getAllElectiveByCourseID(getApplicationContext(),intent.getIntExtra("courseID",0));
                for(Elective elective : electiveList){
                    boolean flag = false;
                    for(SignIn signIn : signInList){
                        if(elective.getSchoolID().equals(signIn.getSchoolID())){
                            flag = true;
                        }
                    }
                    if(!flag){
                        absentList.add(new SignIn(signID,/*studentID*/0,elective.getSchoolID(),elective.getStudentName(),0,0,0));
                    }
                }
                SignInBusi.signIn(getApplicationContext(),signInList);
                SignInBusi.signIn(getApplicationContext(),absentList);
                SignBusi.endSign(getApplicationContext(),signID,actualNumber);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SIGN_INC:
                    String schoolID = msg.getData().getString("schoolID");
                    int studentID = msg.getData().getInt("studentID");
                    String studentName = msg.getData().getString("studentName");
                    int signFlag = 1;
                    signInList.add(new SignIn(signID,studentID,schoolID,studentName,signFlag,0,0));
                    ++actualNumber;
                    sign_detail_actual_number_text_view.setText(String.valueOf(actualNumber));
                    break;
            }
        }
    };
}
