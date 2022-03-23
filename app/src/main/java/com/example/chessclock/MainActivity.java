package com.example.chessclock;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView TextViewCountDown;
    private TextView TextViewCountDown2;
    private Button BStartPause;
    private Button BReset;

    private CountDownTimer CountDownTimer;

    private boolean TimerRun;

    private long TimeLeft = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextViewCountDown = findViewById(R.id.text_view_countdown);
        TextViewCountDown2 = findViewById(R.id.text_view_countdown2);

        BStartPause = findViewById(R.id.button_start_pause);
        BReset = findViewById(R.id.button_reset);

        BStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRun) {
                    pause();
                } else {
                    startTimer();
                }
            }
        });

        BReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        update();
    }

    private void startTimer() {
        CountDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished;
                update();
            }

            @Override
            public void onFinish() {
                TimerRun = false;
                BStartPause.setText("Start");
                BStartPause.setVisibility(View.INVISIBLE);
                BReset.setVisibility(View.VISIBLE);
            }
        }.start();

        TimerRun = true;
        BStartPause.setText("pause");
        BReset.setVisibility(View.INVISIBLE);
    }
    private void reset() {
        TimeLeft = START_TIME_IN_MILLIS;
        update();
        BReset.setVisibility(View.INVISIBLE);
        BStartPause.setVisibility(View.VISIBLE);
    }
    private void update() {
        int minutes = (int) (TimeLeft / 1000) / 60;
        int seconds = (int) (TimeLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        TextViewCountDown.setText(timeLeftFormatted);
    }
    private void pause() {
        CountDownTimer.cancel();
        TimerRun = false;
        BStartPause.setText("Start");
        BReset.setVisibility(View.VISIBLE);
    }

}