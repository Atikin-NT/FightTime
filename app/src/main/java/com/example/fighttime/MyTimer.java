package com.example.fighttime;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyTimer {
    private final TextView currentStatus;
    private final TextView currentTimeView;
    private ArrayList<ItemRound> rounds;
    private RvRoundAdapter adapter;
    private HandlerTimer handlerTimer;

    private SoundPool sp;
    private int soundIdShot;


    MyTimer(TextView _currentTimeView, TextView _currentStatus, Context context){
        System.out.println("MyTimer: MyTimer");
        currentStatus = _currentStatus;
        currentTimeView = _currentTimeView;
        rounds = new ArrayList<ItemRound>();

        handlerTimer = new HandlerTimer(0, 0, currentTimeView, this);
        handlerTimer.runTimer();


        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sp = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(1).build();
        soundIdShot = sp.load(context, R.raw.boxing_bell, 1);

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
        if (rounds.size() != 0){
            handlerTimer.startTimer();
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
            sp.play(soundIdShot, 1, 1, 0, 0, 1);
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
        if(breakCount == 0 || breakTime == 0 || fightTime == 0) return;
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
