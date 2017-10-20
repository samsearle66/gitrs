package com.redeyed600.bots.tutorial_bot;

import com.runemate.game.api.script.framework.task.TaskBot;

public class TutorialBot extends TaskBot {
    @Override
    public void onStart(String... strings) {
        super.onStart(strings);
        add(new TravelToBank(), new BankInterface(), new TravelToPotatoArea(), new PickPotato());
    }
}