package com.redeyed600.bots.tutorial_bot;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class BankInterface extends Task
{
    private final Area.Circular draynorBank = new Area.Circular(new Coordinate(3092,3245,0), 2);
@Override
    public boolean validate() {
        final Player me = Players.getLocal();
        //Execute if haven't started banking |OR| Inventory is full.



        System.out.println(me + "|" + draynorBank.contains(me) + "|"+ Inventory.isFull() + "|" + Bank.isOpen());

        return  me != null && draynorBank.contains(me) && Inventory.isFull() || Bank.isOpen();
    }

    @Override
    public void execute() {
        System.out.println("Busy opening bank");
        if (Inventory.contains("Potato")) {
            System.out.println("Inventory contains potato");
            if (Bank.isOpen()) {
                System.out.println("Deposit potato");
                Bank.depositInventory(); //deposit everything
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
