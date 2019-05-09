package com.example.harshil.attendenceapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacultyLogin extends AppCompatActivity {
    protected Button login;
    protected EditText etpassword, etfacultyno;
    protected CheckBox save;
    protected String sfacultyno, spassword;
    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        login = (Button) findViewById(R.id.login);
        etfacultyno = (EditText) findViewById(R.id.editText);
        etpassword = (EditText) findViewById(R.id.editText2);
        save = (CheckBox) findViewById(R.id.checkBox);

        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("check", "false");
        if (s.equals("true")) {
            save.setChecked(true);
            etfacultyno.setText(sharedPreferences.getString("facultyno", ""));
            etpassword.setText(sharedPreferences.getString("password", ""));
        } else {
            save.setChecked(false);
            etfacultyno.setText("");
            etpassword.setText("");
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sfacultyno = etfacultyno.getText().toString();
                spassword = etpassword.getText().toString();
                if (sfacultyno.equals("")) {
                    etfacultyno.setError("enter valid faculty number");
                    etfacultyno.requestFocus();
                    return;
                }
                if (spassword.equals("")) {
                    etpassword.setError("enter your password");
                    etpassword.requestFocus();
                    return;
                }
                final int fno = Integer.parseInt(sfacultyno);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (save.isChecked()) {

                    editor.putString("check", "true");
                    editor.putString("facultyno", sfacultyno);
                    editor.putString("password", spassword);

                } else {

                    editor.putString("check", "false");
                    editor.putString("facultyno", "");
                    editor.putString("password", "");

                }
                editor.commit();
                //startActivity(new Intent(getApplicationContext(),BatchAndDate.class));

                AndroidNetworking.initialize(getApplicationContext());
                AndroidNetworking.post(Commons.SERVERURL)

                        .addBodyParameter("qry", "select * from facultymaster where facultyno=" + fno + " and password = '" + spassword + "'")
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray arr;
                                try {
                                    arr = response.getJSONArray("data");
                                    if (arr.length() > 0) {
                                        JSONObject obj = arr.getJSONObject(0);
                                        SharedPreferences.Editor edit = sharedPreferences.edit();
                                        edit.putString("tempfno", "" + fno);
                                        edit.commit();
                                        startActivity(new Intent(FacultyLogin.this, BatchAndDate.class));
                                    } else
                                        Snackbar.make(login, "", Snackbar.LENGTH_INDEFINITE).show();
                                } catch (JSONException e) {
                                    //((ViewGroup) v.getParent()).removeView(v);
                                    Snackbar.make(login, e.toString(), Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        }
                                    }).show();
                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                Snackbar.make(login, anError.toString(), Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ((ViewGroup) v.getParent()).removeView(v);
                                    }
                                }).show();
                                Toast.makeText(getApplicationContext(), anError.toString(), Toast.LENGTH_LONG).show();
                            }


                        });


            }


        });
    }
}