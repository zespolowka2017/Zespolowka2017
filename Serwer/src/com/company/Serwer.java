/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *Tworzy obiekty klas odpowiedzialnych za interfejs i usługi, które świadczy serwer.
 *Następnie w pętli nasłuchuje czeka na połączenie połączeniowe. Gdy połączy się z
 *klientem zapisuje liczby odpowiedzialne za jego weryfikację. Odbierając wiadomość
 *zawierającą komendę   weryfikuje klienta a następnie jeżeli przeszedł weryfikację
 *wykonuje komendę.
 * @author Mateusz Markuszewski
 */

public class Serwer {
    private final int privateKey = 42;
    public int publicKey;
    int token;
    ServerSocket serverSock;
    int port = 30110;
    Services services;
    Gui ginterface;

    /**
     * metoda sprawdzająca czy wiadomość jest od urządzenia które zainicjowało połączenie
     * @param token zmienna wysyłana wraz z wiadomością w celu
     * @return
     */
    boolean checkToken(int token){
        System.out.println(publicKey);
        System.out.println(token);
        if(token == publicKey - privateKey)return true;
        else return false;
    }

    void loadGUI(){

        ginterface = new Gui();
        try {
            ginterface.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        services.ginterface=ginterface;
        System.out.println("Dostępne komendy w oparciu o zapisane ustawienia:");
        for (String key : services.commandList.keySet()) {
            System.out.print(key + ": ");
            System.out.println(services.commandList.get(key));
        }

        ginterface. fillSettingsView();
        ginterface.logNetInfo("Nasłuchiwanie na:\n");
        ginterface.logNetInfo("-> IP: " + serverSock.getInetAddress().toString() + "\n");
        ginterface.logNetInfo("-> Port: " + port + "\n");
        ginterface.logNetInfo("Log:\n");

    }

    /**
     * odpowiedzialna   na   nawiązanie   połączenia   i   odbieranie
     * wiadomości
     * @throws IOException
     */
    void connection() throws IOException {

        services = new Services();
        services.loadProperties();

        InetAddress localhost = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(localhost.getHostAddress(), port);
        System.out.print(localhost.getHostAddress());
    
        try {
            serverSock = new ServerSocket();
            serverSock.bind(isa);
            loadGUI();
            ginterface.logNetInfo("Czekam na komendę...\n");
            //ginterface.logNetInfo("Połączono\n");
            while(true){
                //oczekiwanie na połączenie
                Socket connection = serverSock.accept();
                InputStream sockIn = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(sockIn));
                publicKey = Integer.valueOf(br.readLine());
                
                if(!checkToken(Integer.valueOf(br.readLine()))){
                    ginterface.logNetInfo("Wykryto nieautoryzowane połączenie\n");
                    continue;
                }
                while(!connection.isClosed()){
                   //oczekiwanie na wiadomość
                   try{                      
                        if(!checkToken(Integer.valueOf(br.readLine()))){
                            ginterface.logNetInfo("Wykryto nieautoryzowane połączenie\n");
                            continue;
                        }
                        String s = br.readLine();
                        ginterface.logNetInfo("Komenda: " + s + "\n");
                        services.chooseAction(s);
                       
                   }catch(IOException ex){
                       br.close();                    
                       continue;
                   }catch(NumberFormatException ex){
                       br.close();
                       continue;
                   }
                }

                connection.close();
                        
            }          
        }finally{
            serverSock.close();
        }
    }
    
}
