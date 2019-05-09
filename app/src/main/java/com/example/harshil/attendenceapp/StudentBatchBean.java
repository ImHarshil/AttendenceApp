package com.example.harshil.attendenceapp;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

/**
 * Created by ${harshil} on 19-02-2018.
 */

public class StudentBatchBean {
    private int rollno;
    private String batchid;

    public void addRecord(final Context context){
        String qry = "insert into studentbatchmaster values(" + rollno + ",'" + batchid + "')";

        AndroidNetworking.initialize(context);
        AndroidNetworking.post(Commons.SERVERURL)
                .addBodyParameter("qry",qry)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
    public  boolean updateRecord(Context context,int rno){
        String qry="update studentbatchmaster set " +
                "batchid='"+batchid+"',"+
                " where rollno="+rno;

        return true;
    }



    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
}
