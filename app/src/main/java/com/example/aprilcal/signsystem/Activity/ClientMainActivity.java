package com.example.aprilcal.signsystem.Activity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.aprilcal.signsystem.R;

import java.util.ArrayList;
import java.util.List;

public class ClientMainActivity extends AppCompatActivity {
    public static List<LinkInfo> linkInfos = new ArrayList<LinkInfo>();
    private Switch wifi_switch;
    private ListView wifi_listView;
    private Button sign_button;
    private Button info_button;
    private Button history_sign_button;
    private InfoItemAdapter infoItemAdapter;

    //private ListView lv_getWifiList;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        sign_button = (Button) findViewById(R.id.sign_button);
        info_button = (Button) findViewById(R.id.info_button);
        history_sign_button = (Button) findViewById(R.id.history_sign_button);

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "签到成功", Toast.LENGTH_SHORT).show();
            }
        });

        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.BindInfoActivity");
                startActivity(in);
            }
        });

        history_sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "请稍后", Toast.LENGTH_SHORT).show();
            }
        });

        wifi_switch = (Switch) findViewById(R.id.wifi_switch);
        wifi_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifi_switch.isChecked()) {
                    wifi_listView.setVisibility(View.VISIBLE);
                } else {
                    wifi_listView.setVisibility(View.INVISIBLE);
                }
            }
        });

        infoItemAdapter = new InfoItemAdapter(ClientMainActivity.this,R.layout.listitem, linkInfos);
        wifi_listView = (ListView) findViewById(R.id.wifi_listview);
        wifi_listView.setAdapter(infoItemAdapter);

        linkInfos.add(new LinkInfo("CMCC-EDU"));
        linkInfos.add(new LinkInfo("FAST-750E"));
        linkInfos.add(new LinkInfo("数据结构"));
        linkInfos.add(new LinkInfo("620"));
        linkInfos.add(new LinkInfo("738"));
        linkInfos.add(new LinkInfo("sumsung"));
        linkInfos.add(new LinkInfo("xiaomi"));
        linkInfos.add(new LinkInfo("xixi"));
    }
}