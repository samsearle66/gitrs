package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(0);
        add(new TravelToBank(), new BankInterface(),new BankToNorthOfDitch(),new NorthOfDitchToAlterDoor(),
                new AlterDoorToAlter(), new AlterToNorthOfDitch(), new NorthOfDitchToSouthOfDitch());
        //add(new GrabWine());

    }
}
