package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import com.example.aprilcal.signsystem.Busi.SignBusi;
import com.example.aprilcal.signsystem.Client.ConnectThread;
import com.example.aprilcal.signsystem.R;
import com.example.aprilcal.signsystem.vo.Sign;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.example.aprilcal.signsystem.R.id.course_list_view;

public class CourseDetailActivity extends AppCompatActivity {

    private ImageView del_image1;
    private ImageView del_image2;
    private ImageView del_image3;
    private Button start_sign_button;
    private TextView textView11;
    private Menu menu;
    private ListView sign_list_view;
    private ScrollView sign_scroll_view;
    private LinearLayout sign_scroll_linear_layout;
    private LinearLayout sign_linear_layout;
    private SignItemAdaper signItemAdaper;
    private static List<Sign> signList = new ArrayList<Sign>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        del_image1 = (ImageView)findViewById(R.id.del_image1);
        del_image2 = (ImageView)findViewById(R.id.del_image2);
        del_image3 = (ImageView)findViewById(R.id.del_image3);
        start_sign_button = (Button)findViewById(R.id.start_sign_button);
        textView11 = (TextView)findViewById(R.id.textView11);

        del_image1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        del_image2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        del_image3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        textView11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.SignDetailActivity");
                startActivity(in);
            }
        });

        start_sign_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.SignActivity");
                startActivity(in);
            }
        });
        SignBusi.sign(getApplicationContext(),new Sign(1,2,3,10000,98,97,0,0));
        SignBusi.sign(getApplicationContext(),new Sign(1,2,3,10001,98,97,0,0));
        SignBusi.sign(getApplicationContext(),new Sign(1,2,3,10002,98,97,0,0));
        signList = SignBusi.getAllSignByCourseID(getApplicationContext(),2);
        signItemAdaper = new SignItemAdaper(CourseDetailActivity.this,R.layout.sign_list_item,signList);
        sign_list_view = (ListView) findViewById(R.id.sign_list_view);
        sign_list_view .setAdapter(signItemAdaper);
        Toast.makeText(getApplicationContext(), String.valueOf(signList.size()), Toast.LENGTH_SHORT).show();

        //TODO remove this listener;
        sign_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("selected id",String.valueOf(signList.get((int)id).getSignID()));

            }
        });


        //sign_linear_layout = (LinearLayout)findViewById(R.id.sign_linear_layout);
        //sign_linear_layout.addView((LinearLayout)findViewById(R.id.sign_item_linear_layout));

        /*
        sign_scroll_view = (ScrollView) findViewById(R.id.sign_scroll_view);
        sign_scroll_linear_layout = (LinearLayout) findViewById(R.id.sign_scroll_linear_layout);
        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText("stupid");
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        sign_scroll_linear_layout.addView(textView);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Initialize menu item. */
        getMenuInflater().inflate(R.menu.function_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(getApplicationContext(),"同步成功",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.fresh_item:
                Toast.makeText(getApplicationContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.del_item:
                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                finish();
            default:
                return false;
        }
    }
}
