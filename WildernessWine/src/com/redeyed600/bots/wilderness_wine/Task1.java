package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Skills;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;

public class Task1 extends Task {

    wilderness_wine ww;

    public Task1(wilderness_wine ww){
        this.ww = ww;
    }


    private SpriteItemQueryResults jug;
    private Player me;
    private SpriteItemQueryResults food;

    @Override
    public boolean validate() {

//        Player player = Players.getLocal();
//
//        LocatableEntityQueryResults target = Players.getLoaded(player);
//
//        System.out.println(target.nearest());

//        food = Inventory.getItems(GC.FOODIDS);
//        jug = Inventory.getItems("Jug");
//        Player me = Players.getLocal();
//
//        if(me!=null)
//            System.out.println("POSITION!!!!:::::"+me);
//
//        if(jug != null && jug.first() != null && jug.first().interact("Drop"))
//        {
//            jug.first().interact("Drop");
//        } else System.out.println("Cant drop jug");
//
//        if(food.first() != null && food.first().interact("Drop")) {
//            food.first().interact("Drop");
//        } else System.out.println("Cant drop food");

       // System.out.println(Magic.TELEKINETIC_GRAB.isSelected());

        //System.out.println(ww.GC.underAttack());

        System.out.println("Agility_getmaxlevel:"+ Skill.AGILITY.getMaxLevel());
        System.out.println("Agility_currentlevel"+Skill.AGILITY.getCurrentLevel());
        System.out.println("Agility_baselevel"+ Skill.AGILITY.getBaseLevel());

        System.out.println("CONSTITUTION_getmaxlevel:"+Skill.CONSTITUTION.getMaxLevel());
        System.out.println("CONSTITUTION_currentlevel"+Skill.CONSTITUTION.getCurrentLevel());
        System.out.println("CONSTITUTION_baselevel"+Skill.CONSTITUTION.getBaseLevel());

        return false;
    }

    @Override
    public void execute() {


    }
}
