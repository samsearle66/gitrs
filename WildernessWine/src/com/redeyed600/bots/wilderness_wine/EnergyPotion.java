package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
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

        return ww.GC.underAttackPker() && !ww.GC.hasEnoughEnergy();
    }

    @Override
    public void execute() {
        System.out.println("Not enough energy");
        if(energyPotion.first() != null)
        {
            if(energyPotion.first().interact("Drink"))
            {
                Execution.delayWhile(() -> energyPotion.first().isValid(), 3000, 4000);
            }
        }
    }


}
