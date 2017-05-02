package com.example.sebastian.demonsphinx;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Klasa odpowiedzialna za wyświetlanie widoku fragment_about.xml.
 */
public class About extends Fragment {


    public About() {
        // Required empty public constructor
    }

    /**
     * Metoda wyświetlająca layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view= inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }

}
