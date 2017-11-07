package com.redeyed600.bots.wilderness_wine;


import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class Teleport extends Task {

    private wilderness_wine ww;

    public Teleport(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    public boolean validate() {

        Player me = Players.getLocal();

        return (me!=null && !Bank.isOpen() && me.isVisible() && !ww.GC.greaterThanVarrockCenter() && ww.GC.hasVarrockTeleportRunes()) || (me!=null && !Bank.isOpen() && ww.GC.greaterThanDitch() && ww.GC.outOfSuppies() && ww.GC.hasVarrockTeleportRunes() && !ww.GC.greaterThanLevel20Wilderness());

    }

    @Override
    public void execute() {
        //spell selected
        if(InterfaceWindows.getInventory().isOpen()) {
            if (Magic.VARROCK_TELEPORT.activate()) {
                Execution.delayWhile(() -> Magic.VARROCK_TELEPORT.getSpriteIdWhenAvailable()!=-1, 3000, 4000);
                System.out.println("Teleport to varrock");
            } else {
                System.out.println("Cant teleport?.. teleblocked?...wildernessdepth");
            }
        }else{
            InterfaceWindows.getInventory().open();
        }
    }
}
