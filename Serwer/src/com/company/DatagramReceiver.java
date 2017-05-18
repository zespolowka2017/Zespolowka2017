/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;

/**
 * Tworzy   gniazdo   bezpołączeniowe   i   nasłuchuje   na
 * broadcaście. Gdy otrzyma pakiet pobiera adres ip z jakiego przybył i wysyła na ten
 * adres swoją nazwę komputera i adres ip.
 * @author Mateusz Markuszewski
 */
public class DatagramReceiver implements Runnable{
    private DatagramSocket dSocket;
    private DatagramPacket packet;
    private byte[] buffer = new byte[32];
    
    @Override
    public void run() {
        
        try {
            dSocket = new DatagramSocket(30111);
            dSocket.setBroadcast(true);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true){
            //po otrzymaniu wiadomości z broadcastu wysyła pakiet z nazwą komputera do nadawcy
            try {               
                packet = new DatagramPacket(buffer,buffer.length);         
                dSocket.receive(packet);           
                System.out.println(packet.getAddress().toString());
                buffer = InetAddress.getLocalHost().getHostName().getBytes();
                packet = new DatagramPacket(buffer,
                        buffer.length,
                        packet.getAddress(),packet.getPort());
                dSocket.send(packet);
                
            } catch (IOException ex) {
                Logger.getLogger(DatagramReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
