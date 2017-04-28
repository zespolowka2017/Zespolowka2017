package com.example.sebastian.demonsphinx;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;

/**
 * Klasa umozliwiajaca nasluchiwanie na slowo klucz.
 */
public class SphinxRecogniser implements RecognitionListener {
    private static final String KWS_SEARCH = "komputer";
    private static final String KEYPHRASE = "oh mighty computer";

    private Context context;

    // zmienna przechowujaca rozpoznana wartosc
    private String result = "NO RESULT";

    // zmienna odpowiedzialna za rozpoznawanie mowy dla PocketSphinxa
    private SpeechRecognizer recognizer;


    public SphinxRecogniser(Context con) {
        context = con;

        runRecognizerSetup();
    }

    /**
     * metoda przerywajaca nasluchiwanie na slowo klucz
     */
    public void stopRecognition() {
        recognizer.stop();
        recognizer.shutdown();
    }

    // Metody z RecognitionListener (od PocketSphinx) potrzebne do implementacji
    @Override
    public void onBeginningOfSpeech() {

    }

    // metoda od PocketSphinx wywolywana gdy zakonczone zostanie
    // rozpoznawanie
    @Override
    public void onEndOfSpeech() {
        if (!recognizer.getSearchName().equals(KWS_SEARCH))
            recognizer.startListening(KWS_SEARCH);
    }

    // metoda wywolywana jest za kazdym razem gdy rozpoznany
    // zostanie fragment wypowiedzi
    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if (hypothesis == null)
            return;

        result = hypothesis.getHypstr();

        recognizer.stop();
    }

    // metoda wywolywana jest za kazdym razem gdy
    // rozpoznane zostanie slowo klucz
    @Override
    public void onResult(Hypothesis hypothesis) {

        if (hypothesis != null) {
            result = hypothesis.getHypstr();
            switch (result) {
                case "komputer": {
                    // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                    computerRecognised();
                } break;

                case "telefon": {
                    // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                    phoneRecognised();
                } break;

                default: {
                    Toast.makeText(context, "CANNOT RECOGNIZE WORD", Toast.LENGTH_SHORT).show();
                    recognizer.startListening(KWS_SEARCH);
                }
            }
        }
    }

    @Override
    public void onError(Exception e) {

    }

    // metoda wywolywana w momencie gdy czas na rozpoznanie
    // wypowiedzi sie skonczy
    @Override
    public void onTimeout() {
        recognizer.startListening(KWS_SEARCH);
    }

    // ustawienie sciezek do plikow dla sphinxa
    private void runRecognizerSetup() {
        // Inicjalizacja PocketSphinxa zajmuje troche czasu dlatego
        // jest w osobnym watku
        new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    Assets assets;
                    File assetsDir = null;
                    assets = new Assets(context);
                    assetsDir = assets.syncAssets();

                    setupRecognizer(assetsDir);
                } catch (IOException e) {
                    return e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Exception result) {
                if (result != null) {
                    Toast.makeText(context, "Failed to init recognizer" + result, Toast.LENGTH_SHORT).show();
                } else {
                    recognizer.startListening(KWS_SEARCH);
                }
            }
        }.execute();
    }

    // konfiguracja sposobu sluchania oraz oczekiwania na konkretne slowo
    private void setupRecognizer(File assetsDir) {
        File modelDir = new File(assetsDir, "models");

        try {
            recognizer = SpeechRecognizerSetup.defaultSetup()
                    .setAcousticModel(new File(modelDir, "/hmm/en-us-semi"))
                    .setDictionary(new File(modelDir, "/dict/devices.dic"))
                    .setRawLogDir(assetsDir)
                    .getRecognizer();

            recognizer.addListener(this);

            recognizer.addGrammarSearch(KWS_SEARCH, new File(modelDir, "grammar/devices.gram"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metoda wywolywana gdy rozpoznane zostanie slowo Komputer
    private void computerRecognised() {
        // wygenerowanie odpowiedniego sygnalu dzwiekowego
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

        // Utworzenie nowego activity, dzieki ktoremu
        // dalsza czesc polecen bedzie rozpoznawana
        Intent googleActivity = new Intent();
        googleActivity.putExtra("Mode", "Komputer");
        googleActivity.setClass(context, GoogleRecogniser.class);

        googleActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(googleActivity);
    }

    // metoda wywolywana gdy rozpoznane zostanie slowo Telefon
    private void phoneRecognised() {
        // wygenerowanie odpowiedniego sygnalu dzwiekowego
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);

        // Utworzenie nowego activity, dzieki ktoremu
        // dalsza czesc polecen bedzie rozpoznawana
        Intent googleActivity = new Intent();
        googleActivity.putExtra("Mode", "Telefon");
        googleActivity.setClass(context, GoogleRecogniser.class);

        googleActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(googleActivity);
    }
}
