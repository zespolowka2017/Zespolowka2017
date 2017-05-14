package com.example.sebastian.demonsphinx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sebastian.demonsphinx.Adapters.AdapterDisplayComputers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Klasa odpowiedzialna za stworzenei widoku wyszukiwania komputerow w sieci.
 * Opowiada rowniez za wywolanie metody wyszukiwajacej komputery.
 * Pobiera wyniki wyszukiwania i przesyla dane do klasy AdapterDisplayComputers w celu ich wyswietlenia na ekranie.
 */
public class ConnectionView extends AppCompatActivity {

    private  ProgressDialog progressDialog;
    private WifiInfo wifiInfo ;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<NameOfComputers> computerLists;

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
                       // adapter=new AdapterDisplayComputers();
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
        setTitle("Dostępne Urządzenia");
        progressDialog=new ProgressDialog(ConnectionView.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Wyszukiwanie");
        progressDialog.setMessage("Szukam dostępnych komputerów w sieci...");
        progressDialog.show();


    }

    }