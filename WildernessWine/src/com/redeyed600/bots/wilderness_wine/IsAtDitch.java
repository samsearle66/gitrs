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

    //private final Area.Circular wildernessDitch = new Area.Circular(new Coordinate(3146,3293,0), 4);
    private GameObject wildernessDitch;
    private Area.Rectangular wildernessDitchArea;

    @Override
    public boolean validate() {

        wildernessDitch = GameObjects.newQuery().names("Wilderness Ditch").actions("Cross").results().nearest();


        wildernessDitchArea = new Area.Rectangular(new Coordinate(3069,3520,0),
                new Coordinate(3122,3523,0));

        final Player me = Players.getLocal();

        if(me.getPosition().getY() > wildernessDitch.getPosition().getY() && (Inventory.isFull()
                || Inventory.getQuantity("Law rune") < 1 ||
                Inventory.getQuantity("Fire rune") < 1 ||
                !Equipment.contains("Air staff")))
        {
            System.out.println("("+ (me.getPosition().getY() > wildernessDitch.getPosition().getY()) + "&&(" +
                    (Inventory.isFull()|| Inventory.getQuantity("Law rune") < 1 ||
                            Inventory.getQuantity("Fire rune") < 1 ||
                            !Equipment.contains("Air staff"))+"))");
            System.out.println("North of ditch");
        }

        if(me.getPosition().getY() < wildernessDitch.getPosition().getY() && (!Inventory.isFull()
                && Inventory.getQuantity("Law rune") >= 1 &&
                Inventory.getQuantity("Fire rune") >= 1 &&
                Equipment.contains("Air staff")))
        {
            System.out.println("("+(me.getPosition().getY() < wildernessDitch.getPosition().getY())+"&&("+(!Inventory.isFull()
                    && Inventory.getQuantity("Law rune") >= 1 &&
                    Inventory.getQuantity("Fire rune") >= 1 &&
                    Equipment.contains("Air staff"))+")");
            System.out.println("South of ditch");
        }

        if(wildernessDitchArea.contains(me))
        {
            System.out.println("Wilderness ditch contains me");
        }

        if(wildernessDitch.isVisible())
        {
            System.out.println("Ditch is visible");
        }

        //check player position not null
        //should check inventory is not full? seems overkill
        //check gate object is not null
        return  me != null && wildernessDitch != null && wildernessDitchArea.contains(me) &&
                (
                        //Player on northside of ditch - walking to bank
                    (me.getPosition().getY() > wildernessDitch.getPosition().getY() && (Inventory.isFull()
                            || Inventory.getQuantity("Law rune") < 1 ||
                            (!Equipment.contains("Staff of air") && Inventory.getQuantity("Air rune") < 3)))

                            //I am running back because my inventory is full or i am out of runes.
                            ||
                        //Player on southside of ditch - walking to alter
                    (me.getPosition().getY() < wildernessDitch.getPosition().getY() && (!Inventory.isFull() &&
                            Equipment.contains("Staff of air") &&
                            Inventory.getQuantity("Law rune") >= 1))
                            //I am running to the alter because my inventory is not full.
                            //I have enough law, fire and a air staff.

                );
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
