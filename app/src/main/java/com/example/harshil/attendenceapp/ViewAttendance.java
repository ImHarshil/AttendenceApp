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

public class ViewAttendance extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        listView = (ListView)findViewById(R.id.viewattendance);
        String sdate=getIntent().getStringExtra("sdate");
        String sbatchid=getIntent().getStringExtra("sbatchid");

        try {
            AndroidNetworking.initialize(getApplicationContext());
            AndroidNetworking.post(Commons.SERVERURL)
                    .addBodyParameter("qry", "select sname,fname,sm.rollno as rno,status from attendancemaster am,studentmaster sm where batchid = '" + sbatchid + "' and attdate = '" + sdate + "' and am.rollno=sm.rollno")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            List<Bundle> list = new ArrayList<Bundle>();
                            try {
                                JSONArray jrr = response.getJSONArray("data");
                                for (int i = 0; i < jrr.length(); i++) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("sname", jrr.getJSONObject(i).getString("sname"));
                                    bundle.putString("fname", jrr.getJSONObject(i).getString("fname"));
                                    bundle.putString("rollno", jrr.getJSONObject(i).getInt("rno")+"");
                                    bundle.putString("status", jrr.getJSONObject(i).getString("status"));

                                    list.add(bundle);
                                }
                                Toast.makeText(getApplicationContext(), list.get(1).get("sname").toString(), Toast.LENGTH_LONG).show();

                                ViewListAdapter vla = new ViewListAdapter(getApplicationContext(), R.layout.viewxml, list);
                                listView.setAdapter(vla);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }


}


