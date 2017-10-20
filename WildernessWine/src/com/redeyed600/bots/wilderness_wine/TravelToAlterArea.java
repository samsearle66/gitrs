package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class TravelToAlterArea extends Task {

    private final Area.Circular wildernessAlter = new Area.Circular(new Coordinate(3088,3529,0), 2);

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();
        return me != null && !Inventory.isFull() && !wildernessAlter.contains(me);
    }

    @Override
    public void execute() {
        System.out.println("Walk to wilderness alter");
        Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(wildernessAlter.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }
    }
}
