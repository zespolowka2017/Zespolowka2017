package com.example.sebastian.demonsphinx.EditSettings;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sebastian.demonsphinx.R;
import com.example.sebastian.demonsphinx.Settings.Preferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoiceSettings extends Fragment// implements FunctionLists.SetValue
{
 //Spinner spinner,spinner1;
    EditText text,text1,editText,editText5,editText6,editText7;
    Button button;
    TextView textView,textView13,textView14,txt;
    Preferences preferences;
    SharedPreferences sharedPreferences;
    UserSettings userSettings;
    String odp=" ";
    public VoiceSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view= inflater.inflate(R.layout.fragment_voive, container, false);


        button=(Button)view.findViewById(R.id.button2);
        editText7= (EditText) view.findViewById(R.id.editText7);
        editText6= (EditText) view.findViewById(R.id.editText6);
        txt=(TextView)getActivity().findViewById(R.id.textView12);
        textView13=(TextView)view.findViewById(R.id.textView13);
        textView14= (TextView) view.findViewById(R.id.textView14);
        textView13.setText(getArguments().getString("Plus"));
        textView14.setText(String.valueOf(getArguments().getInt("Plus1"))+ " %");

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

            userSettings.VolumePlus(editText6.getText().toString(), Integer.parseInt(editText7.getText().toString()));
            textView13.setText(editText6.getText().toString());
            textView14.setText(editText7.getText().toString()+" %");


    }
});
       /* ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(this.getActivity(),R.array.TorC,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner1=(Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> arrayAdapter1=ArrayAdapter.createFromResource(getActivity(),R.array.Function,android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(this);
        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Komputer")){
                    spinner.setEnabled(false);
                    Spinner spinner1=(Spinner) view.findViewById(R.id.spinner2);
                    ArrayAdapter<CharSequence> arrayAdapter1=ArrayAdapter.createFromResource(getActivity(),R.array.TorC,android.R.layout.simple_spinner_item);
                    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(arrayAdapter1);
                    //Toast.makeText(getContext(),"fffffff",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        return view;

    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            userSettings=(UserSettings)activity;
        }
        catch (Exception ex){}

    }

  /*  @Override
    public void Set(String text, String EditText, int value) {

    }*/

    public interface UserSettings{

        public void VolumePlus(String value, int value1);

    }
/*@Override
    public void onItemSelected(AdapterView<?> adapterView,View view,int i,long j)
{
    if(adapterView.getItemAtPosition(i).toString().equals("Telefon")) {
       // spinner.setEnabled(false);
       // spinner1.setVisibility(View.VISIBLE);
    }
     if(adapterView.getItemAtPosition(i).toString().equals("Głośniej")){
        // preferences =new Preferences();
         textView.setText("Ustawienia Głośności");

         textView.setVisibility(View.VISIBLE);
        // editText6.setText("aaa");
        spinner1.setEnabled(false);
         textView13.setVisibility(View.VISIBLE);
         textView14.setVisibility(View.VISIBLE);
         button.setVisibility(View.VISIBLE);
         editText6.setVisibility(View.VISIBLE);
       editText6.setText(txt.getText().toString());
         editText7.setVisibility(View.VISIBLE);
         text1.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);


    }
   if(adapterView.getItemAtPosition(i).toString().equals("Ciszej")){
        //spinner.setEnabled(false);
        spinner1.setEnabled(false);
        text.setText(adapterView.getItemAtPosition(i).toString());
        text1.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);

    }
    if(adapterView.getItemAtPosition(i).toString().equals("Ciemniej")){
        textView.setText("Ustawienia Głośności");

        textView.setVisibility(View.VISIBLE);
         editText6.setText("aaa");
        spinner1.setEnabled(false);
        textView13.setVisibility(View.VISIBLE);
        textView14.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        editText6.setVisibility(View.VISIBLE);
        editText6.setText(preferences.Load("VolumePlus"));
        editText7.setVisibility(View.VISIBLE);
        text1.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);

    }
    if(adapterView.getItemAtPosition(i).toString().equals("Jaśniej")){
        //spinner.setEnabled(false);
        spinner1.setEnabled(false);
        text.setText(adapterView.getItemAtPosition(i).toString());
        text1.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);

    }
    //Toast.makeText(this.getContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_LONG).show();
}*/
   // @Override
   // public void onNothingSelected(AdapterView<?> adapterView)
   // {

   // }


    
}
