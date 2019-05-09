package com.example.harshil.attendenceapp;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

/**
 * Created by ${harshil} on 19-02-2018.
 */

public class FacultyBean {
    private int facultyno;
    private String fname;
    private String gender;
    private String mobileno;
    private String emailid;
    private String password;


    public void addRecord(final Context context){
        String qry = "insert into facultymaster values(" + facultyno + ",'" + fname + "','" + gender + "','" + mobileno + "','" + emailid + "','" + password +  "')";

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
    public  boolean updateRecord(Context context,int fno){
        String qry="update studentmaster set " +
                "fname='"+fname+"',"+
                "gender='"+gender+"',"+
                "mobileno='"+mobileno+"',"+
                "gender='"+gender+"',"+
                "emailid='"+emailid+"'"+
                "password='"+password+"'"+
                " where facultyno="+fno;

        return true;
    }




    public int getFacultyno() {
        return facultyno;
    }

    public void setFacultyno(int facultyno) {
        this.facultyno = facultyno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
