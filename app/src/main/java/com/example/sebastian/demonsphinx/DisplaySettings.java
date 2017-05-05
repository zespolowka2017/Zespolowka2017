package com.example.sebastian.demonsphinx;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *Klasa odpowiada za wyświetlanie aktywnosci do edycji opcji oraz
 * zapisuje wprowadzone ustawienia przez uzytkownika do pliku konfiguracyjnego
 */

public class DisplaySettings extends AppCompatActivity  {

    /**
     * Zmienne przechowywujące referencje do pół tekstowych w widoku display_settings.xml.
     */
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    /**
     * Zmienna przechowywująca referencje do przyciszku zapisujacego wprowadzone ustawienia.
     */
    private Button save;
    /**
     * Zmienne przechowywujące referencje do pól, w których następuje wprowadzanie ustawień przez użytkownika.
     */
    private EditText eValue;
    private EditText eValue1;
    private EditText eValue2;
    private EditText eValue3;
    /**
     * Zmienna przechowywująca informacje jaka opcja do modyfikacji została wybrana.
     */
    private int key;
    /**
     * Obiekt pozwalający na połączenie z plikiem konfiguracyjnym.
     */
    private SharedPreferences sharedPreferences;

    /**
     *Tworzenie widoku z godnie z wyborem opcji przez użytkownika przeznaconego do edycji ustawień aplikacji.
     **W ciele metody dokunuję się przypisanie referencji do zmiennych.
     * sprawdzany jest różnież wprowadzany tekst (walidacja).
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_settings);
        /**
         * Otworzenie połączenia z plikiem konfiguracyjnym.
         */
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        /**
         * Przypisanie referencji do pol tekstowych
         */
        txt2 = (TextView) findViewById(R.id.value);
        txt3= (TextView) findViewById(R.id.value1);
        txt4=(TextView) findViewById(R.id.value3);
         /**
          * Przypisanie referencji do edytowalnych pol tekstowych
        */
        eValue = (EditText) findViewById(R.id.setValue);
        eValue1 = (EditText) findViewById(R.id.setValue1);
        eValue2 = (EditText) findViewById(R.id.setValue2);
        eValue3 = (EditText) findViewById(R.id.setValue3);

eValue2.setFocusable(true);


