package com.example.harshil.attendenceapp;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${harshil} on 19-02-2018.
 */

public class StudentBean {
    private int rollno;
    private String enrollmentno;
    private String sname;
    private String fname;
    private String gender;
    private String mobileno;
    private String emailid;

    public void addRecord(final Context context){
        String qry = "insert into studentmaster values(" + rollno + ",'" + enrollmentno + "','" + sname + "','" + getFname() + "','" + gender + "','" + mobileno + "','" + emailid + "')";
        final View v =new View(context);
        AndroidNetworking.initialize(context);
        AndroidNetworking.post(Commons.SERVERURL)
                .addBodyParameter("qry",qry)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(v,response,Snackbar.LENGTH_INDEFINITE).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
    public  boolean updateRecord(Context context,int rno){
        String qry="update studentmaster set " +
                "enrollmentno='"+enrollmentno+"',"+
                "sname='"+sname+"',"+
                "fname='"+ getFname() +"',"+
                "gender='"+gender+"',"+
                "mobileno='"+mobileno+"',"+
                "emailid='"+emailid+"'"+
                " where rollno="+rno;

        AndroidNetworking.initialize(context);
        AndroidNetworking.post(Commons.SERVERURL)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

                return true;
    }

    public static List<StudentBean> getAllRecords(JSONObject response) {
        List<StudentBean> list = new ArrayList<StudentBean>();
        try {
            JSONArray arr=response.getJSONArray("data");
            for(int i=0;i<arr.length();i++)
            {
                StudentBean sb=new StudentBean();
                JSONObject obj=arr.getJSONObject(i);
                sb.setRollno(obj.getInt("rollno"));
                sb.setMobileno(obj.getString("mobileno"));
                sb.setGender(obj.getString("gender"));
                sb.setEmailid(obj.getString("emailid"));
                sb.setEnrollmentno(obj.getString("enrollmentno"));
                sb.setFname(obj.getString("fname"));
                sb.setSname(obj.getString("sname"));
                list.add(sb);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
