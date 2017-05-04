package com.example.sebastian.demonsphinx;

/**
 * Created by Hubert Stępiński on 01.05.2017.
 */

public class NameOfComputers {
    private String name;
    private String ip;

    public NameOfComputers(String ip,String name) {
        this.ip = ip;
        this.name=name;

    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}