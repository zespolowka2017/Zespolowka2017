package com.example.sebastian.demonsphinx;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import android.os.AsyncTask;
import android.util.Log;

/*laczenie z serverem*/
/*generuje tokeny*/
/*tworzone w Connection przekazuje informacje na temat polaczenia*/

public class ConnectionTask extends AsyncTask<Void, Void, Void>{
	Connection connection;
	
	
	
	public ConnectionTask(Connection connect) {
        connection = connect;
        Random generator = new Random();	 
        connection.publicKey = generator.nextInt(20000);
        connection.token = connection.publicKey - connection.privateKey ;                
    }
	
    @Override
    protected void onPreExecute() {
        //okno dialogowe 
    	//podczas ��czenia si� z serwerem mo�na doda� okno
    	//tu Activity.ShowDialog...
    }


	@Override
	protected Void doInBackground(Void... params) {
		
			try{
				connection.socket = new Socket(connection.ip,30110);							
				PrintStream ps = new PrintStream(connection.sockOut = connection.socket.getOutputStream(), true);
				connection.ps = ps;
				connection.sockIn = connection.socket.getInputStream();
				Log.d("debug","jestem");
				ps.println(connection.publicKey);
				ps.println(connection.token);
				
				
				
			}catch (UnknownHostException exc) {
				Log.d("errA",exc.toString());		
				exc.printStackTrace();
			}catch (SocketException exc) {
				Log.d("errA",exc.toString());	
				exc.printStackTrace();
			}catch (IOException exc) {
				Log.d("errA",exc.toString());
				exc.printStackTrace();
			}
		
		return null;
	}
	@Override
    protected void onPostExecute(Void result) {
       //tu zamykanie okna �adowania po po��czeniu jesli w onpre cos robione
    }



}
