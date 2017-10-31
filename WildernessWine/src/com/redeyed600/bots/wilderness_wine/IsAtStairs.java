package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class IsAtStairs extends Task {

    private wilderness_wine ww;

    public IsAtStairs(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular lumbridgeStairs = new Area.Circular(new Coordinate(3206,3209,0),3);
    private final Area.Circular lumbridgeStairs2 = new Area.Circular(new Coordinate(3206,3209,1),3);

    private GameObject stairs;
    Player me;

    @Override
    public boolean validate() {
        me = Players.getLocal();
        stairs = GameObjects.getLoaded(ww.GC.LUMBRIDGECASTLESTAIRS).nearest();

        //System.out.println("IsAtStairs:"+(me != null) +"&&"+ !lumbridgeStairs3.contains(me) +"&&("+lumbridgeStairs.contains(me) +"||"+ lumbridgeStairs2.contains(me)+")+&&"+ lumbridgeStairs.contains(me) +"&&"+ (stairs!=null)+"&&"+!ww.GC.greaterThanDitch()+"&&"+!ww.GC.greaterThanLumbridgeCastleLevel1());
        System.out.println("IsAtStairs:"+(me != null) +"&& ("+lumbridgeStairs.contains(me)+"||"+lumbridgeStairs2.contains(me)+")&&"+ (me.getPosition().getPlane()!=2) +"&&"+ (stairs!=null)+"&&"+!ww.GC.greaterThanDitch()+"&&"+!ww.GC.greaterThanLumbridgeCastleLevel1() +"&&"+ !ww.GC.greaterThanVarrockCenter());

        return  me != null &&(lumbridgeStairs.contains(me)||lumbridgeStairs2.contains(me))&& me.getPosition().getPlane()!=2 && stairs != null && !ww.GC.greaterThanDitch() && ww.GC.greaterThanLumbridgeCastleLevel1() && !ww.GC.greaterThanVarrockCenter();
    }

    @Override
    public void execute() {
        System.out.println("Climbing the lumbridge stairs");
        if(stairs!=null&&stairs.isVisible()) {
            if (stairs.interact("Climb-up")) {
                //After interacting with our gate, we can add a check if it's still valid
                //This isn't required, you can check for player animation also
                //If you'd use player animation, you'd check if it went back to idle after opening the gate
                Execution.delayWhile(() -> stairs.isValid(), 3000, 4000);
            } else {
                System.out.println("Looking for stairs");
                Camera.turnTo(stairs);
            }
        }
    }


}
