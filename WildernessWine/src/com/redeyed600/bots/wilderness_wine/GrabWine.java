package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Chatbox;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldHop;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.task.Task;

public class GrabWine extends Task{

    private wilderness_wine ww;

    public GrabWine(wilderness_wine ww){
        this.ww = ww;
    }

   // private final Area.Circular alter = new Area.Circular(new Coordinate(2933,3516,0), 6);
    private final Area.Absolute table = new Area.Absolute(new Coordinate(2930,3515,0));
    private GroundItem wine;
    private SpriteItemQueryResults food;
    private SpriteItemQueryResults jug;
    private Player me;
    private SpriteItemQueryResults wineOfZamarak;
    private int wineOfZamorakInvPrev = 0;
    private boolean hasCasted = false;

    @Override
    public boolean validate() {
        me = Players.getLocal();

        food = Inventory.getItems(ww.GC.FOODIDS);
        jug = Inventory.getItems("Jug");
        wineOfZamarak = Inventory.getItems(ww.GC.WINEOFZAMORAK);

        wine = GroundItems.newQuery().within(ww.wineWithin).names("Wine of zamorak").results().nearest();

        System.out.println ("GW:"+!ww.GC.underAttackPker() +"&&"+ (me != null) +"&&"+ ww.GC.getAlterPosition().contains(me) +"&&"+ ww.GC.greaterThanAlterY() +"&&"+ ww.GC.bankingCompleted() +"&&"+ !ww.GC.outOfSuppies());
        return (!ww.GC.pkersSpotted()&&!ww.GC.underAttackPker() && me != null && ww.GC.getAlterPosition().contains(me) && ww.GC.greaterThanAlterY() && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies());
    }

    @Override
    public void execute()
    {
        if(!Inventory.isFull())
        {
            wineOfZamorakInvPrev = wineOfZamarak.size();
            if(wine!=null){
                if (Magic.TELEKINETIC_GRAB.isSelected()) {
                    if (wine != null) {
                        if(wine.isVisible()) {
                            if (wine.interact("Cast")) {
                                //()->wineOfZamarak.size()
                               // hasCasted = true;
                                if(ww.GC.underAttackPker() && ww.GC.underAttackNpc())
                                    return;
                                Execution.delayUntil(() -> wine==null || ww.GC.pkersSpotted(),2500,3000);



                            } else {
                                System.out.println("Cant cast?");
                            }

                        }else{
                            System.out.println("Camera is turning");
                            Camera.turnTo(wine);
                        }
                    }
                } else {
                    if (InterfaceWindows.getMagic().isOpen()) {
                        Magic.TELEKINETIC_GRAB.activate();
                        System.out.println("Telegrab selected");
                        //hasCasted = false;
                    } else
                        InterfaceWindows.getMagic().open();
                }
            } else if(wine==null){

                if(Magic.getSelected()!=null && Magic.getSelected().isSelected())
                    Magic.getSelected().deactivate();

                if(((food.first() != null && Inventory.getUsedSlots() > 26) || (jug != null && jug.first() != null))) {
                    if(InterfaceWindows.getInventory().isOpen()) {

                        if (food.first() != null && Inventory.getUsedSlots() > 26) {
                            food.first().interact("Drop");
                            System.out.println("Dropping food");
                        }
                        if (jug != null && jug.first() != null) {
                            jug.first().interact("Drop");
                            System.out.println("Dropping a Jug");
                        }
                    }
                    InterfaceWindows.getInventory().open();
                } else{
                    if (!WorldHop.isOpen() && wine==null)
                        WorldHop.open();
                    else
                        System.out.println("World hop is open.");
                }
            }
            add(new SwitchWorld(ww));
        }
    }
}
