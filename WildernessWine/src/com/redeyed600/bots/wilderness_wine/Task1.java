package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Skills;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Task1 extends Task {

    wilderness_wine ww;
    byte[] getBytes ={};
    Web web;

    public Task1(wilderness_wine ww){
        this.ww = ww;

        try {
            //web = FileWeb.fromByteArray(Resources.getAsByteArray("../Resources/varrockToEdgeville.nav")); // load the web with the Resources class, make sure to address this resource in your manifest

            web = SerializableWeb.deserialize(getBytes);
        } catch (Exception e) {
            e.printStackTrace();
            web = null;
        }

//        try {
//            Path path = (Path) Paths.get("..Resources/varrockToEdgeville.nav");
//            data = Files.readAllBytes((java.nio.file.Path) path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            //byteArray = Resources.getAsByteArray(getClass().getResourceAsStream("resources/varrockToEdgeville.nav"));
//            //;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    private SpriteItemQueryResults jug;
    private Player me;
    private SpriteItemQueryResults food;


    @Override
    public boolean validate() {

        if(web != null)
        {
            System.out.println(web.getVertices().size());
        }else System.out.println("shes fucked");


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
//
//        System.out.println("Agility_getmaxlevel:"+ Skill.AGILITY.getMaxLevel());
//        System.out.println("Agility_currentlevel"+Skill.AGILITY.getCurrentLevel());
//        System.out.println("Agility_baselevel"+ Skill.AGILITY.getBaseLevel());
//
//        System.out.println("CONSTITUTION_getmaxlevel:"+Skill.CONSTITUTION.getMaxLevel());
//        System.out.println("CONSTITUTION_currentlevel"+Skill.CONSTITUTION.getCurrentLevel());
//        System.out.println("CONSTITUTION_baselevel"+Skill.CONSTITUTION.getBaseLevel());


        return false;
    }

    @Override
    public void execute() {


    }
}
