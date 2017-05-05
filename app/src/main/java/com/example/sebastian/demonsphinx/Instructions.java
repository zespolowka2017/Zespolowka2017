package com.example.sebastian.demonsphinx;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Klasa odpowiedzialna za wyswietlenie widoku przedstawiajacego instrukcje korzystania z aplikacji wraz z pomoca.
 */
public class Instructions extends Fragment {


    public Instructions() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_instructions, container, false);
            getActivity().setTitle("Instrukcja");
        TextView textView =(TextView)view.findViewById(R.id.hyperlink);
        textView.setClickable(true);
        textView.setLinkTextColor(Color.parseColor("#ffc400"));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://github.com/zespolowka2017/Zespolowka2017/tree/working'> DŻASTALK </a>";
        textView.setText(Html.fromHtml(text));

        return view;
    }

}
