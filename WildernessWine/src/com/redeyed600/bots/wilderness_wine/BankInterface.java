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

    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3496,0), 2);

    //VALIDATE
    //inventory contains wine.
    //not enough law runes
    //not enough fire runes

@Override
    public boolean validate() {
        final Player me = Players.getLocal();
        //Execute if haven't started banking |OR| Inventory is full.
        //no runes left || full inventory

        return  me != null && edgevilleBank.contains(me) &&
                (Inventory.contains("Jug of wine")  || Bank.isOpen() ||
                Inventory.getQuantity("Law rune") < GC.LAWRUNES ||
                        Inventory.getQuantity("Fire rune") < 1);
        //execute if my bank is still open or inventory contains jug of wine or not enough law runes.
    }

    @Override
    public void execute() {
        System.out.println("Busy opening bank");
        if (Inventory.contains("Jug of wine") || Inventory.getQuantity("Law rune") < GC.LAWRUNES ||
           Inventory.getQuantity("Fire rune") < 1) {
            System.out.println("Opening bank");
            if (Bank.isOpen()) {
               System.out.println("Deposit Jug of wine");

               Bank.depositAllExcept("Law rune","Fire rune", "air rune");

               if(Inventory.contains("Jug of wine"))
                    Bank.deposit("Jug of wine",28); //deposit everything

               if (Inventory.getQuantity("Law rune") < GC.LAWRUNES) {
                    Bank.withdraw("Law rune", GC.LAWRUNES - Inventory.getQuantity("Law rune"));
               }
               if (Inventory.getQuantity("Fire runes") < 1)
               {
                   Bank.withdraw("Fire rune", 1);
               }

               if(!Equipment.contains("Staff of air")){
                   if(Bank.contains("Air rune"))
                        Bank.withdraw("Air rune",81 - Inventory.getQuantity("Air rune"));
                   else System.out.println("You have no air runes or a staff.");
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
