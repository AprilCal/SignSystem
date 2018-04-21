package com.example.aprilcal.signsystem.Activity;

/**
 * Created by jhon on 2017/6/10.
 */

public class LinkInfo {
    private String wifi_name;
    private String capabilities;

    public LinkInfo(String wifi_name,String capabilities) {
        this.wifi_name = wifi_name;
        this.capabilities = capabilities;
    }

    public String getWifi_name() {
        return wifi_name;
    }

    public void setWifi_name(String wifi_name) {
        this.wifi_name = wifi_name;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }
}
