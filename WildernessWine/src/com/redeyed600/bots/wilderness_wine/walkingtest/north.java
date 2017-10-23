package com.redeyed600.bots.wilderness_wine.walkingtest;

import com.redeyed600.bots.wilderness_wine.IsDoorOpen;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class north extends Task {

    private Area.Absolute north = new Area.Absolute(new Coordinate(3095,3517,0));

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();
        return (me != null && !north.contains(me));
    }

    @Override
    public void execute() {
        System.out.println("Walking to north");
        final BresenhamPath path = BresenhamPath.buildTo(north);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
