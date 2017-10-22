package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class NorthOfDitchToAlterDoor extends Task {

    private final Area.Absolute alterDoor = new Area.Absolute(new Coordinate(2958,3820,0));

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        return (me != null && !Inventory.contains("Wine of zamorak") && !Inventory.isFull() && !alterDoor.contains(me) &&
                Inventory.getQuantity("Law rune") >= 1 &&
                Inventory.getQuantity("Fire rune") >= 1 &&
                (Equipment.contains("Staff of air") || Inventory.getQuantity("Air rune") >= 3));
    }

    @Override
    public void execute() {
        System.out.println("Walk to alter door");
        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgevilleBank.getRandomCoordinate());
        if(path != null) {
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(alterDoor);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }

    }


}
