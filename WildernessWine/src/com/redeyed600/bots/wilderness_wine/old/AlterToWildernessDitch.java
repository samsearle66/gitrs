package com.redeyed600.bots.wilderness_wine.old;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class AlterToWildernessDitch extends Task {

    private final Area.Rectangular wildernessDitchArea = new Area.Rectangular(new Coordinate(3069,3520,0),
            new Coordinate(3122,3523,0));

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        if(Inventory.getQuantity("Law rune") < 1){
            System.out.println("Not enough runes.");
        }
        if(Inventory.getQuantity("Fire rune") < 1){
            System.out.println("Not enough fire runes.");
        }

        if(!Equipment.contains("Air staff")){
            System.out.println("No air staff.");
        }

        //VALIDATE
        //inventory is full
        //not within wilddernessDitchArea
        //not enough law runes
        //not enough fire runes
        //no staff
        //MAYBE
        //ran out of food?
        return me != null && !wildernessDitchArea.contains(me) && me.getPosition().getY() > 3519 &&
                (Inventory.getQuantity("Law rune") < 1 ||
                Inventory.getQuantity("Fire rune") < 1 ||
                !Equipment.contains("Staff of air") ||
                Inventory.isFull());
        //I am running back because my inventory is full or i am out of runes
    }

    @Override
    public void execute() {
        System.out.println("Walk from alter to Wild Ditch");
        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(wildernessDitchArea.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(wildernessDitchArea);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch());
            path.step();
        }
    }
}
