package com.example.harshil.attendenceapp;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

/**
 * Created by ${harshil} on 19-02-2018.
 */

public class BatchBean {
    private String batchid;
    private String startdate;
    private String enddate;
    private int facultyno;
    private String batchtime;
    private int session;
    private String subjectcode;
    private String coursecode;

    public void addRecord(final Context context){
        String qry = "insert into batchmaster values(" + batchid + ",'" + startdate + "','" + enddate + "','" + facultyno + "','" + batchtime + "','" + session + "','" + subjectcode +"','"+coursecode +"')";

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
    public  boolean updateRecord(Context context,int bid){
        String qry="update batchmaster set " +
                "startdate='"+startdate+"',"+
                "enddate='"+enddate+"',"+
                "facultyno='"+facultyno+"',"+
                "batchtime='"+batchtime+"',"+
                "session='"+session+"',"+
                "subjectcode='"+subjectcode+"'"+
                "coursecode='"+coursecode+"'"+
                " where batchid="+bid;

        return true;
    }



    public int getFacultyno() {
        return facultyno;
    }

    public void setFacultyno(int facultyno) {
        this.facultyno = facultyno;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getBatchtime() {
        return batchtime;
    }

    public void setBatchtime(String batchtime) {
        this.batchtime = batchtime;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }
}
