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

//WALK FROM WILDERNESS TO EDGEVILLE
public class NorthOfDitchToSouthOfDitch extends Task {

    private final Area.Circular SouthOfDitch = new Area.Circular(new Coordinate(3087,3516,0),3);

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        return (me != null && !SouthOfDitch.contains(me) && me.getPosition().getY() > 3519 &&
                (Inventory.getQuantity("Law rune") < 1 ||
                        (!Equipment.contains("Staff of air") && Inventory.getQuantity("Air rune") < 3)||
                        Inventory.isFull()));
    }

    @Override
    public void execute() {
        System.out.println("Walking from north of ditch to south of ditch ");
        final BresenhamPath path = BresenhamPath.buildTo(SouthOfDitch);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch());
            path.step();
        }
    }


}
