package com.example.fighttime;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTimer {
    private final TextView currentStatus;
    private final TextView currentTimeView;
    private ArrayList<ItemRound> rounds;
    private RvRoundAdapter adapter;
    private HandlerTimer handlerTimer;

    MyTimer(TextView _currentTimeView, TextView _currentStatus){
        System.out.println("MyTimer: MyTimer");
        currentStatus = _currentStatus;
        currentTimeView = _currentTimeView;
        rounds = new ArrayList<ItemRound>();

        handlerTimer = new HandlerTimer(0, 0, currentTimeView, this);
        handlerTimer.runTimer();

        rounds.add(new ItemRound(
                2,
                2,
                5,
                10,
                _currentTimeView,
                _currentStatus,
                this));
    }

    public void SetAdapter(RvRoundAdapter _adapter){
        adapter = _adapter;
    }

    public void StartRound(int roundNumber){
        System.out.println("MyTimer: StartRound");
        handlerTimer.startTimer();
        if (rounds.size() != 0){
            int roundN = roundNumber;
            if(roundN == -100 && handlerTimer.isActive()){
                roundN = rounds.get(0).getRoundsCount();
                handlerTimer.setRoundNumber(roundN);
            }
            ItemRound round = rounds.get(0);
            if(roundN > 0){
                if(roundN % 2 == 0){
                    handlerTimer.setHandlerTime(round.fightTime);
                    currentStatus.setText(R.string.FightTitle);
                }
                else{
                    handlerTimer.setHandlerTime(round.breakTime);
                    currentStatus.setText(R.string.BreakTitle);
                }
            }
            else{
                removeRound(0);
                StartRound(-100);
            }
        }
    }

    public void PauseRound(){
        System.out.println("MyTimer: StartRound");
        handlerTimer.stopTimer();
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
        handlerTimer.stopTimer();
        handlerTimer.reset();
        currentStatus.setText("Безделье");
        rounds.remove(roundId);
        adapter.notifyItemRemoved(roundId);
    }
//    public void NextRound(){
//        System.out.println("MyTimer: NextRound");
//        // <-- TODO вставить звонок на 10 секунд -->
//        removeRound(0);
//        if(rounds.size() != 0){
//            StartRound();
//        }
//    }

    public ArrayList<ItemRound> getArray(){
        return rounds;
    }
}
