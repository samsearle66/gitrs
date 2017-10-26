package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToAlterDoor extends Task {

    private final Area.Circular alterDoor = new Area.Circular(new Coordinate(2956,3820,0), 4 );


    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("1WTAD:"+(me != null) +"&&"+ GC.greaterThanLevel20Wilderness() +" && "+ !Inventory.contains(GC.WINEOFZAMORAK) + " && "+ !alterDoor.contains(me) +"&&"+!GC.greaterThanAlter()+"&&"+ GC.bankingCompleted() +"&&"+ GC.greaterThanWildernessArea() +"&&"+ GC.greaterThanDitch());

        if (me != null && !Inventory.contains(GC.WINEOFZAMORAK) && !alterDoor.contains(me) && !GC.greaterThanAlter() && GC.bankingCompleted() && GC.greaterThanWildernessArea() && GC.greaterThanDitch())
        {
            return true;
        }

        System.out.println("2WTAD:"+(me != null) +"&&"+!GC.greaterThanLevel20Wilderness()+ " && "+ !alterDoor.contains(me)+"&&"+ GC.greaterThanAlter() +"&&"+ GC.greaterThanWildernessArea()+"&&"+GC.greaterThanDitch() +"&&"+ GC.outOfSuppies());

        if(me != null && GC.greaterThanLevel20Wilderness() && !alterDoor.contains(me) && GC.greaterThanAlter() && GC.greaterThanWildernessArea() && GC.greaterThanDitch() && GC.outOfSuppies())
        {
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
        final BresenhamPath path = BresenhamPath.buildTo(alterDoor);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsDoorOpen());
            if(GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_RUN);
            else
                path.step();
        }
    }


}
