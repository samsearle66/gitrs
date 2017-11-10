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
    private SpriteItemQueryResults wineOfZamarak;
    private final Area.Rectangular wineWithin = new Area.Rectangular(new Coordinate(2949,3817,0), new Coordinate(2952,3824,0));
    private int wineOfZamorakInvPrev = 0;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        food = Inventory.getItems(ww.GC.FOODIDS);
        jug = Inventory.getItems("Jug");
        wineOfZamarak = Inventory.getItems(ww.GC.WINEOFZAMORAK);

        wine = GroundItems.newQuery().within(wineWithin).names("Wine of zamorak").results().nearest();

        //Banking completed means have runes
        return (!ww.GC.isAnimating() && !ww.GC.underAttackPker() && me != null && alter.contains(me) && ww.GC.greaterThanAlter() && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies());
    }

    @Override
    public void execute()
    {
        if(!Inventory.isFull())
        {
            if (Magic.TELEKINETIC_GRAB.isSelected()) {
                    if (wine != null) {
                        wineOfZamorakInvPrev = wineOfZamarak.size();
                        if(wine.isVisible()) {
                            if (wine.interact("Cast")) {
                                System.out.println("Grabbing wine");
                                //After interacting with our flax, we can add a check if it's still valid
                                //This isn't required, you can check for player animation also
                                //If you'd use player animation, you'd check if it went back to idle after picking the flax
                            } else {
                                System.out.println("Cant cast?");
                            }
                            if(wineOfZamorakInvPrev > wineOfZamarak.size())
                                ww.NUMBEROFWINETELEGRABED++;
                            else {
                                ww.NUMBEROFWINELOST++;
                                ww.WINELOSTATTEMP--;
                            }
                        }else{
                            Camera.turnTo(wine);
                        }
                    } else {
                        System.out.println("no wine here mate!");
                    }


                    //maybe do antiban?
                } else if(wine!=null) {
                    if(InterfaceWindows.getMagic().isOpen()) {
                        Magic.TELEKINETIC_GRAB.activate();
                        System.out.println("Telegrab selected");
                    }
                    else
                        InterfaceWindows.getMagic().open();

                }

                if(wine==null){

                    if((food.first() != null && Inventory.getUsedSlots() > 26) || (jug != null && jug.first() != null)) {
                        if (jug != null && jug.first() != null) {
                            jug.first().interact("Drop");
                            System.out.println("Dropping a Jug");
                        }
                        if (food.first() != null && Inventory.getUsedSlots() > 26) {
                            food.first().interact("Drop");
                            System.out.println("Dropping food");
                        }
                    }
                    

                    if (!WorldHop.isOpen() && wine==null)
                        WorldHop.open();

                }

            } else {
            Magic.TELEKINETIC_GRAB.deactivate();
            //Click somewhere randomly on the screen.
            System.out.println("DONE");
        }
    }
}
