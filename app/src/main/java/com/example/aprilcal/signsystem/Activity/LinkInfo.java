package com.example.aprilcal.signsystem.Activity;

/**
 * Created by jhon on 2017/6/10.
 */

public class LinkInfo {
    public String getWifi_name() {
        return wifi_name;
    }

    public void setWifi_name(String wifi_name) {
        this.wifi_name = wifi_name;
    }

    private String wifi_name;

    public LinkInfo(String wifi_name) {
        this.wifi_name=wifi_name;
    }
}
