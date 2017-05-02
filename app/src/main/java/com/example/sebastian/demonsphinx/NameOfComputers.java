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
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public NameOfComputers(String ip) {
        this.ip = ip;

    }

    public String getIp() {
        return ip;
    }
}
