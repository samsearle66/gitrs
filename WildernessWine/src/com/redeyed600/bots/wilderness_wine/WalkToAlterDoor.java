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

    private final Area.Circular alterDoor = new Area.Circular(new Coordinate(2959,3820,0), 2 );//was 4
    public final Area.Circular alterDoorArea = new Area.Circular(new Coordinate(2956,3820,0), 4);//was 4

    private Player me;
    private GameObject door;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();

       System.out.println((me != null) +"&&"+ !alterDoor.contains(me) +"&&"+ ww.GC.bankingCompleted() +"&&"+ !ww.GC.outOfSuppies() +"&&"+  ww.GC.greaterThanLevel20Wilderness() +"&&"+ !ww.GC.greaterThanAlter());//good
            if (me != null && !alterDoor.contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() &&  ww.GC.greaterThanLevel20Wilderness() && !ww.GC.greaterThanAlter())//good
        {
            return true;
        }

        System.out.println("WTAD:"+(me != null)  +"&&"+ !alterDoor.contains(me) +"&&"+  (door!=null)+"&&"+ ww.GC.greaterThanAlter() + ", alterDoorArea="+ alterDoorArea.contains(me));//good
        if(me != null  && !alterDoor.contains(me) &&  door!=null && ww.GC.outOfSuppies() && ww.GC.greaterThanAlter())//good
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

            if(alterDoorArea.contains(me))
                add(new IsDoorOpen(ww));
            path.step();
        }
    }


}
