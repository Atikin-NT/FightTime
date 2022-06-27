package com.example.fighttime;

import android.widget.TextView;

public class MyTread implements Runnable{
    private int sec;
    private int min;
    private final TextView currentTimeView;
    private boolean pause;
    private boolean STOP = false;
    ItemRound doNext;

    MyTread(int _sec, int _min, TextView _currentTimeView, ItemRound _doNext){
        System.out.println("MyTread: MyTread");
        sec = _sec;
        min = _min;
        pause = false;
        currentTimeView = _currentTimeView;
        doNext = _doNext;
    }
    @Override
    public void run() {
        System.out.println("MyTread: run()");
//        showTime();
        while(true){
            if(!getPauseStatus()) {
                sec--;
                if (sec < 0) {
                    sec = 59;
                    min--;
                }
                if (min < 0) {
                    break;
                }
            }
            showTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(getStopStatus())
                break;
        }
        min = sec = 0;
        showTime();
        doNext.StartRound();
    }
    private void showTime(){
        System.out.println("MyTread: showTime");
        String string = String.format("%d:%d", min, sec);
        currentTimeView.setText(string);
    }

    private boolean getPauseStatus(){
        System.out.println("MyTread: getPauseStatus, pause:" + pause);
        return pause;
    }
    public void ChangePause(){
        System.out.println("MyTread: ChangePause, pause->" + !pause);
        pause = !pause;
    }

    private boolean getStopStatus(){
        System.out.println("MyTread: getStopStatus, STOP:" + STOP);
        return STOP;
    }
    public void ChangeStop(){
        STOP = !STOP;
    }

    public void SetData(int _sec, int _min){
        sec = _sec;
        min = _min;
    }
}
