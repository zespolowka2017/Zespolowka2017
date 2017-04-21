package com.example.sebastian.demonsphinx.EditSettings;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sebastian.demonsphinx.R;


public class BrightnesPlus extends Fragment {

IBrightnesPlus iBrightnesPlus;
    EditText BPEdit,BPEdit1;
    TextView BPText,BPText1;
    Button button;
    public BrightnesPlus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_brightnes_plus, container, false);
        BPEdit= (EditText) view.findViewById(R.id.editText9);
        BPEdit1= (EditText) view.findViewById(R.id.editText10);

        BPText= (TextView) view.findViewById(R.id.textView21);
        BPText1= (TextView)view.findViewById(R.id.textView22);

       BPText.setText(getArguments().getString("BPlus"));
       BPText1.setText(String.valueOf(getArguments().getInt("BPlus1")));
        button= (Button) view.findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBrightnesPlus.BrightnesPlus(BPEdit.getText().toString(), Integer.parseInt(BPEdit1.getText().toString()));

                BPText.setText(BPEdit.getText().toString());
                BPText1.setText(BPEdit1.getText().toString());
            }
        });
        return view;
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            iBrightnesPlus=(IBrightnesPlus) activity;
        }
        catch (Exception ex){}

    }
    public interface IBrightnesPlus{
        public void BrightnesPlus(String value, int value1);
    }
}
