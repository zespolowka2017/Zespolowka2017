package com.example.sebastian.demonsphinx;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hubert Stępiński on 24.04.2017.
 */

public class MyAdapter extends RecyclerView.Adapter {
    public  Context context; //SharedPreferences sharedPreferences=getSharedPreferences("Settings",MODE_PRIVATE);
    FragmentManager fragmentManager,fragmentManager3;
    FragmentTransaction fragmentTransaction, fragmentTransaction1,fragmentTransaction2,fragmentTransaction3,fragmentTransaction4;
    // źródło danych
    private ArrayList<NameOfOptions> mArticles = new ArrayList<>();

    // obiekt listy artykułów
    private RecyclerView mRecyclerView;

    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;


        public MyViewHolder(View pItem) {
            super(pItem);
            context=pItem.getContext();
            mTitle = (TextView) pItem.findViewById(R.id.article_title);
            mContent = (TextView) pItem.findViewById(R.id.article_subtitle);
        }
    }

    // konstruktor adaptera
    public MyAdapter(ArrayList<NameOfOptions> pArticles, RecyclerView pRecyclerView){
        mArticles = pArticles;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_view, viewGroup, false);
            //context=
        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int position = mRecyclerView.getChildAdapterPosition(v);

                Intent intent=new Intent(context,DisplaySettings.class);
                    if(position==0){
                        intent.putExtra("key",0);
                    }
                    else if(position==1){
                        intent.putExtra("key",1);
                    }
                    else if(position==2){
                        intent.putExtra("key",2);
                    }
                    else if(position==3){
                        intent.putExtra("key",3);
                    }
                    else if(position==4){
                        intent.putExtra("key",4);
                    }
                    else if(position==5){
                        intent.putExtra("key",5);
                    }
                    else if(position==6){
                        intent.putExtra("key",6);
                    }
                    else if(position==7){
                        intent.putExtra("key",7);
                    }
                    else if(position==8){
                        intent.putExtra("key",8);
                    }
                context.startActivity(intent);

            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        NameOfOptions article = mArticles.get(i);
        ((MyViewHolder) viewHolder).mTitle.setText(article.getTitle());
        ((MyViewHolder) viewHolder).mContent.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}

