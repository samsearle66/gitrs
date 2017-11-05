package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldSelect;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.sun.webkit.event.WCMouseEvent;

public class GrabWine extends Task{

    private wilderness_wine ww;

    public GrabWine(wilderness_wine ww){
        this.ww = ww;
    }

   // private final Area.Circular alter = new Area.Circular(new Coordinate(2933,3516,0), 6);
    private final Area.Circular alter = new Area.Circular(new Coordinate(2951,3818,0),2);
    private final Area.Absolute table = new Area.Absolute(new Coordinate(2930,3515,0));
    private GroundItem wine;
    private SpriteItemQueryResults food;
    private SpriteItemQueryResults jug;
    private Player me;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        food = Inventory.getItems("Jug of wine");
        jug = Inventory.getItems("Jug");

        wine = GroundItems.newQuery().names("Wine of zamorak").results().nearest();

        //Banking completed means have runes
        System.out.println("GW:"+(me != null) +"&&"+ (wine != null) +"&&"+ alter.contains(me) +"&&"+ ww.GC.greaterThanAlter() +"&&"+ ww.GC.bankingCompleted() +"&&"+ !ww.GC.outOfSuppies() +"&&"+ ww.GC.greaterThanVarrockCenter() +"||"+ Magic.TELEKINETIC_GRAB.isSelected());

        return (me != null && alter.contains(me) && ww.GC.greaterThanAlter() && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && ww.GC.greaterThanVarrockCenter() || Magic.TELEKINETIC_GRAB.isSelected());
    }

    @Override
    public void execute()
    {
        if(!Inventory.isFull())
        {
                if (Magic.TELEKINETIC_GRAB.isSelected()) {
                    if ((wine != null)) {
                        if(wine.isVisible()) {
                            if (wine.interact("Cast")) {
                                //After interacting with our flax, we can add a check if it's still valid
                                //This isn't required, you can check for player animation also
                                //If you'd use player animation, you'd check if it went back to idle after picking the flax
                                Execution.delayWhile(() -> wine.isValid(), 3000, 4000);
                            } else {
                                System.out.println("Cant cast?");
                            }
                        }else{
                            Camera.turnTo(wine);
                        }
                    } else {
                        System.out.println("no wine here mate!");
                    }


                    //maybe do antiban?
                } else if(wine!=null) {
                    if(InterfaceWindows.getMagic().isOpen())
                        if(Magic.TELEKINETIC_GRAB.activate())
                            System.out.println("Telegrab selected");
                    else
                        InterfaceWindows.getMagic().open();

                } else{
                    if(InterfaceWindows.getInventory().isOpen()) {
                        if (jug != null && jug.first() != null)
                            jug.first().interact("Drop");

                        if (food.first() != null && Inventory.getUsedSlots() > 26) {
                            food.first().interact("Drop");
                        }
                        if (!WorldHop.isOpen())
                            WorldHop.open();
                    }else{
                        InterfaceWindows.getInventory().open();
                    }
                }

            } else {
            Magic.TELEKINETIC_GRAB.deactivate();
            //Click somewhere randomly on the screen.
            System.out.println("DONE");
        }
    }
}
