package com.example.sebastian.demonsphinx.Settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.sebastian.demonsphinx.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Hubert Stępiński on 04.04.2017.
 */

public class MyExpedableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> list;
    Map<String,List<String>> map;

    public MyExpedableListAdapter(Context context, List<String> list, Map<String, List<String>> map) {
        this.context = context;
        this.list = list;
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map.get(list.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String lang = (String) getGroup(groupPosition);

        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.look_parent,null);
        }

        TextView txt= (TextView) convertView.findViewById(R.id.Parent);
        txt.setText(lang);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       String topic = (String) getChild(groupPosition,childPosition);

    if(convertView==null)
    {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.look_child,null);
    }

    TextView txt= (TextView) convertView.findViewById(R.id.Child);
    txt.setText(topic);
    return convertView;
}

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return  true;
    }
}
