package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToBank extends Task {
    private wilderness_wine ww;

    public WalkToBank(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3494,0), 2);
    private Player me;

    @Override
    //if bank no where to be found
    public boolean validate() {
        me = Players.getLocal();
        System.out.println("WTB:"+(me!=null)+","+!edgevilleBank.contains(me)+","+!ww.GC.greaterThanDitch()+","+ww.GC.outOfSuppies()+"&&"+ ww.GC.greaterThanVarrockCenter());

        return (me != null && !edgevilleBank.contains(me)&& !ww.GC.greaterThanDitch() && ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter());
    }

    @Override
    public void execute() {
        System.out.println("Walk to edgeville bank");
        Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgevilleBank);
        if(path != null) {
            if(ww.GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_RUN);
            else
                path.step();
        }
//        final BresenhamPath path = BresenhamPath.buildTo(edgevilleBank);
//
//        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
//            if(ww.GC.underAttack())
//                path.step(Path.TraversalOption.MANAGE_RUN);
//            else
//                path.step();
//        }
    }
}
