package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
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

        armour = Inventory.getItems(ww.GC.ARMOUR);
        airStaff = Inventory.getItems(ww.GC.AIRSTAFF);

        System.out.println("R:"+!Bank.open() +"&&(("+ (armour.first()!=null)+ "&&"+ (armour.size() > 0) +")||"+ (airStaff.first()!=null)+")");

        //may check if has armour on already or has air staff equiped already
        return !Bank.open() && ((armour.first()!=null && armour.size() > 0) || airStaff.first()!=null);
    }

    @Override
    public void execute() {

        if(armour.size()>0)
        {
            System.out.println("Equip armour");
            armour.first().interact("Wear");
        }
        if(airStaff.first()!=null)
        {
            System.out.println("Weild staff");
            airStaff.first().interact("Wield");
        }
    }


}
