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
    }

    private final Area.Circular outsideAlterDoor = new Area.Circular(new Coordinate(2961,3820,0), 3);//was 4

    //private final Area.Rectangular outsideAlterDoor = new Area.Rectangular(new Coordinate(2959,3818,0), new Coordinate(2960,3822,0));
//2959
    //3816

    private Player me;
    private GameObject door;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();
        return(me != null  && !outsideAlterDoor.contains(me) && ww.GC.outOfSuppies() && ww.GC.greaterThanAlterY());//good
    }

    @Override
    public void execute() {

            System.out.println("Walk to alter door - BresemhamPath");

            final BresenhamPath path = BresenhamPath.buildTo(outsideAlterDoor);

            if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash

                if (ww.alterDoor.contains(me) && door != null && door.isValid() && ww.GC.lessThanAlterX())
                    add(new IsDoorOpen(ww));
                else
                    path.step();
            }
        add( new Heal(ww), new EnergyPotion(ww),new RunEnergy(ww));
    }
}
