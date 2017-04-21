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


public class BrightnesMinus extends Fragment {
IBrightnesMinus iBrightnesMinus;
    EditText BMEdit,BMEdit1;
    TextView BMText,BMText1;
    Button button;
    public BrightnesMinus() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_brightnes_minus, container, false);

        BMEdit= (EditText) view.findViewById(R.id.editText11);
        BMEdit1= (EditText) view.findViewById(R.id.editText12);

        BMText= (TextView) view.findViewById(R.id.textView25);
        BMText1= (TextView)view.findViewById(R.id.textView26);

       // BMText.setText(getArguments().getString("BrightnesMinus"));
        //BMText1.setText(String.valueOf(getArguments().getInt("BrightnesMinus1")));
        button= (Button) view.findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBrightnesMinus.BrightnesMinus(BMEdit.getText().toString(), Integer.parseInt(BMEdit1.getText().toString()));
                BMText.setText(BMEdit.getText().toString());
                BMText1.setText(BMEdit1.getText().toString());

            }
        });

        return view;
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            iBrightnesMinus=(IBrightnesMinus) activity;
        }
        catch (Exception ex){}

    }
    public interface IBrightnesMinus{
        public void BrightnesMinus(String value, int value1);
    }
}
