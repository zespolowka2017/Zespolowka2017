/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serwer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Laptokodonozozaur
 */
public class Serwer {
    private final int privateKey = 42;
    public int publicKey;
    int token;
    byte[] msg;
    ServerSocket serverSock;
  
    boolean checkToken(int token){
        System.out.println(publicKey);
        System.out.println(token);
        if(token == publicKey - privateKey)return false;
        else return true;
    }
   
    void connection() throws IOException {
        Services services = new Services();
      //Ladowanie ustawien
        services.loadProperties();
        
	System.out.println("Dostępne komendy w oparciu o zapisane ustawienia:");
		for(String key : services.commandList.keySet())
		{	
			 System.out.print(key + ": ");
			 System.out.println(services.commandList.get(key));
		}
		
      int port = 30110;     
      InetAddress localhost = InetAddress.getLocalHost();
      
      InetSocketAddress isa = new InetSocketAddress(localhost.getHostAddress(), port);
      System.out.print(localhost.getHostAddress());
    
        try {
            serverSock = new ServerSocket();
       
            serverSock.bind(isa);
              
            boolean serverIsRunning = true;         
           
            while(true){
         System.out.println("Waiting");
                Socket connection = serverSock.accept();
                System.out.println("Connected");
                InputStream sockIn = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(sockIn));
                publicKey = Integer.valueOf(br.readLine());
                
                if(checkToken(Integer.valueOf(br.readLine()))){
                    System.out.println("token kude bad");
                    continue;
                }
                while(!connection.isClosed()){
                   
                   try{                      
                        if(checkToken(Integer.valueOf(br.readLine())))continue; 
                        String s = br.readLine();
                        System.out.println(s);
                        services.chooseAction(s);
                       
                   }catch(IOException ex){
                       br.close();
                       System.out.println("closed");
                       continue;
                   }catch(NumberFormatException ex){
                       br.close();
                       System.out.println("closed");
                       continue;
                   }
                 // System.out.println("br");
                    /*if(br.ready()){
                        if(checkToken(Integer.valueOf(br.readLine())))continue;                   
                        String s = br.readLine();
                        System.out.println(s);
                        services.chooseAction(s);
                    }*/
                }
                System.out.println("Disconnected");
                
                        
            }          
        }finally{
            serverSock.close();
        }
    }
    
}
