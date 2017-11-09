package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.task.Task;

public class Heal extends Task {

    wilderness_wine ww;

    public Heal(wilderness_wine ww){
        this.ww = ww;
    }

    private SpriteItemQueryResults food;


    @Override
    public boolean validate() {
        food = Inventory.getItems(ww.GC.FOODIDS);
        return !ww.GC.Is829Delay() && !ww.GC.isAnimating() && (!Bank.isOpen() && food!=null && food.size() > 0) && !ww.GC.hasEnoughHealth();
    }

    @Override
    public void execute() {

        if(Magic.getSelected()!=null && Magic.getSelected().isSelected())
            Magic.getSelected().deactivate();


        if(food.first() != null)
        {
            if(InterfaceWindows.getInventory().isOpen()) {
                if (food.first().interact("Drink") || food.first().interact("Eat")) {
                    System.out.println("Healing");
                    Execution.delayWhile(() -> food.first().isValid(), 3000, 4000);
                }
            }else{
                InterfaceWindows.getInventory().open();
            }
        }
    }
}
