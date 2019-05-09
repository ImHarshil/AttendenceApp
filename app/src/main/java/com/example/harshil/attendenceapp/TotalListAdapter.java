package com.example.harshil.attendenceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ${harshil} on 26-02-2018.
 */

public class TotalListAdapter extends BaseAdapter {
    List<HashMap> list;
    Context context;
    int layout;
    public TotalListAdapter(Context context, int layout, List list){
        this.context=context;
        this.layout=layout;
        this.list=list;

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
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       convertView= layoutInflater.inflate(layout,parent,false);
        TextView sname=convertView.findViewById(R.id.sname);
        TextView fname=convertView.findViewById(R.id.fname);
        TextView rollno=convertView.findViewById(R.id.rollno);
        TextView attendance=convertView.findViewById(R.id.attendance);

        sname.setText(list.get(position).get("sname").toString());
        fname.setText(list.get(position).get("fname").toString());
        rollno.setText(list.get(position).get("rollno").toString());
        attendance.setText(list.get(position).get("att").toString());

        return convertView;
    }
}

