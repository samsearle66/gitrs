package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.task.Task;

public class Task1 extends Task {
    @Override
    public boolean validate() {

       /* System.out.println("BANKINTERFACE::::::wine."+ Inventory.contains("Wine of zamorak")+",law."+
                Inventory.getQuantity("Law rune")+",fire."+Inventory.getQuantity("Fire rune")+
                ",staffair."+!Equipment.contains("Staff of air")+",air."+Inventory.getQuantity("Air rune"));
        */

//       System.out.println("Law rune."+Inventory.getQuantity("Law rune"));//563
//       System.out.println("Air rune."+Inventory.getQuantity("Air rune"));//556
//       System.out.println("Wine of zamorak."+Inventory.contains("Wine of zamorak"));//245
//       System.out.println("Staff of air."+Equipment.contains("Staff of air"));//1381

        return true;
    }

    @Override
    public void execute() {

    }
}
