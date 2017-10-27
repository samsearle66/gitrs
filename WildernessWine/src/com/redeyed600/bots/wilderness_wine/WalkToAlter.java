package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToAlter extends Task {

    //private final Area.Circular alter = new Area.Circular(new Coordinate(2950,3821,0),2);
    private final Area.Circular alter = new Area.Circular(new Coordinate(2955,3820,0),1);
    //2955,2821
    final Player me = Players.getLocal();

    @Override
    public boolean validate() {

        System.out.println("WTA:"+(me != null)+"&&"+GC.greaterThanLevel20Wilderness() +"&&"+ !alter.contains(me) +"&&"+ GC.bankingCompleted() +"&&"+ GC.greaterThanAlter() );
        return (me != null && GC.greaterThanLevel20Wilderness() && !alter.contains(me) && GC.bankingCompleted() && GC.greaterThanAlter());
    }

    @Override
    public void execute() {
        System.out.println("Walking to alter");
        final BresenhamPath path = BresenhamPath.buildTo(alter);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsDoorOpen());
            if(GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_RUN);
            else
                path.step();
        }
    }
}
