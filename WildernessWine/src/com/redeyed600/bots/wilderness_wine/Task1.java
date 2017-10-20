package com.redeyed600.bots.wilderness_wine;


import com.runemate.game.api.script.framework.task.Task;

public class Task1 extends Task {
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void execute() {
        System.out.println("this bot is working!");
    }
}
