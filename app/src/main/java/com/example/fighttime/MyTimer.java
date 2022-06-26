package com.example.fighttime;

import android.widget.TextView;

import java.util.ArrayList;

public class MyTimer {
    private int timers_count = 0;
    private Thread thread;
    private final TextView currentStatus;
    private ArrayList<ItemRound> rounds;

    MyTimer(TextView currentTimeView, TextView _currentStatus){
        timers_count = 0;
        currentStatus = _currentStatus;
        rounds = new ArrayList<ItemRound>();
    }

    private void startThread(){

    }

    public void addRound(ItemRound round){
        rounds.add(round);
        timers_count++;
    }
    public void removeRound(int roundId){
        rounds.remove(roundId);
        timers_count--;
    }
    public void NextRound(){
        removeRound(0);
        if(rounds.size() != 0){
            startThread();
        }
    }
}
