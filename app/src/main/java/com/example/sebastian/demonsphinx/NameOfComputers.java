package com.example.sebastian.demonsphinx;

/**
 * Klasa odpowiada za przechowywanie informacji o adresach Ip i nazwach wyszukanych komputerów w sieci.
 */

public class NameOfComputers {

    /**
     * Zmienne przechowuwujące odpowiednio adres ip oraz nazwę komputera.
     */
    private String name;
    private String ip;
    /**
     * Konstruktor z dwoma parametrami. Inicjalizuje zmienne klasy.
     */
    public NameOfComputers(String ip,String name) {
        this.ip = ip;
        this.name=name;

    }
    /**
     *
     * Metoda przpisująca zmiennej ip wartośc.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *
     * Metody pozwalajace na zwrócenie aktualnych wartości odpowiednio nazwy komputera i jego adresu ip.
     */
    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}