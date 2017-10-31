package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;

public class WalkToNorthOfDitch extends Task {

    private wilderness_wine ww;

    public WalkToNorthOfDitch(wilderness_wine ww){

        this.ww = ww;
    }

    private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3070,3535,0),4);
    private Player me;

    private Web web; // Custom web, gets loaded in constructor


    //VALIDATE
    //Inventory is not full.
    //Has runes
    //Has staff

    @Override
    public boolean validate() {
        me = Players.getLocal();

        //WINEOFZAMORAK??&& !Inventory.contains(GC.WINEOFZAMORAK)
        System.out.println("1WTNOD:"+(me != null)+","+!northOfDitch.contains(me)+","+ww.GC.bankingCompleted()+","+ !ww.GC.greaterThanWildernessArea()+","+!ww.GC.outOfSuppies()+"&&+"+ ww.GC.greaterThanVarrockCenter() +"&&"+ ww.GC.greaterThanSouthOfDitch());

        if(me!= null && !northOfDitch.contains(me) && ww.GC.bankingCompleted() && !ww.GC.greaterThanWildernessArea() && !ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter() && ww.GC.greaterThanSouthOfDitch()){
            return true;
        }

        System.out.println("2WTNOD:"+ (me != null) +"&&"+ !northOfDitch.contains(me) +"&&"+ ww.GC.greaterThanWildernessArea() +"&&"+ ww.GC.greaterThanDitch() +"&&"+ ww.GC.outOfSuppies() + "&&"+ !ww.GC.greaterThanLevel20Wilderness() +"&&"+ !ww.GC.greaterThanAlter() +"&&"+ ww.GC.greaterThanVarrockCenter());

        if (me != null && !northOfDitch.contains(me) && ww.GC.greaterThanWildernessArea() && ww.GC.greaterThanDitch() && ww.GC.outOfSuppies() && !ww.GC.greaterThanLevel20Wilderness() && !ww.GC.greaterThanAlter()&& ww.GC.greaterThanVarrockCenter())
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
