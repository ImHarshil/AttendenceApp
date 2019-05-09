package com.example.harshil.attendenceapp;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ${harshil} on 19-02-2018.
 */

public class AttendenceBean {
    private int rollno;
    private String batchid;
    private String attdate;
    private String status;
    private String topic;

    public void addRecord(final Context context){
        String qry = "insert into attendancemaster values(" + rollno + ",'" + batchid + "','" + attdate + "','" + status + "','" + topic + "')";

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
        String qry="update attendancemaster set " +
                "batchid='"+batchid+"',"+
                "attdate='"+attdate+"',"+
                "status='"+status+"',"+
                "topic='"+topic+"',"+
                " where rollno="+rno;
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

        return true;
    }

    public static ArrayList<AttendenceBean> showAllRecords(JSONObject jsonObject){
            ArrayList<AttendenceBean> list =new ArrayList<>();
        try {
            JSONArray jrr = jsonObject.getJSONArray("data");
            for(int i=0;i<jrr.length();i++){

                JSONObject obj= jrr.getJSONObject(i);
                AttendenceBean ab =new AttendenceBean();
                ab.setAttdate(obj.getString("attdate"));
                ab.setBatchid(obj.getString("batchid"));
                ab.setRollno(obj.getInt("rollno"));
                ab.setStatus(obj.getString("status"));
                ab.setTopic(obj.getString("topic"));
                list.add(ab);
            }
        }
        catch (Exception e){

        }

          return  list;
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

    public String getAttdate() {
        return attdate;
    }

    public void setAttdate(String attdate) {
        this.attdate = attdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
