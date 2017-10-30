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
import com.runemate.game.api.script.framework.task.Task;

public class WalkToBank extends Task {
    private wilderness_wine ww;
    Boolean southOfDitch = false;
    Web web;
    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3494,0), 2);
    private Player me;

    public WalkToBank(wilderness_wine ww){
        this.ww = ww;
        try {
            web = SerializableWeb.deserialize(ww.GC.getByteArray("C:\\Users\\trend.ly\\gitrs\\WildernessWine\\src\\com\\redeyed600\\bots\\Resources\\varrockToEdgeville.nav"));
        } catch (Exception e) {
            e.printStackTrace();
            web = null;
        }
    }



    @Override
    //if bank no where to be found
    public boolean validate() {
        me = Players.getLocal();
        System.out.println("WTB:"+(me!=null)+","+!edgevilleBank.contains(me)+","+!ww.GC.greaterThanDitch()+","+ww.GC.outOfSuppies()+"&&"+ ww.GC.greaterThanVarrockCenter());

        return (me != null && !edgevilleBank.contains(me)&& !ww.GC.greaterThanDitch() && ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter());
    }

    @Override
    public void execute() {
        System.out.println("Walking to edgeville bank");

        if(web != null)
        {
            System.out.println(web.getVertices().size());
        }else System.out.println("shes fucked");

        WebPath path = null;

        if (web != null) { // Make sure the web got loaded properly
            path = web.getPathBuilder().buildTo(edgevilleBank);
        }else{System.out.println("dis web is null");}

        if (path != null) { // IMPORTANT: if the path should be null, the pathbuilder could not manage to build a path with the given web, so always nullcheck!
            add(new IsAtDitch(ww));
            if(ww.GC.underAttack()) {
                Traversal.toggleRun();
                path.step(Path.TraversalOption.MANAGE_STAMINA_ENHANCERS);
            }

            path.step();
        }else{
            System.out.println("dis path is null");
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
