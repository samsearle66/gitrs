package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToNorthOfDitch extends Task {

    private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3070,3535,0),4);

    //VALIDATE
    //Inventory is not full.
    //Has runes
    //Has staff

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("1WTNOD:"+(me != null)+","+(!Inventory.contains(GC.WINEOFZAMORAK))+","+(!northOfDitch.contains(me))+","+GC.bankingCompleted()+","+ !GC.greaterThanWildernessArea());

        if(me != null && !Inventory.contains(GC.WINEOFZAMORAK) && !northOfDitch.contains(me) && GC.bankingCompleted() && !GC.greaterThanWildernessArea()){
            return true;
        }

        System.out.println("2WTNOD:"+ (me != null) +"&&"+ !northOfDitch.contains(me) +"&&"+ !GC.greaterThanWildernessArea() +"&&"+ GC.greaterThanDitch() +"&&"+ GC.outOfSuppies());

        if (me != null && !northOfDitch.contains(me) && GC.greaterThanWildernessArea() && GC.greaterThanDitch() && GC.outOfSuppies() && !GC.greaterThanLevel20Wilderness() && !GC.greaterThanAlter())
        {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Walk from bank to north wild ditch");

        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(wildernessDitchArea.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(northOfDitch);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch());
            if(GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_RUN);
            else
                path.step();        }
    }
}