        /**
         * Walidacja wpisywanego tekstu
         */
        eValue2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s.length() ==3) {

                        eValue2.setError("Wprowadź wartość od 0-100");
                    }
                }
                catch(NumberFormatException e)
                {

                }
            }


            @Override
            public void afterTextChanged(Editable s){
        }

        });


        /**
         * Inicjaizacja przycisku odpowiadajacego za zapis wprowadzonych danych
         */
        save = (Button) findViewById(R.id.save);
        /**
         * pobranie informacji jaka opcje wybral uzytkownik
         */
        key = getIntent().getIntExtra("key", 0);
        /**
         * Blok instrukcji warunkowych.
         * W zależności od tego jaka opcja została wybrana, przygotowywany jest obszar widoku do wyświetlenia.
         * Dodanie tytułu stronie.
         * Wyświetlenie aktualnych wartości do pól tekstowych pobranych z pliku konfiguracyjnego.
         * Ustawienie widoczności pól.
         */
        if (key == 0) {
            setTitle("Zmniejszenie jasności");
            txt2.setText(sharedPreferences.getString("BrightnesMinus", " "));
            txt3.setText(String.valueOf(sharedPreferences.getInt("BrightnesMinus1", 1))+ "%");
            txt4.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
        }
        else if (key == 1) {
            setTitle("Zwiększenie jasności");
            txt2.setText(sharedPreferences.getString("BrightnesPlus", " "));
            txt3.setText(String.valueOf(sharedPreferences.getInt("BrightnesPlus1", 1))+"%");
            txt4.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);

        }
        else if (key == 2) {
            setTitle("Zmaina trybu telefonu");
            txt2.setText(sharedPreferences.getString("VolumePlus", " "));

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 3) {
            setTitle("Zmiana trybu telefonu");

            txt2.setText(sharedPreferences.getString("VolumeMinus", " "));

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 4) {
            setTitle("Połączenia głosowe");

            txt2.setText(sharedPreferences.getString("Call", " "));

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 5) {
            setTitle("Przeglądarka");

            txt2.setText(sharedPreferences.getString("Browser", " "));

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 6) {
            setTitle("MultimediaPlus");

            txt2.setText(sharedPreferences.getString("Multimedia", " "));
            txt4.setText(String.valueOf(sharedPreferences.getInt("Multimedia1", 1)));
            txt3.setText(sharedPreferences.getString("Multimedia2", " "));

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setHint("Wprowadz klucz");



        }
        else if (key == 7) {
            setTitle("MultimediaMinus");

            txt2.setText(sharedPreferences.getString("Multimedia", " "));
            txt4.setText(String.valueOf(sharedPreferences.getInt("Multimedia4", 1)));
            txt3.setText(sharedPreferences.getString("Multimedia3", " "));

            eValue1.setVisibility(View.INVISIBLE);



        }
        else if (key == 8) {
            setTitle("Aparat");
            txt2.setText(sharedPreferences.getString("Foto", " "));

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);



        }

        /**
         * metoda obslugujaca zdarzenie nacisniecia przycisku
         */
        addButtonClickListner();
    }

    /**
     * Metoda obsługująca kliknięcie w przycis zapisujący ustawienia.
     * Każdy blok warunkowy odpowiada jednej z dostepnych opcji.
     * W każdym z bloków następuje wywołanie metody zapisującej ustawienia w pliku konfiguracyjnym z odpowiednią ilością parametrów,
     * zależną od ilości pól do modyfikacji.
     */
        public void addButtonClickListner(){


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key==0){
                    if(eValue.getText().toString().isEmpty()
                            ||eValue1.getText().toString().isEmpty()
                            ||eValue1.getText().toString().length()>2)
                    {
                        Toast.makeText(getBaseContext(),"Wpisz wartość",Toast.LENGTH_LONG).show();
                    }
                    else {
                        addSettings(eValue.getText().toString(), Integer.parseInt(eValue1.getText().toString()), "BrightnesMinus", "BrightnesMinus1");
                        eValue.setText(" ");
                        eValue1.setText(String.valueOf("1"));
                    }
                }
                else if(key==1){
                    if(eValue.getText().toString().isEmpty()||eValue1.getText().toString().isEmpty())
                    {
                        Toast.makeText(getBaseContext(),"Jedno z pół jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    }

                    else{
                        addSettings(eValue.getText().toString(),Integer.parseInt(eValue1.getText().toString()),"BrightnesPlus","BrightnesPlus1");
                        eValue.setText(" ");
                        eValue1.setText(String.valueOf("1"));
                    }
                }
                else if(key==2){
                    if(eValue.getText().toString().isEmpty())
                    {
                        Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        addSettings(eValue.getText().toString(),"VolumePlus");
                        eValue.setText(" ");
                    }

                }
                else if(key==3){
                    if(eValue.getText().toString().isEmpty())
                    {
                        Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        addSettings(eValue.getText().toString(),"VolumeMinus");
                        eValue.setText(" ");
                    }

                }
                else if(key==4){
                    if(eValue.getText().toString().isEmpty())
                    {
                        Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        addSettings(eValue.getText().toString(),"Call");
                        eValue.setText(" ");
                    }

                }
                else if(key==5){
                    if(eValue.getText().toString().isEmpty())
                    {
                        Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        addSettings(eValue.getText().toString(),"Browser");
                        eValue.setText(" ");
                    }

                }
                else if(key==6){
                   // if(eValue.getText().toString().isEmpty())
                   // {
                        Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    //}
                   // else{
                        addSettings(eValue.getText().toString(),eValue2.getText().toString(),Integer.parseInt(eValue3.getText().toString()),"Multimedia","Multimedia1","Multimedia2");
                        eValue.setText(" ");
                   // }

                }
                else if(key==7){
                    // if(eValue.getText().toString().isEmpty())
                    // {
                    Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    //}
                    // else{
                    addSettings(eValue.getText().toString(),eValue2.getText().toString(),Integer.parseInt(eValue3.getText().toString()),"Multimedia","Multimedia4","Multimedia3");
                    eValue.setText(" ");
                    // }

                }
                else if(key==8){
                     if(eValue.getText().toString().isEmpty())
                     {
                    Toast.makeText(getBaseContext(),"Pole jest puste. Wpisz wartość.",Toast.LENGTH_LONG).show();
                    }
                    else{
                    addSettings(eValue.getText().toString(),"Foto");
                    eValue.setText(" ");
                     }

                }

            }
        });
    }

    /**
     *Metoda odpowiedzialna za wprowadzenie zmian do pliku konfiguracyjnego poprzez pobranie tekstu wpisywanego do pól.
     * Wyświetla informacje o pomyślnym dodaniu takich ustawień.
     */
    private void addSettings(String value,int value1,String key,String key1) {
        /**
         * Obiekt pozwalający na edycję pól w pliku konfiguracyjnym.
         */
    SharedPreferences.Editor editor = sharedPreferences.edit();

    editor.putString(key, value);
    editor.putInt(key1, value1);
    editor.commit();
    Toast.makeText(getBaseContext(),"Ustawienia Zapisane Pomyślnie",Toast.LENGTH_LONG).show();
}

    /**
     *Przciążona poprzednia metoda wpisująca do pliku konfiguracyjnego jedną wartość.
     * @param value- wprowadzony tekst przez użytkownika.
     * @param key- klucz po jakim teskt zostanie zapisany.
     */
    private void addSettings(String value,String key) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.commit();

        Toast.makeText(getBaseContext(),"Ustawienia Zapisane Pomyślnie",Toast.LENGTH_LONG).show();

    }

    /**
     * Tak jak w poprzednich przypadkach metoda służy do wprowadzania zmian w pliku konfiguracyjnym.
     * @param value- wartość wprowadzanego tekstu.
     * @param value2- wartość wprowadzanego tekstu.
     * @param value1- wartość wprowadzanego tekstu.
     * @param key-klucz dla pierwszej wartości.
     * @param key1-klucz dla drugiej wartości.
     * @param key2-klucz dla trzeciej wartości.
     */
    private void addSettings(String value,String value2 ,int value1,String key,String key1,String key2) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.putString(key2, value2);
        editor.putInt(key1, value1);
        editor.apply();
        Toast.makeText(getBaseContext(),"Ustawienia Zapisane Pomyślnie",Toast.LENGTH_LONG).show();
    }


}





