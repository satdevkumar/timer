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

public class MainActivity extends AppCompatActivity {


        private Chronometer chronometer;
        private long pauseOffset;
        private boolean running;

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            chronometer = findViewById(R.id.chronometer);
            chronometer.setFormat("Time: %s");
            chronometer.setCountDown(true);
            chronometer.setBase(SystemClock.elapsedRealtime());

            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if ((SystemClock.elapsedRealtime() - chronometer.getBase()) == 10000) {
                      //  chronometer.setBase(SystemClock.elapsedRealtime());
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

                findViewById(R.id.btStart).setVisibility(View.VISIBLE);
                findViewById(R.id.btPause).setVisibility(View.GONE);
            }
        }
//pause timer value
        public void pauseChronometer(View v) {
            if (running) {
                chronometer.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;
                findViewById(R.id.btStart).setVisibility(View.GONE);
                findViewById(R.id.btPause).setVisibility(View.VISIBLE);
            }
        }
//reset timer value
        public void resetChronometer(View v) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
        }
    }