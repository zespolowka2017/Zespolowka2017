package com.example.sebastian.demonsphinx;

/**
 * Created by Hubert Stępiński on 20.03.2017.
 */

public class ComputerList {
    private String name;
    private String ip;
    private String mcAdress;
    public ComputerList(String name, String ip, String mcAdress) {
        this.name = name;
        this.ip = ip;
        this.mcAdress = mcAdress;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getMcAdress() {
        return mcAdress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMcAdress(String mcAdress) {
        this.mcAdress = mcAdress;
    }
}
