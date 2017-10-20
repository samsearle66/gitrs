package com.redeyed600.bots.tutorial_bot;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Banks;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class TravelToBank extends Task {

    private final Area.Circular draynorBank = new Area.Circular(new Coordinate(3092,3245,0), 2);


    @Override
    //if bank no where to be found
    public boolean validate() {
        final Player me = Players.getLocal();
        return me != null && Inventory.isFull() && !draynorBank.contains(me);
    }

    @Override
    public void execute() {
        System.out.println("Walk to Bank");
        Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(draynorBank.getRandomCoordinate());
        if(path != null) {
            add(new IsGateOpen());
            path.step();
        }
    }
}
