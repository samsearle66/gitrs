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

public class AlterToNorthOfDitch extends Task {

    private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3070,3535,0),4);
    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        return (me != null && !northOfDitch.contains(me) && me.getPosition().getY() > 3519 &&
                (Inventory.getQuantity("Law rune") < 1 ||
                        (!Equipment.contains("Staff of air") && Inventory.getQuantity("Air rune") < 3)||
                        Inventory.isFull()));
    }

    //WEB WALK THIS ONE
    @Override
    public void execute() {
        System.out.println("Walking to north of ditch");
        final BresenhamPath path = BresenhamPath.buildTo(northOfDitch);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsDoorOpen());
            path.step();
        }
    }


}
