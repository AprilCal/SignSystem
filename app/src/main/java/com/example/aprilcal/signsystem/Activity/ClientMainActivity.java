package com.example.aprilcal.signsystem.Activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aprilcal.signsystem.Adaper.InfoItemAdapter;
import com.example.aprilcal.signsystem.Client.ConnectThread;
import com.example.aprilcal.signsystem.Dao.WiFiHelper;
import com.example.aprilcal.signsystem.R;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientMainActivity extends AppCompatActivity {
    public static List<LinkInfo> linkInfos = new ArrayList<LinkInfo>();
    private Switch wifi_switch;
    private ListView wifi_list_view;
    private TextView student_exit_text_view;
    private TextView student_bind_info_text_view;

    private Button sign_button;
    private InfoItemAdapter infoItemAdapter;

    private WiFiHelper wiFiHelper;
    private WiFiReceiver receiver = new WiFiReceiver();

    class  WiFiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            List<ScanResult> wifilist = wiFiHelper.getScanResults();
            linkInfos.clear();
            for(ScanResult i : wifilist){
                linkInfos.add(new LinkInfo(i.SSID,i.capabilities));
                Log.d(i.SSID.toString(),i.capabilities.toString());
            }
            infoItemAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        wiFiHelper = new WiFiHelper(this.getApplicationContext());
        //register receiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(receiver, intentFilter);

        sign_button = (Button) findViewById(R.id.sign_button);
        student_exit_text_view = (TextView)findViewById(R.id.student_exit_text_view);
        student_bind_info_text_view = (TextView)findViewById(R.id.student_bind_info_text_view);

        student_exit_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("identity", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                //TODO remove all keys;
                edit.remove("identity");
                edit.commit();

                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.MainActivity");
                startActivity(in);
                finish();
            }
        });

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String serverIP = wiFiHelper.getServerIP();
                            Socket socket = new Socket(serverIP, 12345);
                            SharedPreferences sp = getSharedPreferences("identity", MODE_PRIVATE);
                            String schoolID = sp.getString("schoolID","");
                            String studentID = String.valueOf(sp.getInt("studetnID",0));
                            String studetnName = sp.getString("studentName","");
                            String signInfo =  studentID+","+schoolID+","+studetnName;
                            ConnectThread connectThread = new ConnectThread(socket, signInfo,getApplicationContext());
                            connectThread.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        student_bind_info_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClassName(getApplicationContext(), "com.example.aprilcal.signsystem.Activity.BindInfoActivity");
                startActivity(in);
            }
        });

        wifi_switch = (Switch) findViewById(R.id.wifi_switch);
        wifi_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifi_switch.isChecked()) {
                    wiFiHelper.scanWiFi();
                    wifi_list_view.setVisibility(View.VISIBLE);
                } else {
                    wifi_list_view.setVisibility(View.INVISIBLE);
                }
            }
        });

        infoItemAdapter = new InfoItemAdapter(ClientMainActivity.this,R.layout.listitem, linkInfos);
        wifi_list_view = (ListView) findViewById(R.id.wifi_list_view);
        wifi_list_view.setAdapter(infoItemAdapter);
        TextView headText1 = new TextView(this);
        headText1.setText("附近的wifi");
        wifi_list_view.addHeaderView(headText1);

        wifi_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WifiConfiguration config = wiFiHelper.isExsits(linkInfos.get((int) id).getWifi_name());
                Log.d(linkInfos.get((int)id).getWifi_name(), linkInfos.get((int)id).getCapabilities());
                if(config == null){
                    //TODO refactor dialog;
                    String password = showInputDialog();
                    wiFiHelper.connect(linkInfos.get((int) id).getWifi_name(),password);
                }
                else
                {
                    //TODO abstract a sign operation;
                    if(wiFiHelper.connect(config)){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String serverIP = wiFiHelper.getServerIP();
                                    Socket socket = new Socket(serverIP, 12345);
                                    ConnectThread connectThread = new ConnectThread(socket,"i am student",getApplicationContext());
                                    connectThread.start();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private String showInputDialog() {
        final EditText editText = new EditText(ClientMainActivity.this);
        editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(ClientMainActivity.this);
        inputDialog.setTitle("输入wifi密码").setView(editText);
        inputDialog.setPositiveButton("签到", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
        return editText.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}