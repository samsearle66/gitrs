package com.redeyed600.bots.wilderness_wine;


import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;

public class Teleport extends Task {

    private wilderness_wine ww;

    public Teleport(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    public boolean validate() {

        Player me = Players.getLocal();


        //(!greaterThanVarrockCenter || wildernessDepth < 20 && underAttackPker) && hasrunes
        System.out.println("Teleport:"+ww.GC.greaterThanDitch() +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.hasVarrockTeleportRunes() +"&&"+ !ww.GC.greaterThanLevel20Wilderness());


        return (me.isVisible() && !ww.GC.greaterThanVarrockCenter() && ww.GC.hasVarrockTeleportRunes()) || (ww.GC.greaterThanDitch() && ww.GC.outOfSuppies() && ww.GC.hasVarrockTeleportRunes() && !ww.GC.greaterThanLevel20Wilderness());

    }

    @Override
    public void execute() {
        //spell selected
        if(Magic.VARROCK_TELEPORT.activate())
        {
            System.out.println("Teleport to varrock");
        } else{
            System.out.println("Cant teleport?.. teleblocked?...wildernessdepth");
        }
    }
}
