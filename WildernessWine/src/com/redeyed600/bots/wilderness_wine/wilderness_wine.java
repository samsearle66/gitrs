package com.redeyed600.bots.wilderness_wine;
import com.runemate.game.api.script.framework.task.TaskBot;

public class wilderness_wine extends TaskBot {

    public GC GC = new GC(this);

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(700,1200);

        //add(new Task1(this));
        //add(new Heal(this), new WalkToBank(this), new BankInterface(this), new WalkToNorthOfDitch(this), new WalkToLevel20Wilderness(this), new WalkToAlterDoor(this), new WalkToAlter(this), new GrabWine(this), new WalkToAlterDoor(this), new WalkToLevel20Wilderness(this), new WalkToNorthOfDitch(this), new WalkToSouthOfDitch(this));
        //add(new WalkToBank(), new BankInterface());
        //add(new GrabWine());
        add(new Bank());

    }
}
