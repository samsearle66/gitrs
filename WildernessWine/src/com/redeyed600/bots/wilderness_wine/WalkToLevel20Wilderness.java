package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToLevel20Wilderness extends Task {

    private wilderness_wine ww;
    private Area.Circular level20Wilderness = new Area.Circular(new Coordinate(2954,3665,0),3);

    public WalkToLevel20Wilderness(wilderness_wine ww){
        this.ww = ww;
    }


    private Player me;
    private GameObject door;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();


        if(me!=null && !level20Wilderness.contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && ww.GC.greaterThanDitch() && !ww.GC.greaterThanLevel20Wilderness())//good
        {
            return true;
        }

        if (me != null && !level20Wilderness.contains(me) && ww.GC.greaterThanLevel20Wilderness() && ww.GC.outOfSuppies() && door==null){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Walk to 20 wilderness");
        final BresenhamPath path = BresenhamPath.buildTo(level20Wilderness);
        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
