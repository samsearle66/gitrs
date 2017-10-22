package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.Task;

public class GrabWine extends Task{
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Grab wine");
    }
}
