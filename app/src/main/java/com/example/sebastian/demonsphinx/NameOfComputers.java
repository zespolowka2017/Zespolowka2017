package com.example.sebastian.demonsphinx;

/**
 * Created by Hubert Stępiński on 01.05.2017.
 */

public class NameOfComputers {
    private String ip;
    private String computerName;
    private int position;

    public NameOfComputers(){

    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {

        return position;
    }

    public NameOfComputers(String ip, String computerName) {
        this.ip = ip;
        this.computerName = computerName;
    }

    public String getIp() {
        return ip;
    }

    public String getComputerName() {
        return computerName;
    }
}
