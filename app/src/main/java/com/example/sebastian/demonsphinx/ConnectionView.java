package com.example.sebastian.demonsphinx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Klasa odpowiedzialna za stworzenei widoku wyszukiwania komputerow w sieci.
 * Opowiada rowniez za wywolanie metody wyszukiwajacej komputery.
 * Pobiera wyniki wyszukiwania i przesyla dane do klasy AdapterDisplayComputers w celu ich wyswietlenia na ekranie.
 */
public class ConnectionView extends AppCompatActivity {

    private  ProgressDialog progressDialog;
    List<String> computer = new ArrayList<>();
    WifiInfo wifiInfo ;
    AdapterDisplayComputers adapter;
    Context context;
    RecyclerView recyclerView;
    ArrayList<NameOfComputers> computerLists;

    @Override
    protected void onStart() {

        context = getApplicationContext();
        wifiInfo = new WifiInfo(context);
        recyclerView = (RecyclerView) findViewById(R.id.computers);

        // w celach optymalizacji
        recyclerView.setHasFixedSize(true);

        // ustawiamy LayoutManagera
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // tworzymy źródło danych - tablicę z artykułami
        //ArrayList<NameOfOptions> articles = new ArrayList<>();

        computerLists = new ArrayList<>();

        Thread t=new Thread(){
            public void run(){
                try {
                    Map<String,String> map=new HashMap<String,String>();
                    map = new DatagramSender().execute(wifiInfo.getBroadcast()).get();

                    Iterator myIterator = map.keySet().iterator();
                    while(myIterator.hasNext()) {
                        String ip=(String)myIterator.next();
                        String name=(String)map.get(ip);
                        computerLists.add(new NameOfComputers(ip.replace("/",""),name));
                        Log.d("lista1",computerLists.get(0).toString());
                        Log.d("lista",computerLists.get(0).getIp()+computerLists.get(0).getName());
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new AdapterDisplayComputers(computerLists, recyclerView));
                        progressDialog.dismiss();
                        adapter=new AdapterDisplayComputers();
                    }
                });
            }

        };
        t.start();

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connetion_view);

        progressDialog=new ProgressDialog(ConnectionView.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Wyszukiwanie");
        progressDialog.setMessage("Szukam dostępnych komputerów w sieci...");
        progressDialog.show();

    }


    public void Conect(View view) {

        SharedPreferences sharedPreferences=getSharedPreferences("Settings",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        AdapterDisplayComputers adapter=new AdapterDisplayComputers();
        editor.putString("NAME", adapter.Name);

        editor.putString("IP",adapter.IP);
        //editor.commit();
        editor.apply();
        Toast.makeText(this,adapter.IP+ adapter.Name,Toast.LENGTH_LONG).show();
    }
}