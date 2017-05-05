package com.example.sebastian.demonsphinx.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sebastian.demonsphinx.DisplaySettings;
import com.example.sebastian.demonsphinx.NameOfOptions;
import com.example.sebastian.demonsphinx.R;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter {
    private  Context context;
    /**
     * Żródło danych
     */
    private ArrayList<NameOfOptions> mArticles = new ArrayList<>();


    private RecyclerView mRecyclerView;

    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {
        /**
         * Obiekty przechowywujące referencje do pol tekstowych w CardView.
         */

        public TextView mTitle;
        public TextView mContent;


        public MyViewHolder(View pItem) {
            super(pItem);
            //pobranie aktualnego kontekstu.
            context=pItem.getContext();
            //przypisanie referencji do obiektów
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
        // dla elementu listy ustawiamy obiekt OnClickListener

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int position = mRecyclerView.getChildAdapterPosition(v);
                //w zależności od tego w co kliknął użytkownik do klasy DisplaySettings zostaje ta informacja wysłana w posraci wartości liczbowej
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
        // uzupełniamy layout listy opcji
        NameOfOptions article = mArticles.get(i);
        ((MyViewHolder) viewHolder).mTitle.setText(article.getTitle());
        ((MyViewHolder) viewHolder).mContent.setText(article.getDescription());
    }

    /**
     *
     * Metoda zwracająca rozmiar listy opcji.
     */
    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}

