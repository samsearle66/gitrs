package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class GrabWine extends Task{

   // private final Area.Circular alter = new Area.Circular(new Coordinate(2933,3516,0), 6);
    private final Area.Circular alter = new Area.Circular(new Coordinate(2955,3820,0),1);
    private final Area.Absolute table = new Area.Absolute(new Coordinate(2930,3515,0));
    private GroundItem wine;
    private SpriteItemQueryResults food;
    private SpriteItemQueryResults jug;


    @Override
    public boolean validate() {
        final Player me = Players.getLocal();

        food = Inventory.getItems(GC.FOODIDS);
        jug = Inventory.getItems("Jug");

        wine = GroundItems.newQuery().names("Wine of zamorak").results().nearest();

        //Banking completed means have runes
        System.out.println("GW:"+(me != null) +"&&"+ (wine != null) +"&&"+ alter.contains(me) +"&&"+  GC.greaterThanAlter() +"&&"+ GC.bankingCompleted() +"||"+ Magic.TELEKINETIC_GRAB.isSelected());

        return (me != null && wine != null && alter.contains(me) && GC.greaterThanAlter() && GC.bankingCompleted() || Magic.TELEKINETIC_GRAB.isSelected());
    }

    @Override
    public void execute()
    {
        if(!Inventory.isFull())
        {
            if(Magic.TELEKINETIC_GRAB.isSelected())
            {
                System.out.println("Telegrab is selected...waiting");
                if((wine!=null)&&(wine.isVisible()))
                {
                    System.out.println("Wine has been spotted");
                    if(wine.interact("Cast")) {
                        //After interacting with our flax, we can add a check if it's still valid
                        //This isn't required, you can check for player animation also
                        //If you'd use player animation, you'd check if it went back to idle after picking the flax
                        Execution.delayWhile(() -> wine.isValid(), 3000, 4000);
                    }

                    if(jug != null && jug.first() != null)
                        jug.first().interact("Drop");

                    food.first().interact("Drop");


                } else {
                    System.out.println("no wine here mate!");
                }
                //maybe do antiban?
            }else{
                System.out.println("activate telekientic grab");
                Magic.TELEKINETIC_GRAB.activate();
            }
        } else {
            Magic.TELEKINETIC_GRAB.deactivate();
            //Click somewhere randomly on the screen.
            System.out.println("DONE");
        }


    }
}
