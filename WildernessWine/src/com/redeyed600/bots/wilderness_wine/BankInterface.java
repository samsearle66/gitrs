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
        //Execute if haven't started banking |OR| Inventory is full.
        //no runes left || full inventory

        System.out.println("BANKINTERFACE::::::me."+me+",contains."+edgevilleBank.contains(me)+",wine."+Inventory.contains("Jug of wine")+",law."+
                (Inventory.getQuantity("Law rune")<4)+",fire."+(Inventory.getQuantity("Fire rune")<1)+
                ",staffair."+!Equipment.contains("Staff of air")+",air."+(Inventory.getQuantity("Air rune")<3));

        return  (me != null && edgevilleBank.contains(me) &&
                (Inventory.contains("Wine of zamorak")  || Bank.isOpen() ||
                Inventory.getQuantity("Law rune") < GC.LAWRUNES ||
                        Inventory.getQuantity("Fire rune") < 1
                ||  (!Equipment.contains("Staff of air") && Inventory.getQuantity("Air rune") < 3)));
        //execute if my bank is still open or inventory contains jug of wine or not enough law runes.
    }

    @Override
    public void execute() {
        System.out.println("Busy opening bank");
        if (Inventory.contains("Wine of zamorak") || Inventory.getQuantity("Law rune") < GC.LAWRUNES ||
           Inventory.getQuantity("Fire rune") < 1 || (!Equipment.contains("Staff of air") &&
                Inventory.getQuantity("Air rune") < 3)) {
            System.out.println("Opening bank");
            if (Bank.isOpen()) {               Bank.depositAllExcept("Law rune","Fire rune", "air rune");

               if(Inventory.contains("Wine of zamorak"))
                    Bank.deposit("Wine of zamorak",28); //deposit everything

               if (Inventory.getQuantity("Law rune") < GC.LAWRUNES) {
                    Bank.withdraw("Law rune", GC.LAWRUNES - Inventory.getQuantity("Law rune"));
               }

               if (Inventory.getQuantity("Fire rune") < 1)
               {
                   Bank.withdraw("Fire rune", 1);
               }

               if(!Equipment.contains("Staff of air")){
                   if(Bank.contains("Air rune"))
                        Bank.withdraw("Air rune",81 - Inventory.getQuantity("Air rune"));
                   else System.out.println("STOP PROGRAM!!.");
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
