package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class BankInterface extends Task
{
    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3496,0), 2);

@Override
    public boolean validate() {
        final Player me = Players.getLocal();
        //Execute if haven't started banking |OR| Inventory is full.
        //no runes left || full inventory
        return  me != null && edgevilleBank.contains(me) /*&& Inventory.isFull() */ || Bank.isOpen();
    }

    @Override
    public void execute() {
        System.out.println("Busy opening bank");
        if (Inventory.contains("Jug of wine")) {
            System.out.println("Inventory contains jug of wine");
            if (Bank.isOpen()) {
               System.out.println("Deposit Jug of wine");

               Bank.deposit("Jug of wine",28); //deposit everything

               if (Inventory.getQuantity("Law runes") < 27) {
                    Bank.withdraw("Law runes", 27);
               }
               if (Inventory.getQuantity("Fire runes") < 1)
               {
                   Bank.withdraw("Fire rune", 1);
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
