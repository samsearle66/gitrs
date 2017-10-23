package com.redeyed600.bots.wilderness_wine.walkingtest;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class west extends Task {

    private Area.Circular west = new Area.Circular(new Coordinate(3086,3510,0),3);

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();
        return (me != null && !west.contains(me));
    }

    @Override
    public void execute() {
        System.out.println("Walking to west");
        final BresenhamPath path = BresenhamPath.buildTo(west);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
