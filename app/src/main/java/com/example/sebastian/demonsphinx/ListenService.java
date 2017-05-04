package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Serwis działający w tle i obsługujący rozpoznawanie mowy.
 */
public class ListenService extends Service {
    /**
     * zmienna odpowiedzialna za sterowanie i rozpoznawanie słów kluczy.
     */
    private SphinxRecogniser sphinxRecogniser;

    /**
     * zmienna zapewniająca dostęp do konfiguracji.
     */
    private SharedPreferences sharedPreferences;

    /**
     * Dzięki tej zmiennej możliwe jest przesłanie komendy do serwera.
     */
    private Connection con;

    /**
     *  zmienna odpowiedzialna za komunikację między oknem rozpoznającym dalszą część komendy a usługą.
     */
    private BroadcastReceiver mMessageReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Metoda wykonywana w momencie tworzenia serwisu, odpowiedzialna za
     * inicjalizację zmiennych,
     * rejestracje broadcastu służącego do odczytania rozpoznanych komend
     * i rozpoczęcie nasłuchiwania.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Uchwyt do intentu, wywolywany za kazdym razem
        // gdy przyjdzie wiadomosc z GoogleRecogniser
        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                startProccessingCommand(intent.getStringExtra("result"));
            }
        };

        // Rejestracja Broadcastu w celu otrzymywania wiadomosci z GoogleRecogniser
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("GoogleRecogniser"));

        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE: {
                        startRecognition();
                    } break;


                    case TelephonyManager.CALL_STATE_RINGING: {
                        sphinxRecogniser.stopRecognition();
                    } break;


                    case TelephonyManager.CALL_STATE_OFFHOOK: {
                        sphinxRecogniser.stopRecognition();
                    } break;

                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        // dzięki temu mogę wyłączyć nasłuchiwanie gdy trwa rozmowa albo
        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        sharedPreferences=getSharedPreferences("Settings",MODE_PRIVATE);

        con = new Connection( sharedPreferences.getString("IP","Brak IP").toString());

        // rozpoczecie nasluchiwania na slowo klucz
//        Log.d("SPHINXRECOGNIZER", "Service Startuje SphinxRecogniser");
//        sphinxRecogniser = new SphinxRecogniser(this);
    }

    // metoda ktora wywolywana jest za kazdym razem gdy uruchomiony
    // zostanie serwis
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Metoda wykonywana w momencie niszczenia serwisu, odpowiedzialna zaprzerwanie nasłuchiwania na komendy,
     * wyrejestrowanie broadcastu
     * i usunięcie wykorzystywanych obiektów.
     */
    @Override
    public void onDestroy() {
        sphinxRecogniser.stopRecognition();

        // wyrejestrowanie Broadcastu sluzacego do otrzymywania wiadomosci z GoogleRecogniser
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

        // zerwanie polaczenia
        if(con != null) {
            con.disconnect();
        }

        super.onDestroy();
    }

    private void startProccessingCommand(String command) {
        String[] parts = command.split(";;");

        if(parts.length > 1) {
            Log.d("LISTENSERVICE", parts[0] + ": " + parts[1]);

            Toast.makeText(this, parts[0] + ": " + parts[1], Toast.LENGTH_SHORT).show();
            if(parts[0].equals("Telefon")) {
                Intent new_intent = new Intent();
                new_intent.putExtra("Polecenie", parts[1]);
                new_intent.setClass(this, New_Intent.class);

                new_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(new_intent);
            }
            else {
                Log.d("LISTENSERVICE", "IP :" + con.ip + ".");

                if(!con.ip.equals("Brak IP") && !con.ip.equals("") && !con.ip.equals(" ")) {
                    con.send(parts[1].toLowerCase());
                }
                else {
                    Toast.makeText(this, "Brak połączenia z komputerem.", Toast.LENGTH_SHORT).show();
                }
            }
        }


        // uruchomienie na nowo nasluchiwania na slowo klucz
        startRecognition();
    }

    // funkcja pozwalajaca by rozpoczac na nowo nasluchiwanie
    // na slowo klucz
    // funkcja pozwalajaca by rozpoczac na nowo nasluchiwanie
    // na slowo klucz
    private void startRecognition(){
        final Context context = this;
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//
//            }
//        }, 500);   //5 seconds
        Log.d("SPHINXRECOGNIZER", "Service Startuje SphinxRecogniser");
        if(sphinxRecogniser != null)
            sphinxRecogniser.stopRecognition();
        
        sphinxRecogniser = new SphinxRecogniser(context);
    }


}
