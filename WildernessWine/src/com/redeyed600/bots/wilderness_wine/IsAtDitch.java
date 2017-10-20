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

public class IsAtDitch extends Task {

    //private final Area.Circular wildernessDitch = new Area.Circular(new Coordinate(3146,3293,0), 4);
    private GameObject wildernessDitch;

    @Override
    public boolean validate() {
        wildernessDitch = GameObjects.newQuery().names("Wilderness Ditch").actions("Cross").results().nearest();
//      wildernessDitch = GameObjects.newQuery().names("Wilderness Ditch").actions("Cross").within(wildernessDitchArea).results().nearest();

        final Player me = Players.getLocal();

        //check player position not null
        //should check inventory is not full? seems overkill
        //check gate object is not null

        //System.out.println(me + "///" + potatoGate.contains(me) + "///" + gate);

        return  me != null /*&& potatoGate.contains(me)*/ && wildernessDitch != null;
    }

    @Override
    public void execute() {
        if(wildernessDitch.interact("Cross")) {
                //After interacting with our gate, we can add a check if it's still valid
                //This isn't required, you can check for player animation also
                //If you'd use player animation, you'd check if it went back to idle after opening the gate
                Execution.delayWhile(() -> wildernessDitch.isValid(), 3000, 4000);
        } else {

            System.out.println("Looking for wilderness ditch");

            Camera.turnTo(wildernessDitch);
        }
    }
}
