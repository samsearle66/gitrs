package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToLevel20Wilderness extends Task {

    private wilderness_wine ww;
    //private Area.Circular level20Wilderness = new Area.Circular(new Coordinate(2954,3665,0),3);

    private Area.Rectangular level20Wilderness = new Area.Rectangular(new Coordinate(2949,3664,0), new Coordinate(3022, 3667,0));
            //3663 if you want to be safe
    private Player me;
    private GameObject door;
    private Boolean isAtAlter = false;

    public WalkToLevel20Wilderness(wilderness_wine ww){
        this.ww = ww;
    }



    @Override
    public boolean validate() {
        me = Players.getLocal();

        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();

        System.out.println("wilderness position:"+me.getPosition().getY());

        System.out.println("2WTL20W:" + (me != null) + "&&" + !level20Wilderness.contains(me) + "&&" + ww.GC.greaterThanDitch() + "&&" + ww.GC.greaterThanLevel20Wilderness() + "&&" + !(door != null && door.isVisible() && ww.GC.greaterThanAlterY() && ww.GC.lessThanAlterX()));

        if (me != null && !level20Wilderness.contains(me) && ww.GC.greaterThanDitch() && ww.GC.greaterThanLevel20Wilderness() && ww.GC.outOfSuppies() && !(door != null && door.isVisible() && ww.GC.greaterThanAlterY() && ww.GC.lessThanAlterX())) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {

        System.out.println("Walk to 20 wilderness - BresenhamPath");
        final BresenhamPath path = BresenhamPath.buildTo(level20Wilderness);
        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }

        add( new Heal(ww), new EnergyPotion(ww),new RunEnergy(ww));
    }
}
