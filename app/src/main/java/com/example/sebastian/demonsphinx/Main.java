package com.example.sebastian.demonsphinx;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Klasa przygotowyje do wyświetlenia layout fragment_main.xml.
 * Odpowiada za absługę włączenia nasłuchu poleceń.
 * Wyświetla informacje o aktualnie podłączonym urządzeniu.
 */
public class Main extends Fragment {

    /**
     * Zmienna potrzebna do wyświetlenia animacji.
     */
    private  ImageView imageView;

    /**
     * Konstruktor bezargumentowy.
     */
    public Main() {
    }

    /**
     *W ciele metody dodawana jest referencja do zmiennych klasy.
     * Zachodi również obsługa naciśniejcia przycisku odpowiadającego za aktywację lub dezaktywację serwisu nasłuchująceg poleceń.
     * Metoda zwraca powstały widok.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);

        imageView= (ImageView) view.findViewById(R.id.imageView2);

        /**
         * Deklaracja i incjalizacja przycisku.
         */
        final ToggleButton btnServiceStartStop = (ToggleButton) view.findViewById(R.id.btnStartStopService);

        /**
         * Metoda obsługująca zdarzenie wciśniecia przycisku.
         */
        btnServiceStartStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceIntent = new Intent(buttonView.getContext(), ListenService.class);

                /**
               * Jeżeli przycisk jest wciśnięty zmienia się kolor rysunku oraz następuje włączenie serwisu.
                 *
                 */
                if(isChecked) {
                    imageView.setImageResource(R.drawable.transitionon);
                    ((TransitionDrawable) imageView.getDrawable()).startTransition(2000);

                    getActivity().startService(serviceIntent);

                    /**
                     * W przeciwnym wypadku serwis zostaje zatrzymany i zmienia się kolor rysunku.
                     */
                } else {
                    imageView.setImageResource(R.drawable.transitionoff);
                    ((TransitionDrawable) imageView.getDrawable()).startTransition(2000);
                    getActivity().stopService(serviceIntent);

                }

            }
        });

        return view;

    }

    /**
     * Metoda wykonuje sie wraz z ponownym startem aktywności. W wyniku czego pola dotyczące informacji o akualnie podłączonym komputerze
     * są aktualizowane.
     */
    @Override
    public void onResume(){
        super.onResume();
        /**
         * Tworzenie obiektu odpowiedzialnego za podłączenie z plikiem konfiguracyjnym.
         */
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        /**
         * Inicjalizacja odpowiednich pól tekstowych w celu wyświetlenia informacji.
         */
        TextView textView= (TextView) this.getActivity().findViewById(R.id.info);
        TextView textView1= (TextView) this.getActivity().findViewById(R.id.conectName);
        /**
         * Przypisanie odpowienim z pól pobranych z=informacji z pliku konfigurayjnego.
         */
        textView.setText(sharedPreferences.getString("IP"," "));
        textView1.setText(sharedPreferences.getString("NAME"," "));
    }
}
