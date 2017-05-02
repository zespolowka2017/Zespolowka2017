package com.example.sebastian.demonsphinx;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *Klasa odpowiada za wyświetlanie aktywnosci do edycji opcji oraz
 * zapisuje wprowadzone ustawienia przez uzytkownika do pliku konfiguracyjnego
 */

public class DisplaySettings extends AppCompatActivity {

    private TextView txt1,txt2,txt3,txt4;
    private Button save;
    private EditText eValue,eValue1,eValue2,eValue3;
    private int key;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_settings);
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        /**
         * Przypisanie referencji do pol tekstowych
         */
     //   txt1 = (TextView) findViewById(R.id.STitle);
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

        /**
         * Inicjaizacja przycisku odpowiadajacego za zapis wprowadzonych danych
         */
        save = (Button) findViewById(R.id.save);
        /**
         * pobranie informacji jaka opcje wybral uzytkownik
         */
        key = getIntent().getIntExtra("key", 0);
        if (key == 0) {
            setTitle("Zmniejszenie jasności");
            txt2.setText(sharedPreferences.getString("BrightnesMinus", " ").toString());
            txt3.setText(String.valueOf(sharedPreferences.getInt("BrightnesMinus1", 1))+ "%");
            txt4.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
        }
        else if (key == 1) {
            setTitle("Zwiększenie jasności");
            txt2.setText(sharedPreferences.getString("BrightnesPlus", " ").toString());
            txt3.setText(String.valueOf(sharedPreferences.getInt("BrightnesPlus1", 1))+"%");
            txt4.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);

        }
        else if (key == 2) {
            setTitle("Zmaina trybu telefonu");
            txt2.setText(sharedPreferences.getString("VolumePlus", " ").toString());

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 3) {
            setTitle("Zmiana trybu telefonu");

            txt2.setText(sharedPreferences.getString("VolumeMinus", " ").toString());

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 4) {
            setTitle("Połączenia głosowe");

            txt2.setText(sharedPreferences.getString("Call", " ").toString());

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 5) {
            setTitle("Przeglądarka");

            txt2.setText(sharedPreferences.getString("Browser", " ").toString());

            eValue1.setVisibility(View.INVISIBLE);
            eValue2.setVisibility(View.INVISIBLE);
            eValue3.setVisibility(View.INVISIBLE);
            txt3.setVisibility(View.INVISIBLE);
            txt4.setVisibility(View.INVISIBLE);


        }
        else if (key == 6) {
            setTitle("MultimediaPlus");

            txt2.setText(sharedPreferences.getString("Multimedia", " ").toString());
            txt4.setText(String.valueOf(sharedPreferences.getInt("Multimedia1", 1)));
            txt3.setText(sharedPreferences.getString("Multimedia2", " ").toString());

            eValue1.setVisibility(View.INVISIBLE);



        }
        else if (key == 7) {
            setTitle("MultimediaMinus");

            txt2.setText(sharedPreferences.getString("Multimedia", " ").toString());
            txt4.setText(String.valueOf(sharedPreferences.getInt("Multimedia4", 1)));
            txt3.setText(sharedPreferences.getString("Multimedia3", " ").toString());

            eValue1.setVisibility(View.INVISIBLE);



        }
        else if (key == 8) {
            setTitle("Aparat");
            txt2.setText(sharedPreferences.getString("Foto", " ").toString());

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
private void addSettings(String value,int value1,String key,String key1) {

    SharedPreferences.Editor editor = sharedPreferences.edit();

    editor.putString(key, value);
    editor.putInt(key1, value1);
    editor.commit();
    Toast.makeText(getBaseContext(),"Ustawienia Zapisane Pomyślnie",Toast.LENGTH_LONG).show();
}

    private void addSettings(String value,String key) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);

        editor.commit();

        Toast.makeText(getBaseContext(),"Ustawienia Zapisane Pomyślnie",Toast.LENGTH_LONG).show();

    }
    private void addSettings(String value,String value2 ,int value1,String key,String key1,String key2) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.putString(key2, value2);
        editor.putInt(key1, value1);
        editor.apply();
        Toast.makeText(getBaseContext(),"Ustawienia Zapisane Pomyślnie",Toast.LENGTH_LONG).show();
    }


}





