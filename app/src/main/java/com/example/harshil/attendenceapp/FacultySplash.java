package com.example.harshil.attendenceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class FacultySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_splash);
        getSupportActionBar().hide();
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),FacultyLogin.class));
                finish();
            }
        };
        Timer timer=new Timer();
        timer.schedule(timerTask,1000);
    }
}
