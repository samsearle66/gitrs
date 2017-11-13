package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToAlterDoor extends Task {

    public wilderness_wine ww;
    Web web;
    private Boolean isAtAlter = true;

    public WalkToAlterDoor(wilderness_wine ww){
        this.ww = ww;

        try {
            web = SerializableWeb.deserialize(ww.GC.getByteArray("src\\Resources\\wildernessUpper.nav"));
        } catch (Exception e) {
            e.printStackTrace();
            web = null;
        }
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

        System.out.println("1WTAD:"+(me != null) +"&&"+ !alterDoor.contains(me) +"&&"+ ww.GC.bankingCompleted() +"&&"+ !ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanDitch() +"&&"+ ww.GC.greaterThanNorthOfDitch() +"&&"+ !ww.GC.greaterThanAlterY());
        if (me != null && !alterDoor.contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && ww.GC.greaterThanDitch() && ww.GC.greaterThanNorthOfDitch() && !ww.GC.greaterThanAlterY())//good
        {
            isAtAlter = false;
            return true;
        }

        System.out.println("2WTAD:"+(me != null)  +"&&"+ !alterDoor.contains(me) +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanAlterY());//good
        if(me != null  && !alterDoor.contains(me) && ww.GC.outOfSuppies() && ww.GC.greaterThanAlterY())//good
        {
            isAtAlter = true;
            return true;
        }
        return false;
    }

    @Override
    public void execute() {

        if(isAtAlter) {
            System.out.println("Walk to alter door - BresemhamPath");

            final BresenhamPath path = BresenhamPath.buildTo(alterDoor);

            if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash

                if (!alterDoor.contains(me)) {
                    if (door != null && door.isValid() && door.isVisible())
                        add(new IsDoorOpen(ww));
                    else
                        path.step();
                }
            }
        }else {

            System.out.println("Walk to alter door - Custom");
            WebPath path = null;

            if (web != null) { // Make sure the web got loaded properly
                path = web.getPathBuilder().buildTo(alterDoor);
            } else {
                System.out.println("dis web is null");
            }


            if (path != null) { // IMPORTANT: if the path should be null, the pathbuilder could not manage to build a path with the given web, so always nullcheck!
                path.step();
            } else {
                System.out.println("dis path is null");
            }
        }
    }
}
