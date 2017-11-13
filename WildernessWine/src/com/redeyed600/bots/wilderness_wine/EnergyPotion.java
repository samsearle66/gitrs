package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class EnergyPotion extends Task {
    wilderness_wine ww;

    public EnergyPotion(wilderness_wine ww){
        this.ww = ww;
    }

    private SpriteItemQueryResults energyPotion;

    @Override
    public boolean validate() {

        energyPotion = Inventory.getItems(ww.GC.ENERGYPOTION);

        return !ww.GC.isAnimating() && ww.GC.underAttackPker() && !ww.GC.hasEnoughEnergy();
        //!ww.GC.Is829Delay() &&
    }

    @Override
    public void execute() {

        if(Magic.getSelected()!=null && Magic.getSelected().isSelected())
            Magic.getSelected().deactivate();

            if(energyPotion.first() != null)
            {
                if(InterfaceWindows.getInventory().isOpen()) {
                    if (energyPotion.first().interact("Drink")) {
                        Execution.delayUntil(() -> Inventory.getSelectedItem()!=null, 1400, 1600);
                        System.out.println("Drinking energy potion");
                    }
                }else{
                    InterfaceWindows.getInventory().open();
                }
            }
    }

}
