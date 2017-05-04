package com.example.sebastian.demonsphinx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;

public class DatagramSender extends AsyncTask<Integer,Void,Map<String,String>>{
/*objekt dostaje adress broadcast i wydala liste serwerow nasluchujacych w sieci*/

	@Override
	protected Map<String,String> doInBackground(Integer... broadcast){
		DatagramSocket socket = null;
		DatagramPacket packet;
		String s;
		Map<String,String> ipAdresses = new HashMap<String,String>();
		try {
			socket = new DatagramSocket();
			socket.setBroadcast(true);			
			byte[] msg = new byte[32];		
			InetAddress broadcastAddr = InetAddress.getByName(Formatter.formatIpAddress(broadcast[0]));
			  			
			packet = new DatagramPacket(msg, msg.length, broadcastAddr, 30111);  
			socket.send(packet);  
			socket.setSoTimeout(5000);
			
			while(true){			
				socket.receive(packet);		
				
				//System.out.println(new String(packet.getData(), 0, packet.getLength()));
				ipAdresses.put(packet.getAddress().toString(),new String(packet.getData(), 0, packet.getLength()));
			}			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			socket.close();
			return ipAdresses;
		}
		
		
				
	}

}
