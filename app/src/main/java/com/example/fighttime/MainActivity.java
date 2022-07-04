package com.example.fighttime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
    MyTimer timer;
    private boolean isRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView currentTimeView = (TextView) findViewById(R.id.timeLeft);
        TextView currentStatus = (TextView) findViewById(R.id.roundStatus);
        timer = new MyTimer(currentTimeView, currentStatus, this);

        RecyclerView recyclerView = findViewById(R.id.rvRoundList);
        RvRoundAdapter adapter = new RvRoundAdapter(timer);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        timer.SetAdapter(adapter);

        Button startBtn = (Button) findViewById(R.id.BtnStartTimer);
        if(isRun)
            startBtn.setText("Stop");
        else
            startBtn.setText("Start");
    }

    public void startTimerClick(View view) {
        Button button = (Button) view;
        if(isRun){
            timer.PauseRound();
            button.setText("START");
        }
        else{
            timer.StartRound(-100); // число раундов у 1 ячейки массива
            button.setText("PAUSE");
        }
        isRun = !isRun;
    }

    public void addRoundClick(View view) {
        RoundDialogFragment dialog = new RoundDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void addRoundAfterDialog(String editTextFightMin,
                                    String editTextFightSec,
                                    String editTextBreakMin,
                                    String editTextBreakSec,
                                    String editTextCount){
        editTextFightMin = editTextFightMin.length() == 0 ? "0" : editTextFightMin;
        editTextFightSec = editTextFightSec.length() == 0 ? "0" : editTextFightSec;
        editTextBreakMin = editTextBreakMin.length() == 0 ? "0" : editTextBreakMin;
        editTextBreakSec = editTextBreakSec.length() == 0 ? "0" : editTextBreakSec;
        editTextCount = editTextCount.length() == 0 ? "0" : editTextCount;
        timer.addRound(
                Integer.parseInt(editTextCount),
                Integer.parseInt(editTextCount),
                Integer.parseInt(editTextBreakMin) * 60 + Integer.parseInt(editTextBreakSec),
                Integer.parseInt(editTextFightMin) * 60 + Integer.parseInt(editTextFightSec)
        );
    }
}