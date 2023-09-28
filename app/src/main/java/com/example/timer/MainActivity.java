package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView timerTxt;
    private boolean isGoing;

    private int seconds;

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerTxt = findViewById(R.id.timerTxt);
        timerTxt.setText("00:00:00");
        isGoing = false;
    }

    public void startTimer(View v){
        if(!isGoing){
            timer = new Timer();
            isGoing = true;
            runTimer();
        }
    }

    public void stopTimer(View v){
        if(isGoing){
            isGoing = false;
            timer.cancel();
        }
    }

    public void resetTimer(View v){
        if(isGoing){
            isGoing = false;
            timer.cancel();
        }
        seconds = 0;
        timerTxt = findViewById(R.id.timerTxt);
        timerTxt.setText("00:00:00");
    }

    private void runTimer(){
        timerTxt = findViewById(R.id.timerTxt);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                seconds+=1;
                int h = seconds/3600;
                int m = (seconds%3600)/60;
                int s = seconds%60;
                String time = String.format("%02d:%02d:%02d",h,m,s);
//                String time = h + ":" + m + ":" + s;
                timerTxt.setText(time);
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

}