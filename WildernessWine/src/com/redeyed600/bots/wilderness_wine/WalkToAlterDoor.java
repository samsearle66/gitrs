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

    public wilderness_wine ww;

    public WalkToAlterDoor(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular alterDoor = new Area.Circular(new Coordinate(2956,3820,0), 2 );//was 4
    private Player me;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        System.out.println("1WTAD:"+(me != null) +"&&"+ ww.GC.greaterThanLevel20Wilderness() + " && "+ !alterDoor.contains(me) +"&&"+!ww.GC.greaterThanAlter()+"&&"+ ww.GC.bankingCompleted() +"&&"+ ww.GC.greaterThanWildernessArea() +"&&"+ ww.GC.greaterThanDitch()+"&&"+!ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanVarrockCenter());

        //WINEOFZAMORAK??&& !Inventory.contains(GC.WINEOFZAMORAK)
        if (me != null  && !alterDoor.contains(me) && !ww.GC.greaterThanAlter() && ww.GC.bankingCompleted() && ww.GC.greaterThanWildernessArea() && ww.GC.greaterThanDitch() && !ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter())
        {
            return true;
        }

        System.out.println("2WTAD:"+(me != null) +"&&"+ww.GC.greaterThanLevel20Wilderness()+ " && "+ !alterDoor.contains(me)+"&&"+ ww.GC.greaterThanAlter() +"&&"+ ww.GC.greaterThanWildernessArea()+"&&"+ww.GC.greaterThanDitch() +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanVarrockCenter());

        if(me != null && ww.GC.greaterThanLevel20Wilderness() && !alterDoor.contains(me) && ww.GC.greaterThanAlter() && ww.GC.greaterThanWildernessArea() && ww.GC.greaterThanDitch() && ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter())
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

            if(!alterDoor.contains(me))
                add(new IsDoorOpen(ww));

            if(ww.GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_STAMINA_ENHANCERS);
            else
                path.step();
        }
    }


}
