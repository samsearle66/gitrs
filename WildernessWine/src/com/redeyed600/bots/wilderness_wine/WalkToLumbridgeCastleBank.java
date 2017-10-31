package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToLumbridgeCastleBank extends Task {

    private wilderness_wine ww;

    public WalkToLumbridgeCastleBank(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular lumbridgeCastleBank = new Area.Circular(new Coordinate(3209,3219,2),3);


    private Player me;

    @Override
    public boolean validate() {
        me = Players.getLocal();
        System.out.println("WTLCB:"+(me != null) +"&&"+ (me.getPosition().getPlane() == 2) +"&&"+ !lumbridgeCastleBank.contains(me) +"&&"+ !ww.GC.greaterThanDitch() +"&&"+ ww.GC.greaterThanLumbridgeCastleLevel1() +"&&"+ !ww.GC.greaterThanLumbridgeCastleLevel3() +"&&"+ !ww.GC.greaterThanVarrockCenter());

        return (me != null && (me.getPosition().getPlane() == 2) && !lumbridgeCastleBank.contains(me) && !ww.GC.greaterThanDitch() && ww.GC.greaterThanLumbridgeCastleLevel1() && !ww.GC.greaterThanLumbridgeCastleLevel3() && !ww.GC.greaterThanVarrockCenter());
    }

    @Override
    public void execute() {
        System.out.println("Walking to lumbridge bank");
        final BresenhamPath path = BresenhamPath.buildTo(lumbridgeCastleBank);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
