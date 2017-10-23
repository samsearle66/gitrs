package com.redeyed600.bots.wilderness_wine;

import com.redeyed600.bots.wilderness_wine.old.IsAtDitch;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class AlterDoorToAlter extends Task {

    private final Area.Circular alter = new Area.Circular(new Coordinate(2950,3821,0),2);


    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        return (me != null && !Inventory.contains("Wine of zamorak") && !Inventory.isFull() && !alter.contains(me) &&
                Inventory.getQuantity("Law rune") >= 1 &&
                Inventory.getQuantity("Fire rune") >= 1 &&
                (Equipment.contains("Staff of air") || Inventory.getQuantity("Air rune") >= 3));    }

    @Override
    public void execute() {
        System.out.println("Walking to alter");
        final BresenhamPath path = BresenhamPath.buildTo(alter);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
