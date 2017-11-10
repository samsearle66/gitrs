package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToAlterDoor extends Task {

    public wilderness_wine ww;

    public WalkToAlterDoor(wilderness_wine ww){
        this.ww = ww;
    }

    //private final Area.Circular alterDoor = new Area.Circular(new Coordinate(2959,3820,0), 1 );//was 4

    private final Area.Rectangular alterDoor = new Area.Rectangular(new Coordinate(2959,3818,0), new Coordinate(2960,3822,0));
//2959
    //3816

    private Player me;
    private GameObject door;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();

        if (me != null && !alterDoor.contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && !ww.alter.contains(me) && ww.GC.greaterThanDitch() && ww.GC.greaterThanNorthOfDitch() && !ww.GC.greaterThanAlter())//good
        {
            return true;
        }

        System.out.println("WTAD:"+(me != null)  +"&&"+ !alterDoor.contains(me) +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanAlter());//good
        if(me != null  && !alterDoor.contains(me) && ww.GC.outOfSuppies() && ww.GC.greaterThanAlter())//good
        {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Walk to alter door");

        final BresenhamPath path = BresenhamPath.buildTo(alterDoor);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash

            if(!alterDoor.contains(me)) {
                if (door != null && door.isValid())
                    add(new IsDoorOpen(ww));
                else
                    path.step();
            }
        }
    }


}
