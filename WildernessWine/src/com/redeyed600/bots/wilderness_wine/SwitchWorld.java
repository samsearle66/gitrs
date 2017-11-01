package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldSelect;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

import java.util.Random;
import java.util.logging.Logger;

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

        if(ww.GC.greaterThanLevel20Wilderness())
            return ww.GC.pkersSpotted() && !ww.GC.underAttackPker() && !ww.GC.underAttackNpc() || WorldSelect.isOpen() || !RuneScape.isLoggedIn() ;
        return false;
    }

    @Override
    public void execute() {
        int randomWorld = worlds[rand.nextInt(worlds.length)];
            if(Worlds.getCurrent() != randomWorld){
                if(WorldSelect.isOpen() || !RuneScape.isLoggedIn()) {
                    System.out.println("Switching worlds with world select");
                    WorldSelect.open();
                    WorldSelect.select(randomWorld);
                }else{
                    RuneScape.logout();
                }
            }
    }
}