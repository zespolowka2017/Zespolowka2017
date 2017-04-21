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

/**
 * A simple {@link Fragment} subclass.
 */
public class Call extends Fragment {

EditText CEdit;
    Button button;
    TextView CText;
    ICall iCall;
    public Call() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_call, container, false);

        CEdit= (EditText) view.findViewById(R.id.editText8);
        button= (Button) view.findViewById(R.id.button4);
        CText= (TextView) view.findViewById(R.id.textView18);
        CText.setText(getArguments().getString("Call"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCall.Call(CEdit.getText().toString());
                CText.setText(CEdit.getText().toString());

            }
        });
        return view;
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            iCall=(ICall) activity;
        }
        catch (Exception ex){}

    }
public interface ICall{
    public void Call(String value);
}
}
