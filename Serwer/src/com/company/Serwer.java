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
 *
 * @author Laptokodonozozaur
 */
public class Serwer {
    private final int privateKey = 42;
    public int publicKey;
    int token;
    ServerSocket serverSock;
    int port = 30110;
    Services services;
    Gui ginterface;

    boolean checkToken(int token){
        System.out.println(publicKey);
        System.out.println(token);
        if(token == publicKey - privateKey)return false;
        else return true;
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

//        wywołaj f
        ginterface. fillSettingsView();


        ginterface.logNetInfo("Nasłuchiwanie na:\n");
        ginterface.logNetInfo("-> IP: " + serverSock.getInetAddress().toString() + "\n");
        ginterface.logNetInfo("-> Port: " + port + "\n");
        ginterface.logNetInfo("Log:\n");


    }
   
    void connection() throws IOException {

        services = new Services();
        services.loadProperties();

      InetAddress localhost = InetAddress.getLocalHost();
      
      InetSocketAddress isa = new InetSocketAddress(localhost.getHostAddress(), port);
      System.out.print(localhost.getHostAddress());
    
        try {
            serverSock = new ServerSocket();
            serverSock.bind(isa);
            boolean serverIsRunning = true;
            loadGUI();
           
            while(true){
                ginterface.logNetInfo("Czekam na połączenie...");
                Socket connection = serverSock.accept();
                ginterface.logNetInfo("Połączono\n");
                InputStream sockIn = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(sockIn));
                publicKey = Integer.valueOf(br.readLine());
                
                if(checkToken(Integer.valueOf(br.readLine()))){
                    ginterface.logNetInfo("Wykryto nieautoryzowane połączenie\n");
                    continue;
                }
                while(!connection.isClosed()){
                   
                   try{                      
                        if(checkToken(Integer.valueOf(br.readLine()))){
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
                ginterface.logNetInfo("Połączenie zostało zerwane\n");
                connection.close();
                        
            }          
        }finally{
            serverSock.close();
        }
    }
    
}
