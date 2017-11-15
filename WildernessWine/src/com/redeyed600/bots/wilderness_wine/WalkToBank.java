package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.script.framework.task.Task;

import java.io.File;
import java.net.URL;

public class WalkToBank extends Task {
    private wilderness_wine ww;
    Boolean southOfDitch = false;
    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3494,0), 2);
    private Player me;

    public WalkToBank(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    //if bank no where to be found
    public boolean validate() {
        me = Players.getLocal();
        System.out.println("WTB:"+(me != null) +"&&"+ !edgevilleBank.contains(me) +"&&"+ ww.GC.outOfSuppies() +"&&"+ !ww.GC.greaterThanDitch() +"&&"+ ww.GC.greaterThanVarrockCenter());
        return (me != null && !edgevilleBank.contains(me) && ww.GC.outOfSuppies() && !ww.GC.greaterThanDitch() && ww.GC.greaterThanVarrockCenter());
    }

    @Override
    public void execute() {
        System.out.println("Walking to edgeville bank");

        WebPath path = null;

        if (ww.varrock != null) { // Make sure the web got loaded properly
            path = ww.varrock.getPathBuilder().buildTo(edgevilleBank);
        }else{System.out.println("dis web is null");}

        if (path != null) { // IMPORTANT: if the path should be null, the pathbuilder could not manage to build a path with the given web, so always nullcheck!
            add(new IsAtDitch(ww));
            path.step();
        }else{
            System.out.println("dis path is null");
        }
    }
}
