package com.example.harshil.attendenceapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ${harshil} on 26-02-2018.
 */

public class ViewListAdapter extends BaseAdapter {
    List<Bundle> list;
    int layout;
    Context context;

    public  ViewListAdapter(Context context, int layout , List list){
        this.list=list;
        this.layout=layout;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(layout,parent,false);
        TextView rollno=convertView.findViewById(R.id.rollno);
        TextView status= convertView.findViewById(R.id.status);
        TextView sname= convertView.findViewById(R.id.vsname);
        TextView fname= convertView.findViewById(R.id.vfname);

        rollno.setText(list.get(position).getString("rollno"));
        status.setText(list.get(position).getString("status"));
        sname.setText(list.get(position).getString("sname"));
        fname.setText(list.get(position).getString("fname"));


        return convertView;

    }
}
