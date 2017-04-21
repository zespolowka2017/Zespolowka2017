package com.example.sebastian.demonsphinx;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Connections extends Fragment {


    public Connections() {
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
        FunDapter adapter=new FunDapter(Connections.this.getActivity(),
                computerLists,
                R.layout.list_look,
                dictionary);

        View view= inflater.inflate(R.layout.add_connections, container, false);
        ListView compuerList=(ListView)view.findViewById(R.id.computers);
        compuerList.setAdapter(adapter);






        return view;
    }

}
