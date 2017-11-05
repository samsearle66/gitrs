package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.details.Interactable;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Rune;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.InteractablePoint;
import com.runemate.game.api.hybrid.local.hud.InteractableRectangle;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldSelect;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

import java.awt.*;
import java.util.Random;

public class SwitchWorld extends Task {
    wilderness_wine ww;

    public SwitchWorld(wilderness_wine ww){
        this.ww = ww;
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

        System.out.println("SwitchWorld: if-"+ww.GC.greaterThanLevel20Wilderness() +"..."+ ww.GC.pkersSpotted() +"&&"+ !ww.GC.underAttackPker() +"&&"+ !ww.GC.underAttackNpc() +"||"+ WorldSelect.isOpen() +"||"+ !RuneScape.isLoggedIn() );

        if(ww.GC.greaterThanLevel20Wilderness())
            return ww.GC.pkersSpotted() && !ww.GC.underAttackPker() && !ww.GC.underAttackNpc() || !RuneScape.isLoggedIn();
        return false;
    }

    @Override
    public void execute() {

        //check that rand
        //if pkerspotted set lobby disable
        //if not set to enable

        if(RuneScape.isLoggedIn())
        {
            System.out.println("Logging out.prev"+ww.GC.getPreviousWorld()+"curr"+Worlds.getCurrent());
            GameEvents.Universal.LOBBY_HANDLER.disable();
            GameEvents.Universal.LOGIN_HANDLER.disable();
            ww.GC.setPreviousWorld(Worlds.getCurrent());
            RuneScape.logout();
        }else{
            if(!WorldSelect.isOpen() && Worlds.getCurrent() == ww.GC.getPreviousWorld() || GameEvents.LoginManager.getFailedLoginHandler().equals(GameEvents.LoginManager.Fail.LOGIN_LIMIT_EXCEEDED)){
                randomWorld = worlds[rand.nextInt(worlds.length)];
                System.out.println("Opening World.prev"+ww.GC.getPreviousWorld()+"curr"+Worlds.getCurrent());
                WorldSelect.open();
            }
            if(WorldSelect.isOpen() && Worlds.getCurrent() == ww.GC.getPreviousWorld()){
                System.out.println("Selecting World.prev"+ww.GC.getPreviousWorld()+"curr"+Worlds.getCurrent()+"randomWorld"+randomWorld);
                WorldSelect.select(randomWorld);
            }
            if (WorldSelect.isSelected(randomWorld)){//(ww.GC.getPreviousWorld() != 0 && Worlds.getCurrent() != ww.GC.getPreviousWorld() && !WorldSelect.isOpen()){
                System.out.println("Enable Login.prev"+ww.GC.getPreviousWorld()+"curr"+Worlds.getCurrent());
                GameEvents.Universal.LOGIN_HANDLER.enable();
                //InteractablePoint point = new InteractablePoint(new Point(x, y));
                //InteractableRectangle point = new InteractableRectangle(155)

            }
            /*if Login handler is broken enable lobby handler
                enable lobby handler and login handler
            if(GameEvents.LoginManager.Fail == GameEvents.LoginManager.getFailedLoginHandler())
            if(GameEvents.LoginManager.getFailedLoginHandler().equals(GameEvents.LoginManager.Fail.AUTHENTICATOR)||
                    GameEvents.LoginManager.getFailedLoginHandler().equals(GameEvents.LoginManager.Fail.INVALID_CREDENTIALS) ||
                    GameEvents.LoginManager.getFailedLoginHandler().equals(GameEvents.LoginManager.Fail.JAG_BLOCKED)||
                    GameEvents.LoginManager.getFailedLoginHandler().equals(GameEvents.LoginManager.Fail.LOGIN_LIMIT_EXCEEDED)){

                GameEvents.Universal.LOGIN_HANDLER.enable();
                GameEvents.Universal.LOGIN_HANDLER.enable();
            }
            */
        }
    }
}