package com.example.harshil.attendenceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TotalAttendance extends AppCompatActivity {
ListView listView ;
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);
        String batchid=getIntent().getStringExtra("sbatchid");

          list= new ArrayList<HashMap>();

        listView=(ListView)findViewById(R.id.totalxml);
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.post(Commons.SERVERURL)
                .addBodyParameter("qry","select am.rollno,sname,fname,count(status) as att from attendancemaster am,studentmaster sm where am.rollno=sm.rollno and status='P' and batchid = '"+batchid+"' group by am.rollno,sname,fname" )
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                            JSONArray jrr = response.getJSONArray("data");
                            for(int i=0;i<jrr.length();i++) {
                                HashMap hm = new HashMap();
                                hm.put("sname", jrr.getJSONObject(i).get("sname"));
                                hm.put("fname", jrr.getJSONObject(i).get("fname"));
                                hm.put("rollno", jrr.getJSONObject(i).get("rollno"));
                                hm.put("att", jrr.getJSONObject(i).get("att"));
                                list.add(hm);
                            }
                                TotalListAdapter tla =new TotalListAdapter(getApplicationContext(),R.layout.totalxml,list);
                                listView.setAdapter(tla);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
