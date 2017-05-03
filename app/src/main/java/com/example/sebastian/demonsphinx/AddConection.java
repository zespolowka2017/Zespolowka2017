package com.example.sebastian.demonsphinx;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

 /**
 * Klasa odpowiedzialna za wyswietlenie layoutu dla dodawania donwego polaczenia
 *
 */

public class AddConection extends AppCompatActivity {

    /**
     * Deklaracja zmiennych reprezentujacych pola do wpisywania ip, nazwy komputera
     * oraz przycisku do zapisania ustawien.
     */
    private EditText Ip;
    private EditText Name;
    private Button Save;
    /**
     * Deklaracja zmiennej odpowiedzialnej za utworzenie polaczenia z plikiem
     * konfiguracyjnym.
     */
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_connections);
        setTitle("Dodaj Urządzenie");
       /**
         * Inicjalizacja wczesniej zadeklarowanych zmiennych
         */
        Ip= (EditText) findViewById(R.id.ip);
        Name= (EditText) findViewById(R.id.name);

        Save= (Button) findViewById(R.id.add);

        /**
         * Utworzenie polaczenia z plikiem konfiguracyjnym 'Settings'.
         */
        sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        /**
         * Wywolanie metody pozwalajacej na zapisanie wprowadzonych danych poprzez
         * klikniecie w przycisk zapisz.
         */
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Zapisanie w pliku konfiguracyjnym pod odpowiednimi kluczami wprowadzonych danych.
                 */
               editor.putString("IP",Ip.getText().toString());
                editor.putString("NAME",Name.getText().toString());
                editor.commit();
            }
        });
    }

}
