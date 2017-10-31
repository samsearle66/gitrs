package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.WorldOverview;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldSelect;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.queries.WorldQueryBuilder;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

import java.util.Random;
import java.util.function.Predicate;

public class SwitchWorld extends Task {
    wilderness_wine ww;

    public SwitchWorld(wilderness_wine ww){
        this.ww = ww;
    }
    private Player me;
    Random rand = new Random();

    int[] worlds = {301,
            308,
            316,
            326,
            335,
            382,
            383,
            384,
            393,
            394};


    @Override
    public boolean validate() {
        me = Players.getLocal();
        return me != null && ww.GC.pkersSpotted() && ww.GC.greaterThanLevel20Wilderness() && !ww.GC.underAttack();
    }

    @Override
    public void execute() {
        int randomWorld = worlds[rand.nextInt(worlds.length)];
        if(Worlds.getCurrent() != randomWorld){
            System.out.println("Switching worlds");
            WorldHop.hopTo(randomWorld);
        }
    }
}