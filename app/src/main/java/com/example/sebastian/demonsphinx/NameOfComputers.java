package com.example.sebastian.demonsphinx;

/**
 * Created by Hubert Stępiński on 01.05.2017.
 */

public class NameOfComputers {

    public NameOfComputers(){

    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    private String ip;

    public String getName() {
        return name;
    }

    private String name;
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public NameOfComputers(String ip,String name) {
        this.ip = ip;
        this.name=name;

    }

    public String getIp() {
        return ip;
    }
}
