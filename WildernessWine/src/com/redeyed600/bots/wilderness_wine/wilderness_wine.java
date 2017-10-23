package com.redeyed600.bots.wilderness_wine;

import com.redeyed600.bots.wilderness_wine.walkingtest.east;
import com.redeyed600.bots.wilderness_wine.walkingtest.north;
import com.redeyed600.bots.wilderness_wine.walkingtest.south;
import com.redeyed600.bots.wilderness_wine.walkingtest.west;
import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(700,1200);

        //add(new Task1());

        //add(new TravelToBank(), new BankInterface(),new BankToNorthOfDitch(), new AlterDoorToAlter(), new GrabWine(), new AlterToNorthOfDitch(), new NorthOfDitchToSouthOfDitch());
        //add(new GrabWine());

        add(new south(), new west(), new north(), new east());

    }
}
