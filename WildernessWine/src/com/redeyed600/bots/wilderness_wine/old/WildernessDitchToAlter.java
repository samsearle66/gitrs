package com.redeyed600.bots.wilderness_wine.old;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class WildernessDitchToAlter extends Task {

    private final Area.Circular wildernessAlter = new Area.Circular(new Coordinate(3088,3529,0), 2);

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

        return me != null && !Inventory.isFull() && !wildernessAlter.contains(me) && me.getPosition().getY() > 3519 &&
                Inventory.getQuantity("Law rune") >= 1 &&
                Inventory.getQuantity("Fire rune") >= 1 &&
                Equipment.contains("Staff of air");
    }

    @Override
    public void execute() {
        System.out.println("Walk to wilderness alter");
        /*Path path = Traversal.getDefaultWeb().getPathBuilder().buildTo(wildernessAlter.getRandomCoordinate());
        if(path != null) {
            add(new IsAtDitch());
            path.step();
        }*/
        final BresenhamPath path = BresenhamPath.buildTo(wildernessAlter);

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            add(new IsAtDitch());
            path.step();
        }
    }
}
