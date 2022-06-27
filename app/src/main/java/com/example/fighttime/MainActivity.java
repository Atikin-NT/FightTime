package com.example.fighttime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<ItemRound> roundArrayList = new ArrayList<ItemRound>();
    MyTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView currentTimeView = (TextView) findViewById(R.id.timeLeft);
        TextView currentStatus = (TextView) findViewById(R.id.roundStatus);
        timer = new MyTimer(currentTimeView, currentStatus, roundArrayList);


        RecyclerView recyclerView = findViewById(R.id.rvRoundList);
        RvRoundAdapter adapter = new RvRoundAdapter(roundArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button startBtn = (Button) findViewById(R.id.BtnStartTimer);
        Button stopBtn = (Button) findViewById(R.id.BtnStopTimer);
    }

    public void startTimerClick(View view) {
        timer.StartRound();
    }
}