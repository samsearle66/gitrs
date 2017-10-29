package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;


public class WalkToLumbridgeCastleStairs extends Task {
    private wilderness_wine ww;

    public WalkToLumbridgeCastleStairs(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular lumbridgeStairs = new Area.Circular(new Coordinate(3206,3209,0),2);
    private Player me;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        System.out.println("WTLCS:"+(me != null) +"&&"+ (me.getPosition().getPlane()==0) +"&&"+ !lumbridgeStairs.contains(me) +"&&"+ !ww.GC.greaterThanDitch() +"&&"+ ww.GC.greaterThanLumbridgeCastleLevel1() +"&&"+ !ww.GC.greaterThanVarrockCenter());

        return (me != null && me.getPosition().getPlane()==0 && !lumbridgeStairs.contains(me) && !ww.GC.greaterThanDitch() && ww.GC.greaterThanLumbridgeCastleLevel1()&& !ww.GC.greaterThanVarrockCenter());
    }

    @Override
    public void execute() {
        System.out.println("Walking to lumbridge stairs");
        final BresenhamPath path = BresenhamPath.buildTo(lumbridgeStairs);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }

}
