package com.example.sebastian.demonsphinx;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddConection extends AppCompatActivity {

        private EditText Ip;
    private EditText Name;
        private Button Save;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_connections);
        setTitle("Dodaj Urządzenie");

        Ip= (EditText) findViewById(R.id.ip);
        Name= (EditText) findViewById(R.id.name);

        Save= (Button) findViewById(R.id.add);

        sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editor.putString("IP",Ip.getText().toString());
                editor.putString("NAME",Name.getText().toString());
                editor.commit();
            }
        });
    }

}
