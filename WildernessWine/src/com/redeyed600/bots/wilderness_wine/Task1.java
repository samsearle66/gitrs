package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class Task1 extends Task {


    @Override
    public boolean validate() {

//        Player player = Players.getLocal();
//
//        LocatableEntityQueryResults target = Players.getLoaded(player);
//
//        System.out.println(target.nearest());

        Player me = Players.getLocal();

        if(me!=null)
            System.out.println("Y:"+me.getPosition().getY());
        else
            System.out.println("Null");

        if(me!=null)
            System.out.println("X:"+me.getPosition().getX());
        else
            System.out.println("Null");


        return false;
    }

    @Override
    public void execute() {

    }
}
