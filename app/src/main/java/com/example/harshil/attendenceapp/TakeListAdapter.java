package com.example.harshil.attendenceapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ${harshil} on 26-02-2018.
 */

public class TakeListAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<StudentBean> list;
    boolean[] attlist;

    public  TakeListAdapter(Context context, int layout, List list,boolean[] attlist){
        this.context=context;
        this.layout=layout;
        this.list=list;
        this.attlist=attlist;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(layout,parent,false);

        TextView rollno=convertView.findViewById(R.id.trollno);
        TextView sname=convertView.findViewById(R.id.tsname);
        TextView fname=convertView.findViewById(R.id.tfname);
        final CheckBox status = convertView.findViewById(R.id.status);
        status.setChecked(attlist[position]);
        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                attlist[position]=isChecked;
            }
        });
        rollno.setText(list.get(position).getRollno()+"");
        sname.setText(list.get(position).getSname());
        fname.setText(list.get(position).getFname());

        return  convertView;
    }
}
