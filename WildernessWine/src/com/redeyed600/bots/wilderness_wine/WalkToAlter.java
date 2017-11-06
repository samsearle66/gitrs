package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToAlter extends Task {

    wilderness_wine ww;
    private final Area.Circular alter = new Area.Circular(new Coordinate(2951,3818,0),2);


    public WalkToAlter(wilderness_wine ww){
        this.ww = ww;
    }

    //private final Area.Circular alter = new Area.Circular(new Coordinate(2950,3821,0),2);
    //2955,2821
    private Player me;

    @Override
    public boolean validate() {
        me = Players.getLocal();
        return (me != null && !alter.contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && ww.GC.greaterThanAlter());//good
    }

    @Override
    public void execute() {
        System.out.println("Walking to alter");
        final BresenhamPath path = BresenhamPath.buildTo(alter);
        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            if(!alter.contains(me)) {
                add(new IsDoorOpen(ww));
            }
            path.step();
        }
    }
}
