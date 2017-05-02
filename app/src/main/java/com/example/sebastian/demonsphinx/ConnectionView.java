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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ConnectionView extends AppCompatActivity {
        public static boolean state;
    ProgressDialog progressDialog;
    List<String> computer = new ArrayList<>();
    WifiInfo wifiInfo ;

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

                    computer = new DatagramSender().execute(wifiInfo.getBroadcast()).get();
                    Iterator<String> iterator = computer.iterator();
                    while (iterator.hasNext()) {
                        computerLists.add(new NameOfComputers(iterator.next()));
                    }



                    state=false;
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

        state=false;
        progressDialog=new ProgressDialog(ConnectionView.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Wyszukiwanie");
        progressDialog.setMessage("Szukam dostępnych komputerów w sieci...");

        progressDialog.show();






    }

    private void Progress(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(state){

                    progressDialog.incrementProgressBy(1);

                }
            }
        }).start();
    }

    public void Conect(View view) {
SharedPreferences sharedPreferences=getSharedPreferences("Settings",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

AdapterDisplayComputers adapter=new AdapterDisplayComputers();

       editor.putString("IP",adapter.IP);
        editor.commit();
        Toast.makeText(this,adapter.IP,Toast.LENGTH_LONG).show();

    }
}