package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

//WALK FROM WILDERNESS TO EDGEVILLE
public class WalkToSouthOfDitch extends Task {

    private wilderness_wine ww;
    private final Area.Circular SouthOfDitch = new Area.Circular(new Coordinate(3087,3512,0),6);
    Player me;
    Boolean southOfDitch = false;


    public WalkToSouthOfDitch(wilderness_wine ww){
        this.ww = ww;
    }
    @Override
    public boolean validate() {

        me = Players.getLocal();


        System.out.println("1WTSOD"+(me!= null) +"&&"+ !SouthOfDitch.contains(me) +"&&"+ ww.GC.bankingCompleted() +"&&"+ !ww.GC.greaterThanSouthOfDitch() +"&&"+ ww.GC.greaterThanVarrockCenter() +"&&"+ !ww.GC.outOfSuppies());
        if(me!= null && !SouthOfDitch.contains(me) && ww.GC.bankingCompleted() && !ww.GC.greaterThanSouthOfDitch() && ww.GC.greaterThanVarrockCenter() && !ww.GC.outOfSuppies()){//good
            southOfDitch = true;
            return true;
        }

        System.out.println("2WTSOD"+(me != null) +"&&"+ !SouthOfDitch.contains(me) +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanDitch() +"&&"+ !ww.GC.greaterThanLevel20Wilderness());
        if(me != null && !SouthOfDitch.contains(me) && ww.GC.outOfSuppies() && ww.GC.greaterThanDitch() && !ww.GC.greaterThanLevel20Wilderness()){//good
            southOfDitch = false;
            return true;
        }
        return false;
    }

    @Override
    public void execute() {

        //If south of the ditch use custom walking.
        if(southOfDitch)
        {
            System.out.println("Walking to south of ditch - CUSTOM ");

            WebPath path = null;

            if (ww.varrock != null) { // Make sure the web got loaded properly
                path = ww.varrock.getPathBuilder().buildTo(SouthOfDitch);
            }else{System.out.println("dis web is null");}

            if (path != null) { // IMPORTANT: if the path should be null, the pathbuilder could not manage to build a path with the given web, so always nullcheck!

                if(ww.wildernessDitchArea.contains(me))
                    add(new IsAtDitch(ww));
                else
                    path.step();
            }else{
                System.out.println("dis path is null");
            }
            //if south of the ditch use straight pathing.
        } else {
            System.out.println("Walking to south of ditch - BresenhamPath ");


            final BresenhamPath path = BresenhamPath.buildTo(SouthOfDitch);

            if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
                add(new IsAtDitch(ww));
                path.step();
            }
        }
        add(new Heal(ww), new EnergyPotion(ww));

    }


}
