package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class BankInterface extends Task
{

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3496,0), 3);

    //VALIDATE
    //inventory contains wine.
    //not enough law runes
    //not enough fire runes

@Override
    public boolean validate() {
        final Player me = Players.getLocal();

        System.out.println("BI:"+(me != null)+","+edgevilleBank.contains(me)+",("+Bank.isOpen() +"||"+ Inventory.contains(GC.WINEOFZAMORAK) +"||"+ GC.outOfSuppies()+")");

        return  (me != null && edgevilleBank.contains(me) && (Bank.isOpen() || Inventory.contains(GC.WINEOFZAMORAK) || GC.outOfSuppies()));
    }

    @Override
    public void execute() {
        System.out.println("Busy opening bank:::("+Inventory.contains(GC.WINEOFZAMORAK)+"||"+(Inventory.getQuantity(GC.LAWRUNE) < GC.LAWRUNE) +"||"+
                (Inventory.getQuantity(GC.FIRERUNE) < 1) +"("+ !Equipment.contains(GC.STAFFOFAIR) +"&&"+ (Inventory.getQuantity(GC.AIRRUNE) < 3)+"))");

        if (Inventory.contains(GC.WINEOFZAMORAK) || Inventory.getQuantity(GC.LAWRUNE) < GC.LAWRUNEQUANTITY ||
           Inventory.getQuantity(GC.FIRERUNE) < 1 ||
                (!Equipment.contains(GC.STAFFOFAIR) && Inventory.getQuantity(GC.AIRRUNE) < 3)) {
            System.out.println("Opening bank");
            if (Bank.isOpen()) {

               Bank.depositAllExcept("Law rune","Fire rune", "Air rune");

               if(Inventory.contains(GC.WINEOFZAMORAK))
                    Bank.deposit(GC.WINEOFZAMORAK,28); //deposit everything

               if (Inventory.getQuantity(GC.LAWRUNE) < GC.LAWRUNEQUANTITY) {
                    Bank.withdraw(GC.LAWRUNE, GC.LAWRUNEQUANTITY - Inventory.getQuantity(GC.LAWRUNE));
               }

               if (Inventory.getQuantity(GC.FIRERUNE) < 1)
               {
                   Bank.withdraw(GC.FIRERUNE, 1);
               }

               if(!Equipment.contains(GC.STAFFOFAIR)){
                   if(Bank.contains(GC.AIRRUNE))
                        Bank.withdraw(GC.AIRRUNE,GC.AIRRUNEQUANTITY - Inventory.getQuantity(GC.AIRRUNE));
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
