package com.example.aprilcal.signsystem.Dao;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
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
        wifiInfo = wifiManager.getConnectionInfo();
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
    public boolean isWifiEnabled(){
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
        //open();
        LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //TODO remove context;
            Toast.makeText(context.getApplicationContext(), "请先打开GPS", Toast.LENGTH_SHORT).show();
        } else {

        }
        wifiManager.startScan();
    }

    /**
     * return true on enabled & false on disabled;
     * @return
     */
    public boolean isWifiApEnabled() {
        try {
            Method method = wifiManager.getClass().getMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifiManager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * open wifi hotspot;
     */
    public boolean openAp(String SSID,String password,Context context) {
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "AprilCal";
        config.networkId = 1;
        config.preSharedKey = "123456789";
        config.hiddenSSID = false;
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedKeyManagement.set(6);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);

        try {
            Method method = wifiManager.getClass().getMethod("setWifiApConfiguration", config.getClass());
            Boolean rt = (Boolean)method.invoke(wifiManager, config);
        } catch (NoSuchMethodException e) {
            Log.e("wifi:", e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.e("wifi:", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.e("wifi:", e.getMessage());
        } catch (InvocationTargetException e) {
            Log.e("wifi:", e.getMessage());
        }

        try {
            Method method1 = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method1.invoke(wifiManager, null, true);
            //vThread.sleep(200);
        } catch (Exception e) {
            return false;
        }
        return true;
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

    /**
     * return a WiFiConfiguration when wifi info exists;
     * return null when wifi info does not exist;
     * @param SSID
     * @return
     */
    public WifiConfiguration isExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                Log.d("exist:",existingConfig.SSID);
                return existingConfig;
            }
        }
        return null;
    }

    /**
     * connect to a wifi by SSID;
     * return false on failed;
     * return ture on success;
     * @param SSID
     * @return
     */
    public boolean connect(String SSID){

        WifiConfiguration config = isExsits(SSID);
        if(config == null){

        }
        else{
            //TODO failed;
            connect(config);
        }
        return true;
    }

    public boolean connect(String SSID, String password){
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "\""+SSID+"\"";
        config.preSharedKey = "\""+"123456789"+"\"";
        wifiManager.addNetwork(config);
        wifiManager.saveConfiguration();
        return true;
    }

    /**
     * Connect to a wifi with configuration;
     * return true on success;
     * return failed on failed;
     * @param config
     * @return
     */
    public boolean connect(WifiConfiguration config) {
        wifiManager.disconnect();
        wifiManager.enableNetwork(config.networkId,true);
        return true;
    }

    /**
     * Return gateway ip address;
     * @return
     */
    public String getServerIP(){
        //WifiInfo wifiinfo = wifiManager.getConnectionInfo();
        //Log.d("Wifi info----->",Formatter.formatIpAddress(wifiinfo.getIpAddress()));
        //System.out.println("DHCP info gateway----->" + Formatter.formatIpAddress(dhcpInfo.gateway));
        //System.out.println("DHCP info netmask----->" + Formatter.formatIpAddress(dhcpInfo.netmask));
        //DhcpInfo中的ipAddress是一个int型的变量，通过Formatter将其转化为字符串IP地址
        //String routeIp = Formatter.formatIpAddress(dhcpInfo.gateway);
        //Log.d("", "wifi route ip：" + routeIp);
        //TODO modify this deprecated functhion;
        //wifiManager.getConnectionInfo();
        if(wifiManager.getDhcpInfo()==null){
            Log.d("dhcpinfo:","null");
        }

        Log.d("gateway int:",String.valueOf(wifiManager.getDhcpInfo().gateway));
        while (wifiManager.getConnectionInfo().getIpAddress() == 0) {
            Log.d("waiting", "waiting for valid ip");
        }
        Log.d("ip:",Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress));
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    //TODO remove this function;
    public ArrayList<String> getConnectedIP() {
        ArrayList<String> connectedIP = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                // Splitted by more than one space;
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4) {
                    String ip = splitted[0];
                    connectedIP.add(ip);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("ip:",String.valueOf(connectedIP.size()));
        return connectedIP;
    }

    public String getMacAddress(){
        String address = null;
        // 把当前机器上的访问网络接口的存入 Enumeration集合中
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.d("TEST_BUG", " interfaceName = " + interfaces );
        while (interfaces.hasMoreElements()) {
            NetworkInterface netWork = interfaces.nextElement();
            // 如果存在硬件地址并可以使用给定的当前权限访问，则返回该硬件地址（通常是 MAC）。
            byte[] by = new byte[0];
            try {
                by = netWork.getHardwareAddress();
                if (by == null || by.length == 0) {
                    continue;
                }
                StringBuilder builder = new StringBuilder();
                for (byte b : by) {
                    builder.append(String.format("%02X:", b));
                }
                if (builder.length() > 0) {
                    builder.deleteCharAt(builder.length() - 1);
                }
                String mac = builder.toString();
                Log.d("TEST_BUG", "interfaceName="+netWork.getName()+", mac="+mac);
                // 从路由器上在线设备的MAC地址列表，可以印证设备Wifi的 name 是 wlan0
                if (netWork.getName().equals("wlan0")) {
                    Log.d("TEST_BUG", " interfaceName ="+netWork.getName()+", mac="+mac);
                    address = mac;
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        return address;
    }
}