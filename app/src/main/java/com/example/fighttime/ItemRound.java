package com.example.fighttime;

import android.widget.TextView;

public class ItemRound {
    private int breakCount; // >= 0
    private int fightCount; // > 0
    private int breakTime; // > 0
    private int fightTime; // > 0
    private Thread thread;
    private MyTread myTread;
    private int totalRoundCount;

    TextView currentRoundView;
    MyTimer nextRound;

    ItemRound(int _breakCount,
              int _fightCount,
              int _breakTime,
              int _fightTime,
              TextView currentTimeView,
              TextView _currentRoundView,
              MyTimer _nextRound)
    {
        breakCount = _breakCount;
        fightCount = _fightCount;
        totalRoundCount = breakCount + fightCount + 1;

        breakTime = _breakTime;
        fightTime = _fightTime;
        currentRoundView = _currentRoundView;
        nextRound = _nextRound;
        myTread = new MyTread(0, 0, currentTimeView, this);
        thread = new Thread(myTread);
    }

    public void StartRound(){
        totalRoundCount--;
        if(fightCount != 0){
            if(totalRoundCount % 2 == 0) // break
            {
                myTread.SetData(breakTime - breakTime % 60, breakTime % 60);
                currentRoundView.setText("Break");
            }
            else // fight
            {
                myTread.SetData(fightTime - fightTime % 60, fightTime % 60);
                currentRoundView.setText("Fight");
            }
            thread = new Thread(myTread);
            thread.start();
        }
        else{
            nextRound.NextRound();
        }
    }

    public void Pause(){
        myTread.ChangePause();
    }

    public void ForceStop(){
        myTread.ChangeStop();
    }
}
