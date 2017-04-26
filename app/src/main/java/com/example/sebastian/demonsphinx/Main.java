package com.example.sebastian.demonsphinx;


import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main extends Fragment {

ImageView imageView;
    boolean turnOn=false;
    public Main() {
       // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final ArrayList<ComputerList> computerLists=new ArrayList<>();
        ComputerList comp1=new ComputerList("Stacja1","192.168.1.1","aaa:ddad:12fd");
        ComputerList comp2=new ComputerList("Domowy","192.168.1.22","aaa:00:12fd");
        ComputerList comp3=new ComputerList("Maniek","192.168.4.12","3ca:df:12fd");

        computerLists.add(comp1);
        computerLists.add(comp2);
        computerLists.add(comp3);



        final BindDictionary<ComputerList> dictionary=new BindDictionary<>();
        dictionary.addStringField(R.id.compName, new StringExtractor<ComputerList>() {
            @Override
            public String getStringValue(ComputerList computerList, int position) {
                return computerList.getName();

            }
        });
        dictionary.addStringField(R.id.ip, new StringExtractor<ComputerList>() {
            @Override
            public String getStringValue(ComputerList computerList, int position) {
                return "" + computerList.getIp();
            }
        });
        dictionary.addStringField(R.id.mc, new StringExtractor<ComputerList>() {
            @Override
            public String getStringValue(ComputerList computerList, int position) {
                return "" + computerList.getMcAdress();
            }
        });
        FunDapter adapter=new FunDapter(Main.this.getActivity(),
                computerLists,
                R.layout.list_look,
                dictionary);

        View view= inflater.inflate(R.layout.fragment_main, container, false);

        imageView= (ImageView) view.findViewById(R.id.imageView2);
        ListView compuerList=(ListView)view.findViewById(R.id.computerList);
        compuerList.setAdapter(adapter);

        final ToggleButton tbtn= (ToggleButton) view.findViewById(R.id.connect);
        compuerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tbtn.setVisibility(view.VISIBLE);

            }
        });

        final ToggleButton btnServiceStartStop = (ToggleButton) view.findViewById(R.id.btnStartStopService);

        btnServiceStartStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    startService(new Intent(, ListenService.class));
//                } else {
//                    getActivity().stopService(new Intent(getActivity(),ListenService.class));
//                }

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

}
