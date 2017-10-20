package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class TravelToBank extends Task {

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3496,0), 2);


    @Override
    //if bank no where to be found
    public boolean validate() {
        final Player me = Players.getLocal();
        return me != null /*&& Inventory.isFull() */&& !edgevilleBank.contains(me);
    }

    @Override
    public void execute() {
        System.out.println("Walk to Edgeville Bank");
        Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgevilleBank.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }
    }
}
