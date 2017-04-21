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


public class VolumeMinus extends Fragment {

    EditText VMinus,VMinus1;
    TextView VMinusT,VMinusT1;
    Button button;
    IVolumeMinus volumeminus;

    public VolumeMinus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_volume_minus, container, false);
        VMinus= (EditText) view.findViewById(R.id.editText);
        VMinus1=(EditText) view.findViewById(R.id.editText5);
        button= (Button) view.findViewById(R.id.button3);
        VMinusT= (TextView) view.findViewById(R.id.textView15);
        VMinusT1= (TextView) view.findViewById(R.id.textView16);

        VMinusT.setText(getArguments().getString("Minus"));
        VMinusT1.setText(String.valueOf(getArguments().getInt("Minus1"))+ " %");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volumeminus.VolumeMinus(VMinus.getText().toString(), Integer.parseInt(VMinus1.getText().toString()));
                VMinusT.setText(VMinus.getText().toString());
                VMinusT1.setText(VMinus1.getText().toString()+ " %");
            }
        });
                return view;
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            volumeminus=(IVolumeMinus) activity;
        }
        catch (Exception ex){}

    }
    public  interface IVolumeMinus{
        public void VolumeMinus(String value, int value1);
    }

}
