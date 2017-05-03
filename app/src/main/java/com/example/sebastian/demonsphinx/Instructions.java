package com.example.sebastian.demonsphinx;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Klasa odpowiedzialna za wyswietlenie widoku przedstawiajacego instrukcje korzystania z aplikacji wraz z pomoca.
 */
public class Instructions extends Fragment {


    public Instructions() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_instructions, container, false);
    }

}
