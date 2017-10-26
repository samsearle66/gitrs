package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class AlterToNorthOfDitch extends Task {

    private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3070,3535,0),4);
    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("ATNOD:"+ (me != null) +"&&"+ !northOfDitch.contains(me) +"&&"+ !GC.greaterThanWildernessArea() +"&&"+ GC.greaterThanDitch() +"&&"+ GC.outOfSuppies());

        return (me != null && !northOfDitch.contains(me) && GC.greaterThanWildernessArea() && GC.greaterThanDitch() && GC.outOfSuppies());
    }

    //WEB WALK THIS ONE
    @Override
    public void execute() {
        System.out.println("Walking to north of ditch");
        final BresenhamPath path = BresenhamPath.buildTo(northOfDitch);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsDoorOpen());
            path.step();
        }
    }


}
