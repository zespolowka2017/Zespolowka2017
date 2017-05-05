package com.example.sebastian.demonsphinx.Adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sebastian.demonsphinx.NameOfComputers;
import com.example.sebastian.demonsphinx.R;

import java.util.ArrayList;


public class AdapterDisplayComputers extends RecyclerView.Adapter {
    public static String IP;
    public static String Name;
    // źródło danych
    private ArrayList<NameOfComputers> list = new ArrayList<>();
    // obiekt listy artykułów
    private RecyclerView mRecyclerView;
   // private RecyclerView computers;


    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView ip;
        private TextView name;


        public MyViewHolder(View pItem) {
            super(pItem);
            ip = (TextView) pItem.findViewById(R.id.ip_address);
            name = (TextView) pItem.findViewById(R.id.name);
           // computers = (RecyclerView) pItem.findViewById(R.id.computers);

        }
    }

    public AdapterDisplayComputers() {

    }

    // konstruktor adaptera
    public AdapterDisplayComputers(ArrayList<NameOfComputers> pList, RecyclerView pRecyclerView) {
        list = pList;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout dostepnyc komputerow w sieci oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_view_computerlist, viewGroup, false);
        final CardView cardView = (CardView) view.findViewById(R.id.cardview);
        // dla elementu listy ustawiamy obiekt OnClickListener

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int position = mRecyclerView.getChildAdapterPosition(v);

                NameOfComputers lcomp = list.get(position);
                IP = lcomp.getIp();
                Name = lcomp.getName();

            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new AdapterDisplayComputers.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout dostepnych komputerow

        ((AdapterDisplayComputers.MyViewHolder) viewHolder).ip.setText(list.get(i).getIp().replace("/", ""));
        ((AdapterDisplayComputers.MyViewHolder) viewHolder).name.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

