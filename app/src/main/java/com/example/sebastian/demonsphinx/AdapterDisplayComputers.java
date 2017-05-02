package com.example.sebastian.demonsphinx;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class AdapterDisplayComputers extends RecyclerView.Adapter{
    public Context context;
   public static String IP;
    public String iptext;


    // źródło danych
    private ArrayList<NameOfComputers> list = new ArrayList<>();

    // obiekt listy artykułów
    private RecyclerView mRecyclerView;
    private RecyclerView computers;

    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView ip;



        public MyViewHolder(View pItem) {
            super(pItem);
            context=pItem.getContext();
            ip = (TextView) pItem.findViewById(R.id.ip_address);
            computers= (RecyclerView) pItem.findViewById(R.id.computers);

        }
    }

    public AdapterDisplayComputers(){

    }
    // konstruktor adaptera
    public AdapterDisplayComputers(ArrayList<NameOfComputers> pArticles, RecyclerView pRecyclerView){
        list = pArticles;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_view_computerlist, viewGroup, false);
        final CardView cardView= (CardView) view.findViewById(R.id.cardview);
        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int position = mRecyclerView.getChildAdapterPosition(v);


               Intent intent=new Intent(context,ConnectionView.class);
                NameOfComputers name=list.get(position);
                IP=name.getIp();


            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new AdapterDisplayComputers.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        NameOfComputers nameOfComputers = list.get(i);
        ((AdapterDisplayComputers.MyViewHolder) viewHolder).ip.setText(nameOfComputers.getIp());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getIP() {
        return IP;
    }
}
