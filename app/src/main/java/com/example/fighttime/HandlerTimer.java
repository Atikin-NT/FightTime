package com.example.fighttime;

import android.os.Handler;
import android.widget.TextView;

public class HandlerTimer {
    int handlerTime;
    int roundNumber;
    TextView currentTimeView;
    boolean pause;
    MyTimer myTimer;

    HandlerTimer(int _handlerTime, int _roundNumber, TextView _currentTimeView, MyTimer _myTimer){
        handlerTime = _handlerTime;
        currentTimeView = _currentTimeView;
        pause = true;
        roundNumber = _roundNumber;
        myTimer = _myTimer;
    }

    public void setHandlerTime(int time){ handlerTime = time; }
    public void setRoundNumber(int number){ roundNumber = number; }
    public void stopTimer(){ pause = true; }
    public void startTimer(){ pause = false; }
    public boolean isActive(){ return !pause; }
    public void reset(){
        pause = true;
        roundNumber = 0;
        handlerTime = 0;
        currentTimeView.setText("00:00");
    }

    public void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int min = handlerTime / 60;
                int secs = handlerTime % 60;
                String strTime = String.format("%02d:%02d", min, secs);
                currentTimeView.setText(strTime);
                if(!pause){
                    handlerTime--;
                    if (handlerTime < 0){
                        stopTimer();
                        roundNumber--;
                        myTimer.StartRound(roundNumber);
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
