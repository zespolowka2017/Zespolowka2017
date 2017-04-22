package com.example.sebastian.demonsphinx;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Serwis działający w tle i obsługujący rozpoznawanie mowy.
 */
public class ListenService extends Service {
    private SphinxRecogniser sphinxRecognise;

    /**
     * Nazwa broadcastu dzieki ktoremu dowiadujemy sie o statusie serwisu.
     */
    static final public String SERVICE_STATUS = "SERVICE_STATUS";

    /**
     * Rodzaj wiadomosci przesylanej do okna glownego aplikacji
     * W celu zmiany statusu przycisku od serwisu
     */
    static final public String SERVICE_MESSAGE = "STATUS_MESSAGE";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Metoda wywolywana za kazdym razem gdy tworzony jest serwis.
     * Rejestruje ona Broadcast, dzieki ktoremu
     * przesylane jest rozpoznane polecenie.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Rejestracja Broadcastu w celu otrzymywania wiadomosci z GoogleRecogniser
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("GoogleRecogniser"));

        // dzięki temu mogę wyłączyć nasłuchiwanie gdy trwa rozmowa albo
        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        // rozpoczecie nasluchiwania na slowo klucz
        sphinxRecognise = new SphinxRecogniser(this);
    }

    // metoda ktora wywolywana jest za kazdym razem gdy uruchomiony
    // zostanie serwis
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // przeslanie statusu serwisu
        sendResult("Service started");

        return super.onStartCommand(intent, flags, startId);
    }

    // metoda ktora wywolywana jest za kazdym razem gdy
    // serwis zostanie wylaczony
    @Override
    public void onDestroy() {
        sphinxRecognise.stopRecognition();

        // wyrejestrowanie Broadcastu sluzacego do otrzymywania wiadomosci z GoogleRecogniser
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

        // przeslanie statusu serwisu
        sendResult("Service stopped");
        super.onDestroy();
    }

    /**
     * metoda dzieki ktorej przesylany jest status serwisu
     * do glownego okna aplikacji
     * @param message tresc wiadomosci przesylanej do glownego okna aplikacji
     */
    public void sendResult(String message) {
        Intent intent = new Intent(SERVICE_STATUS);
        if (message != null)
            intent.putExtra(SERVICE_MESSAGE, message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    // Uchwyt do intentu, wywolywany za kazdym razem
    // gdy przyjdzie wiadomosc z GoogleRecogniser
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Pobranie dodatkowych danych z intentu
            String message = intent.getStringExtra("result");

            String[] parts = message.split(";;");

            if(parts.length > 1) {
                Toast.makeText(context, parts[0] + " 99999 " + parts[1], Toast.LENGTH_SHORT).show();
                Intent new_intent = new Intent();
                new_intent.putExtra("Polecenie", parts[1]);
                new_intent.setClass(context, New_Intent.class);

                new_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(new_intent);
            }


            // uruchomienie na nowo nasluchiwania na slowo klucz
            startRecognition();
        }
    };

    // funkcja pozwalajaca by rozpoczac na nowo nasluchiwanie
    // na slowo klucz
    private void startRecognition(){
        sphinxRecognise = new SphinxRecogniser(this);
    }

    private final PhoneStateListener phoneStateListener = new PhoneStateListener() {

        boolean phoned = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: {
                    startRecognition();
                } break;


                case TelephonyManager.CALL_STATE_RINGING: {
                    sphinxRecognise.stopRecognition();
                } break;


                case TelephonyManager.CALL_STATE_OFFHOOK: {
                    sphinxRecognise.stopRecognition();
                } break;

            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

}
