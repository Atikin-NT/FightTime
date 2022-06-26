package com.example.fighttime;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int secondsBreak = 0;
    private int secondsFight = 0;
    private int deltaBreak = 0;
    private int deltaFight = 0;
    private int deltaAlarm = 0;

    private int count = 0;
    private int round_Count = 0;

    private boolean running = false;
    private boolean alarmTime = true;
    private boolean isPlaySound = false;

    final int MAX_STREAMS = 1;
    SoundPool sp;
    int soundIdShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner minBreak = (Spinner) findViewById(R.id.setMinBreak);
        Spinner secBreak = (Spinner) findViewById(R.id.setSecBreak);
        Spinner minFight = (Spinner) findViewById(R.id.setMinFight);
        Spinner secFight = (Spinner) findViewById(R.id.setSecFight);

        String[] timeArr = new String[60];
        for (int i = 0; i < 60; i++){
            timeArr[i] = String.valueOf(i);
        }

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeArr);
        minBreak.setAdapter(timeAdapter);
        secBreak.setAdapter(timeAdapter);
        minFight.setAdapter(timeAdapter);
        secFight.setAdapter(timeAdapter);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundIdShot = sp.load(this, R.raw.sound, 1);
    }

    public void onClickStartTimer(View view){
        running = false;
        EditText FightCountField = (EditText)findViewById(R.id.setRoundCount);
        int roundCount = Integer.parseInt(String.valueOf(FightCountField.getText()));

        count = roundCount + roundCount - 1;
        round_Count = roundCount;

        Spinner minBreak = (Spinner) findViewById(R.id.setMinBreak);
        Spinner secBreak = (Spinner) findViewById(R.id.setSecBreak);
        Spinner minFight = (Spinner) findViewById(R.id.setMinFight);
        Spinner secFight = (Spinner) findViewById(R.id.setSecFight);

        int min_Break = Integer.parseInt(String.valueOf(minBreak.getSelectedItem()));
        int sec_Break = Integer.parseInt(String.valueOf(secBreak.getSelectedItem()));

        deltaBreak = sec_Break + min_Break * 60;
        secondsBreak = deltaBreak * (roundCount - 1);

        int min_Fight = Integer.parseInt(String.valueOf(minFight.getSelectedItem()));
        int sec_Fight = Integer.parseInt(String.valueOf(secFight.getSelectedItem()));

        deltaFight = sec_Fight + min_Fight * 60;
        secondsFight = deltaFight * roundCount;

        deltaAlarm = 10 * count;


        running = true;
        runTimer();
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        ResetData();
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (secondsFight==0) {
                    ResetData();
                    return;
                }
                if (alarmTime){
                    alarmRun();
                }
                else{
                    if(count%2==1){
                        fightTime();
                    }
                    else{
                        breakTime();
                    }
                }
            }

            private void breakTime(){
                TextView breakOrFight = (TextView) findViewById(R.id.breakOrFight);
                breakOrFight.setText("Сейчас: Перерыв");

                int min = ((secondsBreak - deltaBreak * (secondsBreak / deltaBreak))%3600)/60;
                int secs = (secondsBreak - deltaBreak * (secondsBreak / deltaBreak))%60;

                String time = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                timeView.setText(time);

                if(running) secondsBreak--;

                if(secondsBreak % deltaBreak == 0){
                    count--;
                    alarmTime = true;
                }

                handler.postDelayed(this, 1000);
            }

            private void fightTime(){
                TextView breakOrFight = (TextView) findViewById(R.id.breakOrFight);
                breakOrFight.setText("Сейчас: Бой");

                int min = ((secondsFight - deltaFight * (secondsFight / deltaFight))%3600)/60;
                int secs = (secondsFight - deltaFight * (secondsFight / deltaFight))%60;

                String time = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                timeView.setText(time);

                TextView roundCountField = (TextView) findViewById(R.id.roundCount);
                String roundCountText = String.format(Locale.getDefault(), "Прошло подходов: %d", round_Count - secondsFight / deltaFight);
                roundCountField.setText(roundCountText);

                if(running) secondsFight--;

                if(secondsFight % deltaFight == 0){
                    count--;
                    alarmTime = true;
                }

                handler.postDelayed(this, 1000);
            }
            private void alarmRun(){
                TextView breakOrFight = (TextView) findViewById(R.id.breakOrFight);
                breakOrFight.setText("Сейчас: Звонок");

                int secs = deltaAlarm%10;

                String time = String.format(Locale.getDefault(), "00:%02d", secs);
                timeView.setText(time);

                if(running) deltaAlarm--;
                if (!isPlaySound){
                    sp.play(soundIdShot, 1, 1, 0, 0, 1);
                    isPlaySound = true;
                }
                if(deltaAlarm%10 == 0) {
                    alarmTime = false;
                    isPlaySound = false;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void ResetData(){
        running = false;
        alarmTime = false;

        TextView timeView = (TextView) findViewById(R.id.timeView);
        timeView.setText("00:00");
        TextView roundCountField = (TextView) findViewById(R.id.roundCount);
        roundCountField.setText("Прошло подходов: 0");
    }
}