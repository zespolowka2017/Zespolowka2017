package com.example.sebastian.demonsphinx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import android.util.Log;

/*unikalne dla kazdego polaczenia*/
/*przechowuje informacje na temat po�aczenia*/


public class Connection{
	
	OutputStream sockOut;
	InputStream  sockIn;
	Socket socket;
	PrintStream ps;
	
	int privateKey = 42;
	int publicKey;
	int token;
	String ip;
	
	public Connection(String ip){
		this.ip=ip;
		new ConnectionTask(this).execute();
		Log.d("pk",Integer.toString(publicKey));
		Log.d("t",Integer.toString(token));
	}
						
	void disconnect() {			
			try{			
				ps.close();			
				socket.close(); 			
				sockOut.close();
				sockIn.close();				
				Log.d("debug","zamkniete");
			}catch (SocketException exc) {
				Log.d("err","zamykanie gniazda");
				exc.printStackTrace();
			}catch (IOException exc) {
				Log.d("err","zamykanie gniazda");
				exc.printStackTrace();
			}catch (NullPointerException exc) {
				Log.d("err","zamykanie gniazda");
				exc.printStackTrace();
			}
	}
	
	void send(String msg) {			
	
			ps.println(token);
			ps.println(msg);			
			Log.d("debug","wys�ane");
	}
	
	
}
