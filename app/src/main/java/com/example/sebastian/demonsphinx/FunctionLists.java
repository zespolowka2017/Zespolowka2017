package com.example.sebastian.demonsphinx;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.demonsphinx.EditSettings.BrightnesMinus;
import com.example.sebastian.demonsphinx.EditSettings.BrightnesPlus;
import com.example.sebastian.demonsphinx.EditSettings.Call;
import com.example.sebastian.demonsphinx.EditSettings.VoiceSettings;
import com.example.sebastian.demonsphinx.EditSettings.VolumeMinus;
import com.example.sebastian.demonsphinx.Settings.MyExpedableListAdapter;
import com.example.sebastian.demonsphinx.Settings.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionLists extends AppCompatActivity implements VoiceSettings.UserSettings, VolumeMinus.IVolumeMinus,Call.ICall,BrightnesPlus.IBrightnesPlus,BrightnesMinus.IBrightnesMinus{
    TextView textView;
    ExpandableListView expandableListView;
    FragmentManager fragmentManager,fragmentManager3;
   FragmentTransaction fragmentTransaction, fragmentTransaction1,fragmentTransaction2,fragmentTransaction3,fragmentTransaction4;
    TextView text;
    List<String> list;
    Map<String,List<String>> map;
    ExpandableListAdapter expandableListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_lists);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction1=fragmentManager.beginTransaction();
        fragmentTransaction2=fragmentManager.beginTransaction();
        //fragmentManager3=getSupportFragmentManager();
        fragmentTransaction3=fragmentManager.beginTransaction();
        fragmentTransaction4=fragmentManager.beginTransaction();
        final VoiceSettings voiceSettings=new VoiceSettings();
        Intent intent =new Intent();





        textView= (TextView) findViewById(R.id.textView12);
        expandableListView= (ExpandableListView)findViewById(R.id.expendable);
        FillData();
        expandableListAdapter=new MyExpedableListAdapter(this,list,map);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                CaseFunction(map.get(list.get(groupPosition)).get(childPosition).toString());


                return true;
                 }
               });
         }
public void  CaseFunction(String field){
    if(field.equals("Głośniej")){


        SharedPreferences sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        String value=sharedPreferences.getString("VolumePlus"," "  );
        int value1=sharedPreferences.getInt("VolumePlus1",1  );
        Bundle bundle=new Bundle();
        bundle.putString("Plus",value);
        bundle.putInt("Plus1",value1);
        VoiceSettings voiceSettings=new VoiceSettings();
        voiceSettings.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentVolume,voiceSettings);

        fragmentTransaction.addToBackStack(null).commit();


    }
    if(field.equals("Ciszej")){
        fragmentManager.popBackStack();
        SharedPreferences sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        String value=sharedPreferences.getString("VolumeMinus"," "  );
        int value1=sharedPreferences.getInt("VolumeMinus1",1  );
        Bundle bundle=new Bundle();
        bundle.putString("Minus",value);
        bundle.putInt("Minus1",value1);
        VolumeMinus volumeMinus=new VolumeMinus();
        volumeMinus.setArguments(bundle);

        fragmentTransaction1.replace(R.id.fragmentVolume,volumeMinus);
        fragmentTransaction1.addToBackStack(null).commit();



    }
    if(field.equals("Jaśniej")){

        SharedPreferences sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        String value=sharedPreferences.getString("BrightnesPlus"," "  );
        int value1=sharedPreferences.getInt("BrightnesPlus1",1  );
        Bundle bundle=new Bundle();
        bundle.putString("BPlus",value);
        bundle.putInt("BPlus1",value1);
        BrightnesPlus brightnesPlus=new BrightnesPlus();
        brightnesPlus.setArguments(bundle);

        fragmentTransaction3.replace(R.id.fragmentVolume,brightnesPlus);
        fragmentTransaction3.commit();


    }
    if(field.equals("Ciemniej")){

         SharedPreferences sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
         String value=sharedPreferences.getString("BrightnesMinus"," "  );
         int value1=sharedPreferences.getInt("BrightnesMinus1",1  );
        Bundle bundle=new Bundle();
         bundle.putString("BrightnesMinus",value);
         bundle.putInt("BrightnesMinus1",value1);
        BrightnesMinus brightnesMinus=new BrightnesMinus();
         brightnesMinus.setArguments(bundle);

        fragmentTransaction4.replace(R.id.fragmentVolume,brightnesMinus);
        fragmentTransaction4.commit();


    }
    if(field.equals("Zadzwoń")){

        SharedPreferences sharedPreferences= getSharedPreferences("Settings",MODE_PRIVATE);
        String value=sharedPreferences.getString("Call"," "  );
        Bundle bundle=new Bundle();
        bundle.putString("Call",value);
        Call call=new Call();
        call.setArguments(bundle);

        fragmentTransaction2.replace(R.id.fragmentVolume,call);
        fragmentTransaction2.commit();


    }

}
    public  void FillData(){
        list=new ArrayList<>();
        map=new HashMap<>();

        list.add("Telefon");


        List<String> Telephon=new ArrayList<>();


        Telephon.add("Głośniej");
        Telephon.add("Ciszej");
        Telephon.add("Jaśniej");
        Telephon.add("Cemniej");
        Telephon.add("Zadzwoń");



        map.put(list.get(0),Telephon);


    }

    @Override
    public void VolumePlus(String value, int value1) {

       SharedPreferences sharedPreferences= getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("VolumePlus",value);
        editor.putInt("VolumePlus1",value1);
        editor.apply();
        Toast.makeText(this,"Zapisano ustawienia", Toast.LENGTH_LONG).show();
    }
    @Override
    public void VolumeMinus(String value, int value1) {

        SharedPreferences sharedPreferences= getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("VolumeMinus",value);
        editor.putInt("VolumeMinus1",value1);
        editor.apply();
        Toast.makeText(this,"Zapisano ustawienia", Toast.LENGTH_LONG).show();
    }

    @Override
    public void Call(String value) {
        SharedPreferences sharedPreferences= getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("Call",value);
        editor.commit();
        Toast.makeText(this,"Zapisano ustawienia", Toast.LENGTH_LONG).show();
    }

    @Override
    public void BrightnesPlus(String value, int value1) {
        SharedPreferences sharedPreferences= getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("BrightnesPlus",value);
        editor.putInt("BrightnesPlus1",value1);
        editor.apply();
        Toast.makeText(this,"Zapisano ustawienia", Toast.LENGTH_LONG).show();
    }
    @Override
    public void BrightnesMinus(String value, int value1) {
        SharedPreferences sharedPreferences= getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("BrightnesMinus",value);
        editor.putInt("BrightnesMinus1",value1);
        editor.commit();
        Toast.makeText(this,"Zapisano ustawienia", Toast.LENGTH_LONG).show();
    }
}
