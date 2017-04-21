package com.example.sebastian.demonsphinx.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


public class Preferences extends Activity {

String value;
SharedPreferences sharedPreferences;
Context context=getBaseContext();
    public String getValue() {
        return value;
    }

    public Preferences() {
        value="ddddd";

    }

        public void Save(String key, String value)
        {
            SharedPreferences sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString(key,value);
            editor.apply();
            //Toast.makeText(this,"Sucessfully",Toast.LENGTH_LONG).show();
        }

    public String Load(String key)
    {
        String Default="N/A";
        String value;

        sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        value=sharedPreferences.getString(key,Default);
        if(value==null)
        {
            Toast.makeText(this,"aaa", Toast.LENGTH_LONG).show();
            value="aaa";

        }
        //Toast.makeText(this,value,Toast.LENGTH_LONG).show();
        return value;
    }
}

