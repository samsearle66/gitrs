package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class TravelToBank extends Task {

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3494,0), 2);

    @Override
    //if bank no where to be found
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("TRAVELTOBANK::::me."+me+",contains."+!edgevilleBank.contains(me)+",law."+
                Inventory.getQuantity("Law rune")+",fire."+Inventory.getQuantity("Fire rune")+
                ",staffair."+!Equipment.contains("Staff of air")+",air."+Inventory.getQuantity("Air rune")+",inventoryFull."+Inventory.isFull());

        //VALIDATE
        //inventory is full
        //not at the bank yet
        //not enough law runes
        //not enough fire runes
        //no staff
        //MAYBE
        //ran out of food?

//        System.out.println("me."+me != null);
//        System.out.println("edgevilleBank."+!edgevilleBank.contains(me));
//        System.out.println("getY<3519."+(me.getPosition().getY() < 3519));
//        System.out.println("Law."+(Inventory.getQuantity("Law rune") < 1));
//        System.out.println("Fire."+(Inventory.getQuantity("Fire rune") < 1));
//        System.out.println("InvFull."+Inventory.isFull());
//        System.out.println("DontHaveStaff."+!Equipment.contains("Staff of air"));
//        System.out.println("Air."+(Inventory.getQuantity("Air rune") < 3));
//        System.out.println("DontHaveStaffbutAir"+(!Equipment.contains("Staff of air") && Inventory.getQuantity("Air rune") < 3));


        return (me != null && !edgevilleBank.contains(me)  && me.getPosition().getY() < 3519 &&
                (Inventory.getQuantity("Law rune") < 1 ||
                Inventory.isFull() || (!Equipment.contains("Staff of air") && Inventory.getQuantity("Air rune") < 3)));
    }

    @Override
    public void execute() {
        System.out.println("Walk to edgeville bank");
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
