package com.redeyed600.bots.wilderness_wine;


import com.runemate.game.api.hybrid.local.SpellBook;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;

public class Teleport extends Task {

    private wilderness_wine ww;

    public Teleport(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    public boolean validate() {

        //(!greaterThanVarrockCenter || wildernessDepth < 20 && underAttack) && hasrunes
        System.out.println("Teleport:"+!ww.GC.greaterThanVarrockCenter() +"&&"+ ww.GC.hasVarrockTeleportRunes());

        return !ww.GC.greaterThanVarrockCenter() && ww.GC.hasVarrockTeleportRunes();

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
