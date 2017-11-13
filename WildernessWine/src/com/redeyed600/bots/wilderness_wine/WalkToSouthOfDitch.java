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
    Web web;
    private final Area.Circular SouthOfDitch = new Area.Circular(new Coordinate(3087,3516,0),6);
    Player me;
    Boolean southOfDitch = false;


    public WalkToSouthOfDitch(wilderness_wine ww){
        this.ww = ww;
        try {
            web = SerializableWeb.deserialize(ww.GC.getByteArray("src\\Resources\\varrockToEdgeville.nav"));
        } catch (Exception e) {
            e.printStackTrace();
            web = null;
        }
    }
    @Override
    public boolean validate() {

        me = Players.getLocal();

        if(me!= null && !SouthOfDitch.contains(me) && ww.GC.bankingCompleted() && !ww.GC.greaterThanSouthOfDitch() && ww.GC.greaterThanVarrockCenter() && !ww.GC.outOfSuppies()){//good
            southOfDitch = true;
            return true;
        }

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

            if(web != null)
            {
                System.out.println(web.getVertices().size());
            }else System.out.println("shes fucked");

            WebPath path = null;

            if (web != null) { // Make sure the web got loaded properly
                path = web.getPathBuilder().buildTo(SouthOfDitch);
            }else{System.out.println("dis web is null");}

            if (path != null) { // IMPORTANT: if the path should be null, the pathbuilder could not manage to build a path with the given web, so always nullcheck!
                add(new IsAtDitch(ww));
//                if(ww.GC.underAttackPker()) {
                if(!Traversal.isRunEnabled() && Traversal.getRunEnergy() > 10)
                    Traversal.toggleRun();
                path.step(Path.TraversalOption.MANAGE_STAMINA_ENHANCERS);
//                }
//                path.step();
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
    }


}
