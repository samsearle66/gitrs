package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        add(new TravelToBank(), new BankInterface(), new TravelToAlterArea());//, new PickPotato());
    }
}
