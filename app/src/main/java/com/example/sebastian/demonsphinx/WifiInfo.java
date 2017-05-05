package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Klasa odpowiedzialna za pobranie informacji na temat dostepnej sieci.
 * W szczegolnosci, pobranei adresu broadcast.
 */

public class WifiInfo extends Service {

    /**
     * Zmienne przechowywująca pobrany adres broadcast.
     */
    private int broadcast;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     *Konstruktor klasy w którym pobierany jest adres broadcast sieci.
          */
    public WifiInfo(Context context){

        /**
         * Utowrzenie obiektu WifiManager potzrbnego do pobrania informacji o sieci WiFi.
         */
        WifiManager wifiManager= (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo=wifiManager.getDhcpInfo();
        broadcast=(dhcpInfo.ipAddress&dhcpInfo.netmask)|~dhcpInfo.netmask;
    }

    /**
     *Metoda zwracająca zmienna broadcast.
          */
    public int getBroadcast() {
        return broadcast;
    }
}
