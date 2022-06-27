package com.example.fighttime;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTimer {
    private int timers_count = 0;
    private Thread thread;
    private final TextView currentStatus;
    private ArrayList<ItemRound> rounds;
    private RvRoundAdapter adapter;

    MyTimer(TextView _currentTimeView, TextView _currentStatus, ArrayList<ItemRound> _rounds){
        System.out.println("MyTimer: MyTimer");
        timers_count = 0;
        currentStatus = _currentStatus;
        rounds = _rounds;

        rounds.add(new ItemRound(
                1,
                1,
                10,
                100,
                _currentTimeView,
                _currentStatus,
                this));
    }

    public void StartRound(){
        System.out.println("MyTimer: StartRound");
        if (rounds.size() != 0){
            rounds.get(0).StartRound();
        }
    }

    public void addRound(ItemRound round){
        System.out.println("MyTimer: addRound");
        rounds.add(round);
        timers_count++;
    }
    public void removeRound(int roundId){
        System.out.println("MyTimer: removeRound");
        rounds.remove(roundId);
        timers_count--;
    }
    public void NextRound(){
        System.out.println("MyTimer: NextRound");
        removeRound(0);
        if(rounds.size() != 0){
            StartRound();
        }
    }
}
