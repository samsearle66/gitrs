package com.redeyed600.bots.wilderness_wine.old;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class BankToWildernessDitch extends Task {

    private final Area.Circular alter = new Area.Circular(new Coordinate(3070,3535),4);

    //VALIDATE
    //Inventory is not full.
    //Has runes
    //Has staff

    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        return (me != null && !Inventory.isFull() && !alter.contains(me) &&
                Inventory.getQuantity("Law rune") >= 1 &&
                Inventory.getQuantity("Fire rune") >= 1 &&
                (Equipment.contains("Staff of air") || Inventory.getQuantity("Air rune") >= 3));
        //
    }

    @Override
    public void execute() {
        System.out.println("Walk from bank to wild ditch");

        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(wildernessDitchArea.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(alter);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch());
            path.step();
        }
    }
}
