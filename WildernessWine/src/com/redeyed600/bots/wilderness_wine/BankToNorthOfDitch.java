package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class BankToNorthOfDitch extends Task {

    private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3070,3535,0),4);

    //VALIDATE
    //Inventory is not full.
    //Has runes
    //Has staff

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("BTNOD:"+(me != null)+","+(!Inventory.contains(GC.WINEOFZAMORAK))+","+(!northOfDitch.contains(me))+","+GC.bankingCompleted()+","+ !GC.greaterThanWildernessArea());

        return (me != null && !Inventory.contains(GC.WINEOFZAMORAK) && !northOfDitch.contains(me) && GC.bankingCompleted() && !GC.greaterThanWildernessArea());
    }

    @Override
    public void execute() {
        System.out.println("Walk from bank to wild ditch");

        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(wildernessDitchArea.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(northOfDitch);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch());
            path.step();
        }
    }
}
