package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(700,1200);

        add(new Task1());

        add(new Heal(), new WalkToBank(), new BankInterface(), new WalkToNorthOfDitch(), new WalkToLevel20Wilderness(), new WalkToAlterDoor(), new WalkToAlter(), new GrabWine(), new WalkToAlterDoor(), new WalkToLevel20Wilderness(), new WalkToNorthOfDitch(), new WalkToSouthOfDitch());
        //add(new WalkToBank(), new BankInterface());
       // add(new WalkToNorthOfDitch());
        //add(new GrabWine());

    }
}
