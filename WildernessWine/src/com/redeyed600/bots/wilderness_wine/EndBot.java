package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

import java.awt.*;

public class EndBot extends Task {

    wilderness_wine ww;

    public EndBot(wilderness_wine ww){
        this.ww=ww;
    }

    @Override
    public void execute() {
        RuneScape.logout();
        ClientUI.sendTrayNotification("Wilderness Wine has stopped after number of wine telegrabbed:" + ww.NUMBEROFWINETELEGRABED + ", which took " + ww.runtime.getRuntimeAsString(), TrayIcon.MessageType.INFO);
        ww.stop();
    }

    @Override
    public boolean validate() {
        Player me = Players.getLocal();

        return (me!=null && (Bank.isOpen() && Bank.getQuantity(ww.GC.FIRERUNE)==0 && Bank.getQuantity(ww.GC.LAWRUNE)==0 &&
                Bank.getQuantity(ww.GC.FOODIDS)==0 && Bank.getQuantity(ww.GC.ENERGYPOTION[0])==0 && Bank.getQuantity(ww.GC.STAFFOFAIR)==0));
    }
}
