package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.BankQueryBuilder;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;
import java.util.Arrays;


public class BankInterfaceCastle extends Task {

    private wilderness_wine ww;

    public BankInterfaceCastle(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular lumbridgeCastleBank = new Area.Circular(new Coordinate(3209,3219,2),3);
    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3496,0), 3);

    private Player me;
    private SpriteItemQueryResults food;
    private SpriteItemQueryResults energyPotion;

    @Override
    public boolean validate() {
        me = Players.getLocal();
        food = Inventory.getItems(ww.GC.FOODIDS);
        energyPotion = Inventory.getItems(ww.GC.ENERGYPOTION);

        System.out.println("BIC:"+(me != null) +"&&"+ (me.getPosition().getPlane() == 2) +"&&"+ lumbridgeCastleBank.contains(me) +"&&"+ !ww.GC.greaterThanDitch() +"&&"+ !ww.GC.greaterThanLumbridgeCastleLevel3() +"&&("+ww.GC.outOfSuppies()+"||"+ Bank.isOpen()+")");

        return (me != null && (me.getPosition().getPlane() == 2) && lumbridgeCastleBank.contains(me) && !ww.GC.greaterThanDitch() && !ww.GC.greaterThanLumbridgeCastleLevel3() && (ww.GC.outOfSuppies()|| Bank.isOpen()));
                //||
               // (me != null && edgevilleBank.contains(me) && (Bank.isOpen() || Inventory.contains(ww.GC.WINEOFZAMORAK) || ww.GC.outOfSuppies()));

    }


    @Override
    public void execute() {
        System.out.println("Busy opening castle bank:"+ Inventory.contains(ww.GC.WINEOFZAMORAK)+"||"+ww.GC.outOfSuppies());

        if (ww.GC.outOfSuppies() || Inventory.contains(ww.GC.WINEOFZAMORAK)) {
            System.out.println("Opening bank");
            if (Bank.isOpen()) {

                Bank.depositAllExcept(ww.GC.LAWRUNE,ww.GC.FIRERUNE, ww.GC.AIRRUNE,ww.GC.AIRSTAFF,ww.GC.ENERGYPOTION[0],ww.GC.ENERGYPOTION[1],ww.GC.ENERGYPOTION[2],ww.GC.ENERGYPOTION[3],ww.GC.FOODIDS[0]);

                if(energyPotion.size() < ww.GC.ENERGYPOTIONQUANTITY){
                    Bank.withdraw(Bank.getItems(ww.GC.ENERGYPOTION).first(), ww.GC.ENERGYPOTIONQUANTITY - energyPotion.size());
                }

                if (Inventory.getQuantity(ww.GC.LAWRUNE) < ww.GC.LAWRUNEQUANTITY) {
                    Bank.withdraw(ww.GC.LAWRUNE, ww.GC.LAWRUNEQUANTITY - Inventory.getQuantity(ww.GC.LAWRUNE));
                }

                if (Inventory.getQuantity(ww.GC.FIRERUNE) < 1)
                    Bank.withdraw(ww.GC.FIRERUNE, 1);


                if(!Equipment.contains(ww.GC.STAFFOFAIR) && Inventory.getItems(ww.GC.STAFFOFAIR).first()==null){
                    Bank.withdraw(ww.GC.AIRSTAFF, 1);
                }

                if(!Bank.contains(ww.GC.STAFFOFAIR))
                    Bank.withdraw(ww.GC.AIRRUNE,ww.GC.AIRRUNEQUANTITY - Inventory.getQuantity(ww.GC.AIRRUNE));

                if(food.size() < ww.GC.MINIMUMFOOD)
                    Bank.withdraw(Bank.getItems(ww.GC.FOODIDS).first(),ww.GC.MINIMUMFOOD - food.size());


            } else {
                System.out.println("Open bank");
                Bank.open();
            }
        } else {
            if (Bank.isOpen()) {
                System.out.println("Close bank");
                Bank.close();
            }

        }
    }


}
