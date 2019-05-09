package com.example.harshil.attendenceapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BatchAndDate extends AppCompatActivity {

    Button vattendance, tattendance, totalattendance;
    DatePicker dp;
    Spinner sbatchid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_and_date);


        dp = (DatePicker) findViewById(R.id.datePicker);
        totalattendance = (Button) findViewById(R.id.totalattendance);
        vattendance = (Button) findViewById(R.id.vattendance);
        tattendance = (Button) findViewById(R.id.tattendance);
        sbatchid = (Spinner) findViewById(R.id.batchid);

        sbatchid.setBackgroundColor(Color.WHITE);
        int fno = 0;
        String sfno = getSharedPreferences("MYPREF",Context.MODE_PRIVATE).getString("tempfno", "");
        if (sfno.equals("")) {
            Toast.makeText(getApplicationContext(), "Faculty not logged in", Toast.LENGTH_LONG).show();
            finish();
        } else
            fno = Integer.parseInt(sfno);

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.post(Commons.SERVERURL)
                .addBodyParameter("qry", "select batchid from batchmaster where facultyno = " + fno)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jrr = response.getJSONArray("data");
                            String[] item = new String[jrr.length()];
                            for (int i = 0; i <= jrr.length(); i++) {
                                try {
                                   item[i] = jrr.getJSONObject(i).getString("batchid");
                                } catch (Exception e) {

                                }
                            }
                            ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, item);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sbatchid.setAdapter(aa);


                        } catch (JSONException e) {

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }

                });


        vattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdate = dp.getYear() + "-" + (dp.getMonth()+1) + "-" + dp.getDayOfMonth();
                String sbatch = sbatchid.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), ViewAttendance.class);
                intent.putExtra("sdate", sdate);
                intent.putExtra("sbatchid", sbatch);
                startActivity(intent);
            }
        });
        tattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdate = dp.getYear() + "-" + (dp.getMonth()+1) + "-" + dp.getDayOfMonth();
                String sbatch = sbatchid.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), TakeAttendance.class);
                intent.putExtra("sdate", sdate);
                intent.putExtra("sbatchid", sbatch);
                startActivity(intent);
            }
        });
        totalattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sbatch = sbatchid.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(),TotalAttendance.class);
                intent.putExtra("sbatchid", sbatch);
                startActivity(intent);
            }
        });
    }
}
