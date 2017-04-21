package com.example.sebastian.demonsphinx;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

//import android.media.audiofx.BassBoost.Settings;


public class New_Intent extends Activity {
	private String command;
	private int brightness;
	private ContentResolver cResolver;
	private Window window;
    private String[] order = new String[7];
    private int percentagePower;
    private int percentageBrightnessMore;
    private int percentageBrightnessLess;
    private String player;
    private String value;
    private int value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings",MODE_PRIVATE);

       // Tutaj bedzie odczyt ustawien
        order[0] = "zadzwoń";//sharedPreferences.getString("Call", " ").toLowerCase();

        order[1] = sharedPreferences.getString("BrightnesPlus", " ").toLowerCase();
        //ile procent jasnosci ekranu proponuj�, �eby na sztywno by�o narzucone,�e wielokrotno�� 5
        percentageBrightnessMore = sharedPreferences.getInt("BrightnesPlus1", 1);

        order[2] = sharedPreferences.getString("BrightnesMinus", " ").toLowerCase();
        percentageBrightnessLess = sharedPreferences.getInt("BrightnesMinus1",1  ); //ile procent jasnosci ekranu proponuj�, �eby na sztywno by�o narzucone,�e wielokrotno�� 5

        order[3] = sharedPreferences.getString("VolumeMinus"," "  ).toLowerCase();

        order[4] = sharedPreferences.getString("VolumePlus"," "  ).toLowerCase();

        order[5] = sharedPreferences.getString("Aparat"," "  ).toLowerCase();

        order[6] = sharedPreferences.getString("Browser"," "  ).toLowerCase();

        player = sharedPreferences.getString("Multimedia"," "  ).toLowerCase();
        percentagePower = sharedPreferences.getInt("Multimedia1",1  );

        Bundle b = getIntent().getExtras();
        command = b.getString("Polecenie");

        execute();
    }

    private void execute() {
        command = command.toLowerCase();

        String[] tab = command.split(" ");

        if(tab[0].equals(order[0])) {
            call(tab);
        }
        else if(tab[0].equals(order[1])){
            moreClearly(percentageBrightnessMore);
        }
        else if(tab[0].equals(order[2])) {
            dark(percentageBrightnessLess);
        }
        else if(tab[0].equals(order[3])) {
            quieter();
        }
        else if(tab[0].equals(order[4])) {
            louder();
        }
        else if(tab[0].equals(player) && tab[1].equals(order[4])) {
            playerLouder(percentagePower);
        }
        else if(tab[0].equals(player) && tab[1].equals(order[3])) {
            playerQuieter(percentagePower);
        }
        else if(tab[0].equals(order[5])) {
            foto();
        }
        else if(tab[0].equals(order[6])) {
            browser(tab);
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.new__intent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
        return super.onOptionsItemSelected(item);
    }

	private void call(String[] tab) {
		if (tab.length > 1) {
			String connection = tab[1];
			for (int i = 2; i < tab.length; i++) {
				connection = connection + " " + tab[i];
			}
			String number;
			ContentResolver contentResolver = this.getContentResolver();
			ArrayList<Kontakty> listaKontaktow = Kontakty.listaWszystkichKontaktow(contentResolver);
			for (Kontakty kontakt : listaKontaktow) {
				String test2 = kontakt.nazwaWyswietlana;

                String test3 = test2.toLowerCase();

                Log.d("@@@@@@@@@@KONTAKT", test3);

                if (connection.equals(test3)) {
					number = kontakt.telefonKomorkowy;
					Uri nr = Uri.parse("tel:" + number);
					Intent intencja = new Intent(Intent.ACTION_CALL, nr);
					intencja.setData(Uri.parse("tel:" + number));
					if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
						// TODO: Consider calling
						//    ActivityCompat#requestPermissions
						// here to request the missing permissions, and then overriding
						//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
						//                                          int[] grantResults)
						// to handle the case where the user grants the permission. See the documentation
						// for ActivityCompat#requestPermissions for more details.
						return;
					}

					// poczekac na zakonczenie intencji ale zakonczenie nie minimalizacje
					startActivity(intencja);
				}
			}
		} else System.out.println("Nie podales kontaktu");
	}

	private void moreClearly(int percentageBrightness){
		cResolver = getContentResolver(); 
        window = getWindow(); 
        try{
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);//Pobiera jasnosc ekranu
        } 
        catch (SettingNotFoundException e) {
        	Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }
        int add2 = (percentageBrightness/5) * 12;
        if(brightness+add2 < 0)
            brightness = 0;
        else if(brightness+add2 > 255)
            brightness = 255 - add2;
        
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness+ add2);
        WindowManager.LayoutParams layoutpars = window.getAttributes();
        layoutpars.screenBrightness = brightness / (float)255 + add2 / (float)255;//(more*percentageBrightness) /(float)255 ;
        window.setAttributes(layoutpars);
	}

	private void dark(int percentageBrightness){
		cResolver = getContentResolver();
        window = getWindow();
        try{
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);//Pobiera jasnosc ekranu
        }
        catch (SettingNotFoundException e) {
        	Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }
        int reduce2 = (percentageBrightness/5) * 12;
        if(brightness - reduce2 < 0)
            brightness = 0;
        else if(brightness > 255)
            brightness = 255  ;
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness-reduce2);
        WindowManager.LayoutParams layoutparsII = window.getAttributes();
        layoutparsII.screenBrightness = brightness / (float)255 - reduce2/(float)255 ;
        window.setAttributes(layoutparsII);
	}

	private void quieter(){
		AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		switch (am.getRingerMode()) {
	    case AudioManager.RINGER_MODE_SILENT:
	        break;
	    case AudioManager.RINGER_MODE_VIBRATE:
	    	am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	        break;
	    case AudioManager.RINGER_MODE_NORMAL:
	      	am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	        break;
		}
	}
	private void louder(){
		AudioManager amII = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		switch (amII.getRingerMode()) {
	    case AudioManager.RINGER_MODE_SILENT:
	    	amII.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	        break;
	    case AudioManager.RINGER_MODE_VIBRATE:
	    	amII.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	        break;
	    case AudioManager.RINGER_MODE_NORMAL:
	        break;
		}
	}

	private void playerLouder(int percentagePower){
		AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int add =  (maxVolume * percentagePower) / 100;
		int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
		if(currentVolume <= maxVolume){
			currentVolume = currentVolume + add;
		}
		audio.setStreamVolume(AudioManager.STREAM_MUSIC,currentVolume,0);
	}

	private void playerQuieter(int percentagePower){
		AudioManager audio2 = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int maxVolume2 = audio2.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int reduce = (maxVolume2 * percentagePower) / 100;
		int currentVolume2 = audio2.getStreamVolume(AudioManager.STREAM_MUSIC);
		if(currentVolume2 > 0){
			currentVolume2 = currentVolume2 - reduce;
		}
			audio2.setStreamVolume(AudioManager.STREAM_MUSIC,currentVolume2,0);
	}

	private void foto(){
		Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(foto, 1);
	}

	private void browser(String[] tab){
        Uri uri = Uri.parse("http://"+tab[1]);
		Intent i = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(i);
	}
}
