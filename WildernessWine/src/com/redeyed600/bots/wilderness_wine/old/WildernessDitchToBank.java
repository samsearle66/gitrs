package com.redeyed600.bots.wilderness_wine.old;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WildernessDitchToBank extends Task {

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3494,0), 2);

    @Override
    //if bank no where to be found
    public boolean validate() {
        final Player me = Players.getLocal();

        //VALIDATE
        //inventory is full
        //not at the bank yet
        //not enough law runes
        //not enough fire runes
        //no staff
        //MAYBE
        //ran out of food?
        return ((me != null && !edgevilleBank.contains(me) && me.getPosition().getY() < 3519) &&
                (Inventory.getQuantity("Law rune") < 1 ||
                Inventory.getQuantity("Fire rune") < 1 ||
                        (!Equipment.contains("Staff of air") || Inventory.getQuantity("Air rune") < 3) ||
                Inventory.isFull()));
        //i am walking to the bank because i dont have enough runes or my inventory is full.
    }

    @Override
    public void execute() {
        System.out.println("Walk from wilderness ditch to bank");
        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgevilleBank.getRandomCoordinate());
        if(path != null) {
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(edgevilleBank);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            path.step();
        }
    }
}
