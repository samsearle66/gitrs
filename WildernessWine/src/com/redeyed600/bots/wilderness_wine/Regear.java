package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Spell;
import com.runemate.game.api.hybrid.local.SpellBook;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;

public class Regear extends Task {

    private wilderness_wine ww;
    private SpriteItemQueryResults armour;
    private SpriteItemQueryResults airStaff;

    public Regear(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    public boolean validate() {

        Player me = Players.getLocal();

        armour = Inventory.getItems(ww.GC.ARMOUR);
        airStaff = Inventory.getItems(ww.GC.AIRSTAFF);

        //may check if has armour on already or has air staff equiped already
        return me!=null && !Bank.isOpen() && ((armour.first()!=null && armour.size() > 0) || airStaff.first()!=null);
    }

    @Override
    public void execute() {

        //need to deactivate spell...

        if(Magic.getSelected().isSelected())
            Magic.getSelected().deactivate();
        if(InterfaceWindows.getInventory().isOpen()) {
            if (armour.size() > 0) {
                System.out.println("Equip armour");
                armour.first().interact("Wear");
            }
            if (airStaff.first() != null) {
                System.out.println("Weild staff");
                airStaff.first().interact("Wield");
            }
        }else{
            InterfaceWindows.getInventory().open();
        }
    }


}
