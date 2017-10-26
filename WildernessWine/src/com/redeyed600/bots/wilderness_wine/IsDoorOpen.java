package com.redeyed600.bots.wilderness_wine;

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

public class IsDoorOpen extends Task {

    private final Area.Circular alterDoor = new Area.Circular(new Coordinate(2958,3820,0),20);

    private GameObject door;

    @Override
    public boolean validate() {
        door = GameObjects.newQuery().names("Large door").actions("Open").within(alterDoor).results().nearest();

        final Player me = Players.getLocal();

        //check player position not null
        //should check inventory is not full? seems overkill
        //check gate object is not null

        //System.out.println(me + "///" + potatoGate.contains(me) + "///" + gate);

        System.out.println("IsDoorOpen:"+(me != null) +","+ alterDoor.contains(me) +","+ door);

        return  me != null && alterDoor.contains(me) && door != null;
    }

    @Override
    public void execute() {
        System.out.println("Opening alter door");
        if(door.interact("Open")) {
                //After interacting with our gate, we can add a check if it's still valid
                //This isn't required, you can check for player animation also
                //If you'd use player animation, you'd check if it went back to idle after opening the gate
                Execution.delayWhile(() -> door.isValid(), 3000, 4000);
        } else {

            System.out.println("Looking for door");

            Camera.turnTo(door);
        }
    }
}
