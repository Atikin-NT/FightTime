package com.example.fighttime;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTimer {
    private final TextView currentStatus;
    private final TextView currentTimeView;
    private ArrayList<ItemRound> rounds;
    private RvRoundAdapter adapter;

    MyTimer(TextView _currentTimeView, TextView _currentStatus){
        System.out.println("MyTimer: MyTimer");
        currentStatus = _currentStatus;
        currentTimeView = _currentTimeView;
        rounds = new ArrayList<ItemRound>();;

        rounds.add(new ItemRound(
                1,
                1,
                5,
                10,
                _currentTimeView,
                _currentStatus,
                this));
    }

    public void SetAdapter(RvRoundAdapter _adapter){
        adapter = _adapter;
    }

    public void StartRound(){
        System.out.println("MyTimer: StartRound");
        if (rounds.size() != 0){
            rounds.get(0).StartRound();
        }
    }

    public void PauseRound(){
        System.out.println("MyTimer: StartRound");
        if (rounds.size() != 0){
            rounds.get(0).Pause();
        }
    }

    public void addRound(int breakCount,
                         int fightCount,
                         int breakTime,
                         int fightTime){
        System.out.println("MyTimer: addRound");
        rounds.add(new ItemRound(
                breakCount,
                fightCount,
                breakTime,
                fightTime,
                currentTimeView,
                currentStatus,
                this
        ));
        adapter.notifyItemInserted(adapter.getItemCount());
    }
    public void removeRound(int roundId){
        System.out.println("MyTimer: removeRound");
        rounds.remove(roundId);
        adapter.notifyItemRemoved(roundId);
    }
    public void NextRound(){
        System.out.println("MyTimer: NextRound");
        // <-- TODO вставить звонок на 10 секунд -->
        removeRound(0);
        if(rounds.size() != 0){
            StartRound();
        }
    }

    public ArrayList<ItemRound> getArray(){
        return rounds;
    }
}
