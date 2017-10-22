package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(0);
        add(new WildernessDitchToBank(), new BankInterface(), new BankToWildernessDitch(), new WildernessDitchToAlter(), new AlterToWildernessDitch());//, new PickPotato());
    }
}
