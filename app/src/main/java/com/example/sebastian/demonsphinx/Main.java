package com.example.sebastian.demonsphinx;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main extends Fragment {

    private  ImageView imageView;

    public Main() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);

        imageView= (ImageView) view.findViewById(R.id.imageView2);


        final ToggleButton btnServiceStartStop = (ToggleButton) view.findViewById(R.id.btnStartStopService);

        btnServiceStartStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent serviceIntent = new Intent(buttonView.getContext(), ListenService.class);

                if(isChecked) {
                    imageView.setImageResource(R.drawable.transitionon);
                    ((TransitionDrawable) imageView.getDrawable()).startTransition(2000);

                    getActivity().startService(serviceIntent);

                } else {
                    imageView.setImageResource(R.drawable.transitionoff);
                    ((TransitionDrawable) imageView.getDrawable()).startTransition(2000);
                    getActivity().stopService(serviceIntent);

                }

            }
        });

        return view;

    }
    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        TextView textView= (TextView) this.getActivity().findViewById(R.id.info);
        TextView textView1= (TextView) this.getActivity().findViewById(R.id.conectName);
        textView.setText(sharedPreferences.getString("IP"," ").toString());
        textView1.setText(sharedPreferences.getString("NAME"," ").toString());
    }
}
