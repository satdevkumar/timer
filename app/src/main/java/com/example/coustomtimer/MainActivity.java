package com.example.coustomtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


        private Chronometer chronometer;
        private long pauseOffset;
        private boolean running;
        private  Button btPause,btStart;

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btStart=  findViewById(R.id.btStart);
            btPause=findViewById(R.id.btPause);
            btStart.setOnClickListener(this);
            btPause.setOnClickListener(this);
            chronometer = findViewById(R.id.chronometer);
            chronometer.setFormat("Time: %s");
            chronometer.setCountDown(true);
            chronometer.setBase(SystemClock.elapsedRealtime());


            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if ((SystemClock.elapsedRealtime() - chronometer.getBase()) == 0) {
                        // timer value is 0 then stop timer to not go into nag tive values
                        chronometer.stop();
                        Toast.makeText(MainActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
// start timer value
        public void startChronometer(View v) {
            if (!running) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                chronometer.start();
                running = true;

               btStart.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);
            }
        }
//pause timer value
        public void pauseChronometer(View v) {
            if (running) {
                chronometer.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;
                btStart.setVisibility(View.VISIBLE);
               btPause.setVisibility(View.GONE);
            }
        }
//reset timer value
        public void resetChronometer(View v) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btStart:
                startChronometer(view);
                break;
                case R.id.btPause:
                pauseChronometer(view);
                break;
        }
    }
}