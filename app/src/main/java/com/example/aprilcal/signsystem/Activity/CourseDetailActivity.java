package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aprilcal.signsystem.R;

public class CourseDetailActivity extends AppCompatActivity {

    private ImageView del_image1;
    private ImageView del_image2;
    private ImageView del_image3;
    private Button start_sign_button;
    private TextView textView11;
    private Menu menu;

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
