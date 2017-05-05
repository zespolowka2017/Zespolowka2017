package com.example.sebastian.demonsphinx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sebastian.demonsphinx.Adapters.MyAdapter;

import java.util.ArrayList;

/**
 * Klasa opdowiada za przygotowanie danych okreslajacych jakie sa dostepne opcje ustawien
 * wraz z ich opisem.
 * Powstale dane przesylane sa do odpowiedniego adaptera w celu ich wyswietlenia na ekranie telefonu.
 */
public class DisplayOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_options);
        setTitle("Ustawienia Głosowe");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.articles);
        // w celach optymalizacji
        recyclerView.setHasFixedSize(true);

        // ustawiamy LayoutManagera
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // tworzymy źródło danych - tablicę z artykułami
        ArrayList<NameOfOptions> articles = new ArrayList<>();

            articles.add(new NameOfOptions("Zmniejszenie Jasności ","Opcja pozwalająca na edycję słowa kluczowego dla zmniejszenia jasności ekranu telefonu"));
            articles.add(new NameOfOptions("Zwiększenie Jasności","Opcja pozwalająca na edycję słowa kluczowego dla zwiększenia jasności ekranu telefonu"));
            articles.add(new NameOfOptions("Zmiana Trybu","Opcja pozwalająca na edycję słowa kluczowego dla zmainy trybu dzwiękowego telefonu (podgłoszenie)"));
            articles.add(new NameOfOptions("Zmiana Trybu do wibracji","Opcja pozwalająca na edycję słowa kluczowego dla zmainy trybu dzwiękowego telefonu (przyciszanie)"));
             articles.add(new NameOfOptions("Połączenie Głosowe","Opcja pozwalająca na edycję słowa kluczowego dla wykonania połączenia telefonicznego"));
                articles.add(new NameOfOptions("Przeglądarka","Opcja pozwalająca na edycję słowa kluczowego dla uruchomienia przeglądarki www"));
        articles.add(new NameOfOptions("Zmiejszenie poziomu dzwięku mediów","Opcja pozwalająca na edycję słowa kluczowego dla zmniejsenia głośności mediów wraz z jej procentową wartością."));
        articles.add(new NameOfOptions("Zwiększenie poziomu dzwięku mediów","Opcja pozwalająca na edycję słowa kluczowego dla zwiększenia głośności mediów wraz z jej procentową wartością."));
        articles.add(new NameOfOptions("Aparat","Opcja pozwalająca na edycję słowa kluczowego dla uruchomienia aparatu."));



        // tworzymy adapter oraz łączymy go z RecyclerView
        recyclerView.setAdapter(new MyAdapter(articles, recyclerView));
    }
}
