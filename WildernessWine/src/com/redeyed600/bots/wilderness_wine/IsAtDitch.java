package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class IsAtDitch extends Task {

    wilderness_wine ww;

    public IsAtDitch(wilderness_wine ww){
        this.ww = ww;
    }


    GameObject wildernessDitch;
    private static Area.Rectangular wildernessDitchArea;
    public Player me;

    @Override
    public boolean validate() {
        wildernessDitch = GameObjects.newQuery().names("Wilderness Ditch").actions("Cross").results().nearest();
        wildernessDitchArea = new Area.Rectangular(new Coordinate(3069,3515,0), new Coordinate(3122,3528,0)); //BROKEN NEED FIXING

        me = Players.getLocal();

        return wildernessDitch!=null && wildernessDitchArea.contains(me) &&
                ((ww.GC.greaterThanDitch() && ww.GC.outOfSuppies())
                ||
                (!ww.GC.greaterThanDitch() && !ww.GC.outOfSuppies()));
    }

    @Override
    public void execute() {
        System.out.println("Jump wilderness ditch");
        if(wildernessDitch.isVisible()) {
            if(wildernessDitch.interact("Cross")) {
                //After interacting with our gate, we can add a check if it's still valid
                //This isn't required, you can check for player animation also
                //If you'd use player animation, you'd check if it went back to idle after opening the gate
                Execution.delayWhile(() -> wildernessDitch.isValid(), 3000, 4000);
            }
        } else {

            System.out.println("Looking for wilderness ditch");

            Camera.turnTo(wildernessDitch);
        }
    }
}
