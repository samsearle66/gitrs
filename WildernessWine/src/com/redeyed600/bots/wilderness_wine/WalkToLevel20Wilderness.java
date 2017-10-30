package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WalkToLevel20Wilderness extends Task {

    private wilderness_wine ww;

    public WalkToLevel20Wilderness(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular level20Wilderness = new Area.Circular(new Coordinate(2983,3660,0),6);
    private Player me;
    private GameObject door;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();


        //WINEOFZAMORAK??&& !Inventory.contains(GC.WINEOFZAMORAK)
        System.out.println("1WTL20W:"+(me != null)+","+ !ww.GC.greaterThanLevel20Wilderness() +","+ww.GC.bankingCompleted() + ","+ !ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanVarrockCenter() +"&&"+ ww.GC.greaterThanSouthOfDitch());

        if(me != null && !level20Wilderness.contains(me) && !ww.GC.greaterThanLevel20Wilderness() && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter() && ww.GC.greaterThanSouthOfDitch())
        {
            return true;
        }

        System.out.println("2WTL20W:"+ (me != null) +"&&"+ !level20Wilderness.contains(me) +"&&"+ ww.GC.greaterThanLevel20Wilderness() +"&&"+ !ww.GC.greaterThanWildernessArea() +"&&"+ ww.GC.greaterThanDitch() +"&&"+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanVarrockCenter() +"&&"+ (door==null));

        if (me != null && !level20Wilderness.contains(me) && ww.GC.greaterThanLevel20Wilderness() && ww.GC.greaterThanWildernessArea() && ww.GC.greaterThanDitch() && ww.GC.outOfSuppies() && ww.GC.greaterThanVarrockCenter() && door==null){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Walk to 20 wilderness");
        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgevilleBank.getRandomCoordinate());
        if(path != null) {
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(level20Wilderness);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash

            if(ww.GC.underAttack()) {
                Traversal.toggleRun();
                path.step(Path.TraversalOption.MANAGE_STAMINA_ENHANCERS);
            }

            path.step();


        }
    }
}
