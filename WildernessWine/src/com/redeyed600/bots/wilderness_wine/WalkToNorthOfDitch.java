package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToNorthOfDitch extends Task {

    private wilderness_wine ww;

    public WalkToNorthOfDitch(wilderness_wine ww){

        this.ww = ww;
    }

    private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3070,3535,0),8);
    private Player me;

    private Web web; // Custom web, gets loaded in constructor


    //VALIDATE
    //Inventory is not full.
    //Has runes
    //Has staff

    @Override
    public boolean validate() {
        me = Players.getLocal();

        if(me!= null && !northOfDitch.contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && !ww.GC.greaterThanDitch() && ww.GC.greaterThanSouthOfDitch()){//good
            return true;
        }

        System.out.println("WTNOD:"+(me != null) +"&&"+ !northOfDitch.contains(me) +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanNorthOfDitch() +"&&"+ !ww.GC.greaterThanLevel20Wilderness());
        if (me != null && !northOfDitch.contains(me) && ww.GC.outOfSuppies() && ww.GC.greaterThanNorthOfDitch() && !ww.GC.greaterThanLevel20Wilderness())//good
        {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("walking to north of ditch");
        final BresenhamPath path = BresenhamPath.buildTo(northOfDitch);
        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch(ww));
            path.step();
        }
    }
}
