package com.example.fighttime;

import android.widget.TextView;

public class ItemRound {
    int breakCount; // >= 0
    int fightCount; // > 0
    int breakTime; // > 0
    int fightTime; // > 0

    MyTimer myTimer;

    ItemRound(int _breakCount,
              int _fightCount,
              int _breakTime,
              int _fightTime,
              TextView _currentTimeView,
              TextView _currentRoundView,
              MyTimer _nextRound)
    {
        breakCount = _breakCount;
        fightCount = _fightCount;

        breakTime = _breakTime;
        fightTime = _fightTime;
        myTimer = _nextRound;
    }

    public int getRoundsCount(){ return fightCount + breakCount; }

    public int getFightCount() {
        return fightCount;
    }
    public String getFightTime(){
        return "Бой:" + String.valueOf(fightTime / 60) + ":" + String.valueOf(fightTime % 60);
    }
    public String getBreakTime(){
        return "Перерыв:" + String.valueOf(breakTime / 60) + ":" + String.valueOf(breakTime % 60);
    }
}
