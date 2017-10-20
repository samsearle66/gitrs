package com.redeyed600.bots.tutorial_bot;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class TravelToPotatoArea extends Task {

    private final Area.Circular potatoArea = new Area.Circular(new Coordinate(3147,3284,0), 2);

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();
        return me != null && !Inventory.isFull() && !potatoArea.contains(me);
    }

    @Override
    public void execute() {
        System.out.println("Walk to PotatoArea");
        Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(potatoArea.getRandomCoordinate());
        if(path != null) {
            add(new IsGateOpen());
            path.step();
        }
    }
}
