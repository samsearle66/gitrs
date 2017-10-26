package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(700,1200);

        //add(new Task1());

        add(new TravelToBank(), new BankInterface(), new BankToNorthOfDitch(),  new NorthOfDitchToAlterDoor(), new AlterDoorToAlter(), new GrabWine(), new AlterToNorthOfDitch(), new NorthOfDitchToSouthOfDitch());
        //add(new TravelToBank(), new BankInterface());
       // add(new BankToNorthOfDitch());
        //add(new GrabWine());

    }
}
