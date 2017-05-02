package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by Hubert Stępiński on 01.05.2017.
 */

public class WifiInfo extends Service {


    private int broadcast;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public WifiInfo(Context context){
        WifiManager wifiManager= (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

        DhcpInfo dhcpInfo=wifiManager.getDhcpInfo();
        broadcast=(dhcpInfo.ipAddress&dhcpInfo.netmask)|~dhcpInfo.netmask;

        Log.d("br",String.valueOf(dhcpInfo.ipAddress));


    }
    public int getBroadcast() {
        return broadcast;
    }
}
