package com.redeyed600.bots.wilderness_wine.walkingtest;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class south extends Task {

    private Area.Circular south = new Area.Circular(new Coordinate(3096,3503,0),3);

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();
        return (me != null && !south.contains(me));
    }

    @Override
    public void execute() {
        final Player me = Players.getLocal();

        System.out.println("Walking to south");
        final BresenhamPath path = BresenhamPath.buildTo(south);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
