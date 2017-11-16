package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Interactable;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.InteractablePoint;
import com.runemate.game.api.hybrid.local.hud.InteractableRectangle;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldSelect;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;

import java.awt.*;
import java.util.Random;

public class SwitchWorld extends Task {
    wilderness_wine ww;

    public SwitchWorld(wilderness_wine ww){
        this.ww = ww;
        randomWorld = worlds[rand.nextInt(worlds.length)];
    }
    private Player me;
    private Random rand = new Random();
    private int randomWorld;

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

        System.out.println("SW:("+ww.GC.pkersSpotted()+"&&"+ !ww.GC.underAttackPker() +"&&"+ !ww.GC.underAttackNpc());
        if(ww.GC.greaterThanLevel20Wilderness())
            return ww.GC.pkersSpotted() && !ww.GC.underAttackPker() && !ww.GC.underAttackNpc();
        return false;
    }

    @Override
    public void execute() {
        if(Magic.getSelected()!=null && Magic.getSelected().isSelected())
            Magic.getSelected().deactivate();

        if (ww.GC.pkersSpotted() && RuneScape.isLoggedIn())
            randomWorld = worlds[rand.nextInt(worlds.length)];

        if(Worlds.getCurrent() != randomWorld)
        {
            if(WorldHop.isOpen()) {
                System.out.println("Switch to world " + randomWorld);
                ww.WINELOSTATTEMP=5;
                if(ww.GC.underAttackPker() && ww.GC.underAttackNpc())
                    return;
                WorldHop.hopTo(randomWorld);
            }else{
                if(ww.GC.underAttackPker() && ww.GC.underAttackNpc())
                    return;
                WorldHop.open();
            }
        }
    }
}