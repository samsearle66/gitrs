package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToLevel20Wilderness extends Task {

    private final Area.Circular level20Wilderness = new Area.Circular(new Coordinate(2983,3660,0),6);


    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("1WTL20W:"+(me != null)+"&&"+(!Inventory.contains(GC.WINEOFZAMORAK))+","+(!level20Wilderness.contains(me))+","+ !GC.greaterThanLevel20Wilderness() +","+GC.bankingCompleted());

        if(me != null && !Inventory.contains(GC.WINEOFZAMORAK) && !level20Wilderness.contains(me) && !GC.greaterThanLevel20Wilderness() && GC.bankingCompleted())
        {
            return true;
        }

        System.out.println("2WTL20W:"+ (me != null) +"&&"+ !level20Wilderness.contains(me) +"&&"+ GC.greaterThanLevel20Wilderness() +"&&"+ !GC.greaterThanWildernessArea() +"&&"+ GC.greaterThanDitch() +"&&"+ GC.outOfSuppies());

        if (me != null && !level20Wilderness.contains(me) && GC.greaterThanLevel20Wilderness() && GC.greaterThanWildernessArea() && GC.greaterThanDitch() && GC.outOfSuppies()){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Walk to alter door");
        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgevilleBank.getRandomCoordinate());
        if(path != null) {
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(level20Wilderness);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            if(GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_RUN);
            else
                path.step();        }
    }
}
