package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

//WALK FROM WILDERNESS TO EDGEVILLE
public class WalkToSouthOfDitch extends Task {

    private wilderness_wine ww;

    public WalkToSouthOfDitch(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular SouthOfDitch = new Area.Circular(new Coordinate(3087,3516,0),3);
    Player me;

    @Override
    public boolean validate() {

        me = Players.getLocal();

        System.out.println("WTSOD:"+(me != null) +" && "+ !SouthOfDitch.contains(me) +" && "+ !ww.GC.greaterThanDitch() +" && "+ ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanVarrockCenter());

        return (me != null && !SouthOfDitch.contains(me) && ww.GC.greaterThanDitch() && ww.GC.outOfSuppies()&& ww.GC.greaterThanVarrockCenter());
    }

    @Override
    public void execute() {
        System.out.println("Walking from north of ditch to south of ditch ");
        final BresenhamPath path = BresenhamPath.buildTo(SouthOfDitch);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch(ww));
            if(ww.GC.underAttack())
                path.step(Path.TraversalOption.MANAGE_STAMINA_ENHANCERS);
            else
                path.step();
        }
    }


}
