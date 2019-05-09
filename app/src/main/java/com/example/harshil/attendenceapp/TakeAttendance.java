package com.example.harshil.attendenceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TakeAttendance extends AppCompatActivity {

    Button submit;
    ListView listView;
    boolean[] attist;
    List<StudentBean> list;
    String sbatchid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        submit=(Button)findViewById(R.id.submit);
        listView=(ListView)findViewById(R.id.takeattendance);
        final EditText topic=(EditText)findViewById(R.id.topic);
         list =new ArrayList<StudentBean>();
        sbatchid=getIntent().getStringExtra("sbatchid");

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.post(Commons.SERVERURL)
                .addBodyParameter("qry","select * from studentmaster  where rollno in (select rollno from studentbatch where batchid = '"+sbatchid+"')")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jrr = response.getJSONArray("data");
                            for(int i=0;i<jrr.length();i++){
                                StudentBean sb= new StudentBean();
                                sb.setRollno(jrr.getJSONObject(i).getInt("rollno"));
                                sb.setSname(jrr.getJSONObject(i).getString("sname"));
                                sb.setFname(jrr.getJSONObject(i).getString("fname"));
                                list.add(sb);
                            }
                            attist=new boolean[list.size()];
                            TakeListAdapter tla =new TakeListAdapter(getApplicationContext(),R.layout.takexml,list,attist);
                            listView.setAdapter(tla);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stopic=topic.getText().toString();
                String sdate=getIntent().getStringExtra("sdate");
                String sbatchid=getIntent().getStringExtra("sbatchid");

                if(stopic.equals("")){
                    topic.setError("enter topic");
                    topic.requestFocus();
                    return;
                }
                for (int i=0;i<list.size();i++){
                    AttendenceBean ab =new AttendenceBean();
                    ab.setTopic(stopic);
                    ab.setStatus((attist[i]?'P':'A')+"");
                    ab.setBatchid(sbatchid);
                    ab.setAttdate(sdate);
                    ab.setRollno(list.get(i).getRollno());
                    ab.addRecord(getApplicationContext());
                }
                Toast.makeText(getApplicationContext(),"attendance taken successfully",Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }
}
