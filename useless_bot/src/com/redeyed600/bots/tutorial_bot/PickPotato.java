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

public class PickPotato extends Task {

    private final Area.Circular potatoArea = new Area.Circular(new Coordinate(3147,3284,0), 2);
    private GameObject potato;

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();
        potato = GameObjects.newQuery().names("Potato").actions("Pick").results().nearest();

        System.out.println("me: " + me + "potato: " + potato);
        return me != null && potato != null && !Inventory.isFull();

    }

    @Override
    public void execute() {
        //we don't want to interact with the flax, if it's not visible, so let's check
        //if it's visible let's interact with it, if not then just turn the camera

        if(potato.isVisible()) {

            System.out.println("Can see potato");

            if(potato.interact("Pick")) {
                //After interacting with our flax, we can add a check if it's still valid
                //This isn't required, you can check for player animation also
                //If you'd use player animation, you'd check if it went back to idle after picking the flax
                Execution.delayWhile(() -> potato.isValid(), 3000, 4000);
            }

        } else {

            System.out.println("Cant see potato");
            Camera.turnTo(potato);
        }
    }
}
