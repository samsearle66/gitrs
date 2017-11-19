package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Spell;
import com.runemate.game.api.hybrid.local.SpellBook;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class Regear extends Task {

    private wilderness_wine ww;
    private SpriteItemQueryResults armour;
    private SpriteItemQueryResults airStaff;
    private SpriteItemQueryResults funkyHead;
    private SpriteItemQueryResults funkyCape;

    public Regear(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    public boolean validate() {

        Player me = Players.getLocal();

        armour = Inventory.getItems(ww.GC.ARMOUR);
        airStaff = Inventory.getItems(ww.GC.STAFFOFAIR);

        funkyHead = Inventory.getItems(ww.GC.funkyHead);
        funkyCape = Inventory.getItems(ww.GC.funkyCape);

        //may check if has armour on already or has air staff equiped already
        return me!=null && !Bank.isOpen() && ((armour.first()!=null && armour.size() > 0) || (airStaff.first()!=null && !Equipment.contains(ww.GC.STAFFOFAIR)) ||
                (funkyHead.first()!=null && !Equipment.containsAnyOf(ww.GC.funkyHead)) || (funkyCape.first()!=null && !Equipment.containsAnyOf(ww.GC.funkyCape)));
    }

    @Override
    public void execute() {

        //need to deactivate spell...

        if(Magic.getSelected()!=null && Magic.getSelected().isSelected() && !Magic.VARROCK_TELEPORT.isSelected())
            Magic.getSelected().deactivate();

        if(InterfaceWindows.getInventory().isOpen()) {
            if (armour.size() > 0) {
                System.out.println("Equip armour");
                armour.first().interact("Wear");
                Execution.delayUntil(() -> Inventory.getSelectedItem()!=null, 1400, 1600);
            }
            if (airStaff.first() != null && !Equipment.contains(ww.GC.STAFFOFAIR)) {
                System.out.println("Weild staff");
                airStaff.first().interact("Wield");
                Execution.delayUntil(() -> Inventory.getSelectedItem()!=null, 1400, 1600);

            }
            if(funkyHead.first()!=null && !Equipment.containsAnyOf(ww.GC.funkyHead)){
                System.out.println("equip funky head");
                funkyHead.first().interact("Wear");
                Execution.delayUntil(() -> Inventory.getSelectedItem()!=null, 1400, 1600);

            }
            if(funkyCape.first()!=null && !Equipment.containsAnyOf(ww.GC.funkyCape)){
                System.out.println("equip funky head");
                funkyCape.first().interact("Wear");
                Execution.delayUntil(() -> Inventory.getSelectedItem()!=null, 1400, 1600);

            }

        }else{
            InterfaceWindows.getInventory().open();
        }
    }


}
