package com.redeyed600.bots.tutorial_bot;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class IsGateOpen extends Task {

    private final Area.Circular potatoGate = new Area.Circular(new Coordinate(3146,3293,0), 4);
    private GameObject gate;

    @Override
    public boolean validate() {
        gate = GameObjects.newQuery().names("Gate").actions("Close").within(potatoGate).results().nearest();

        final Player me = Players.getLocal();

        //check player position not null
        //should check inventory is not full? seems overkill
        //check gate object is not null

        //System.out.println(me + "///" + potatoGate.contains(me) + "///" + gate);

        return  me != null && potatoGate.contains(me) && gate != null;
    }

    @Override
    public void execute() {
        if(gate.interact("Open")) {
                //After interacting with our gate, we can add a check if it's still valid
                //This isn't required, you can check for player animation also
                //If you'd use player animation, you'd check if it went back to idle after opening the gate
                Execution.delayWhile(() -> gate.isValid(), 3000, 4000);
        } else {

            System.out.println("Looking for gate");

            Camera.turnTo(gate);
        }
    }
}
