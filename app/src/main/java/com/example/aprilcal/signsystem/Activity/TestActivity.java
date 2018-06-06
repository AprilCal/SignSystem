package com.example.aprilcal.signsystem.Activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.aprilcal.signsystem.R;

public class TestActivity extends AppCompatActivity {

    protected boolean statusBarCompat = true;
    //声明相关变量
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"Android", "iOS", "Python", "Html5", "Java"};
    private ArrayAdapter arrayAdapter;
    private static long DOUBLE_CLICK_TIME = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

}
