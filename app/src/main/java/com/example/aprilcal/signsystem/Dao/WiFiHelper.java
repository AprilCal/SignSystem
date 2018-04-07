package com.example.aprilcal.signsystem.Dao;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by AprilCal on 2018/4/2.
 */

public class WiFiHelper {
    private WifiManager wifiManager;
    private List<ScanResult> scanResults;
    private Context context;

    private List<String> wifiList;
    private WifiInfo wifiInfo;

    public WiFiHelper(Context context){
        wifiManager = (WifiManager)context.getSystemService(WIFI_SERVICE);
        this.context = context;
    }

    /**
     * open wifi.
     */
    public void open(){
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
    }

    /**
     * close wifi.
     */
    public void close(){
        if(wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(false);
        }
    }

    /**
     * check if Ap is open;
     * @return
     */
    public boolean isWifiApEnabled(){
        return wifiManager.isWifiEnabled();
    }

    /**
     * get wifi scan result;
     * @return
     */
    public List<ScanResult> getScanResults(){
        scanResults = wifiManager.getScanResults();
        return scanResults;
    }

    /**
     * scan wifi list;
     * @return
     */
    public void scanWiFi(){
        open();
        LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context.getApplicationContext(), "请先打开GPS", Toast.LENGTH_SHORT).show();
        } else {

        }
        wifiManager.startScan();
    }

    /**
     * open wifi hotspot;
     */
    public boolean openAp(String SSID,String password,Context context) {
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "AprilCal";
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.preSharedKey = "123456789";
        //config.hiddenSSID = true;
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);//开放系统认证
        //config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        //config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        //config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        //config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.status = WifiConfiguration.Status.ENABLED;
        //通过反射调用设置热点
        boolean enable = false;
        try {
            Method method = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            enable = (Boolean) method.invoke(wifiManager, config, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enable;
    }

    /**
     * close hot spot;
     */
    public void closeAp(){
        if(isWifiApEnabled()){
            try{
                Method method = wifiManager.getClass().getMethod("getWifiApConfiguration");
                method.setAccessible(true);
                WifiConfiguration config = (WifiConfiguration) method.invoke(wifiManager);
                Method method2 = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
                method2.invoke(wifiManager, config, false);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public int getConnectCount() {
        try {
            Method method = wifiManager.getClass().getMethod("getWifiApState");
            List<Object> list1 = (List<Object>) method.invoke(wifiManager);
            if(list1.size()==0){
                Toast.makeText(context.getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
            }
            else{
                Integer i = list1.size();
                Toast.makeText(context.getApplicationContext(), i.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}