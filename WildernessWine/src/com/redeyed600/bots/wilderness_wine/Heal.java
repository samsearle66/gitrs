package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class Heal extends Task {

    private SpriteItemQueryResults food;


    @Override
    public boolean validate() {
        food = Inventory.getItems(GC.FOODIDS);

        System.out.println(food.size() +"&&"+ !GC.hasEnoughHealth());

        return (food.size() > 0) && !GC.hasEnoughHealth();
    }

    @Override
    public void execute() {

        if(food.first() != null)
        {
            if(food.first().interact("Drink")||food.first().interact("Eat"))
            {
                Execution.delayWhile(() -> food.first().isValid(), 3000, 4000);
            }
        }
    }
}
