package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

import java.util.List;

public class BankInterface extends Task
{
    wilderness_wine ww;

    public BankInterface(wilderness_wine ww){
        this.ww = ww;
    }

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3496,0), 3);

    private SpriteItemQueryResults food;
    private SpriteItemQueryResults energyPotion;
    private Player me;

    //VALIDATE
    //inventory contains wine.
    //not enough law runes
    //not enough fire runes

@Override
    public boolean validate() {
        me = Players.getLocal();
        food = Inventory.getItems(ww.GC.FOODIDS);
        energyPotion = Inventory.getItems(ww.GC.ENERGYPOTION);

        System.out.println("BI:"+(me != null)+"&&"+edgevilleBank.contains(me)+"&&("+Bank.isOpen() +"||"+ Inventory.contains(ww.GC.WINEOFZAMORAK) +"||"+ ww.GC.outOfSuppies()+")");

        return (me != null && edgevilleBank.contains(me) && (Bank.isOpen() || Inventory.contains(ww.GC.WINEOFZAMORAK) || ww.GC.outOfSuppies()));
    }

    @Override
    public void execute() {
        System.out.println("Busy opening bank:"+Inventory.contains(ww.GC.WINEOFZAMORAK)+"||"+ww.GC.outOfSuppies());

        if (Inventory.contains(ww.GC.WINEOFZAMORAK) || ww.GC.outOfSuppies()) {
            System.out.println("Opening bank");
            if (Bank.isOpen()) {

               Bank.depositAllExcept("Law rune","Fire rune", "Air rune", "Jug of wine", "Energy potion");

               if(energyPotion.size() < ww.GC.ENERGYPOTIONQUANTITY){
                   Bank.withdraw(Bank.getItems(ww.GC.ENERGYPOTION).first(), ww.GC.ENERGYPOTIONQUANTITY - energyPotion.size());
               }

               if (Inventory.getQuantity(ww.GC.LAWRUNE) < ww.GC.LAWRUNEQUANTITY) {
                    Bank.withdraw(ww.GC.LAWRUNE, ww.GC.LAWRUNEQUANTITY - Inventory.getQuantity(ww.GC.LAWRUNE));
               }

               if (Inventory.getQuantity(ww.GC.FIRERUNE) < 1)
               {
                   Bank.withdraw(ww.GC.FIRERUNE, 1);
               }

               if(!Equipment.contains(ww.GC.STAFFOFAIR)){
                    Bank.withdraw(ww.GC.AIRRUNE,ww.GC.AIRRUNEQUANTITY - Inventory.getQuantity(ww.GC.AIRRUNE));
               }

                if(food.size() < ww.GC.MINIMUMFOOD){
                    Bank.withdraw(Bank.getItems(ww.GC.FOODIDS).first(),ww.GC.MINIMUMFOOD - food.size());
                }

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
