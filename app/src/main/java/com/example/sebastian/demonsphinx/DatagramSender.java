package com.example.sebastian.demonsphinx;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;

public class DatagramSender extends AsyncTask<Integer,Void,List<String>>{
/*objekt dostaje adress broadcast i wydala liste serwerow nasluchujacych w sieci*/

	@Override
	protected List<String> doInBackground(Integer... broadcast){
		DatagramSocket socket = null;
		DatagramPacket packet;
		String s;
		List<String> ipAdresses = new ArrayList<String>();
		try {
			socket = new DatagramSocket();
			socket.setBroadcast(true);			
			byte[] msg = new byte[1];		
			InetAddress broadcastAddr = InetAddress.getByName(Formatter.formatIpAddress(broadcast[0]));
			  			
			packet = new DatagramPacket(msg, msg.length, broadcastAddr, 30111);  
			socket.send(packet);  
			socket.setSoTimeout(5000);
			
			while(true){			
				socket.receive(packet);		
				s=packet.getAddress().toString();
				Log.d("ip", s);	
				ipAdresses.add(s);
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
