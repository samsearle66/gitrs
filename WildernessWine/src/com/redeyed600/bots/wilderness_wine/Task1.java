package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Skills;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.InteractableRectangle;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.WorldSelect;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.web.SerializableWeb;
import com.runemate.game.api.hybrid.location.navigation.web.Web;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.osrs.local.hud.interfaces.Magic;
import com.runemate.game.api.script.framework.task.Task;
import sun.security.krb5.internal.LoginOptions;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Task1 extends Task {

    wilderness_wine ww;
    Web web;//3130 3493
    //private final Area.Circular northOfDitch = new Area.Circular(new Coordinate(3130,3493,0),4);
    private final Area.Circular edgevilleBank = new Area.Circular(new Coordinate(3096,3494,0), 2);

    private SpriteItemQueryResults jug;
    private Player me;
    private SpriteItemQueryResults food;

    public Task1(wilderness_wine ww){
        this.ww = ww;
        Worlds.setPreferred(randomWorld = worlds[rand.nextInt(worlds.length)]);
        //GameEvents.Universal.LOGIN_HANDLER.disable();
//        try {
//            web = SerializableWeb.deserialize(ww.GC.getByteArray("C:\\Users\\Skippy\\gitrs\\WildernessWine\\src\\com\\redeyed600\\bots\\Resources\\varrockToEdgeville.nav"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            web = null;
//        }

            Worlds.setPreferred(394);
            GameEvents.Universal.LOBBY_HANDLER.enable();
            GameEvents.Universal.LOGIN_HANDLER.enable();

    }

    @Override
    public boolean validate() {
//        if(web != null)
//        {
//            System.out.println(web.getVertices().size());
//        }else System.out.println("shes fucked");
//
//        WebPath path = null;
//
//
//        if (web != null) { // Make sure the web got loaded properly
//            path = web.getPathBuilder().buildTo(edgevilleBank);
//        }else{System.out.println("dis web is null");}
//
//        if (path != null) { // IMPORTANT: if the path should be null, the pathbuilder could not manage to build a path with the given web, so always nullcheck!
//                path.step();
//        }else {System.out.println("dis path is null");}


       Player me = Players.getLocal();
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

        //System.out.println((food.first() != null) +"&&"+ food.first().interact("Drop") +"&&"+ (Inventory.getUsedSlots()>26));
        //if(me!=null)
        //    System.out.println("underAttackNpc:"+ ww.GC.underAttackNpc());
        //System.out.println(ww.GC.hasEnoughEnergy());

//
//        if(WorldSelect.open()) {
//            WorldSelect.select(393);
//
//        }
//        else
//            WorldSelect.open();



        return true;
    }

    private int NUMBEROFTRIES = 0;
    private final InteractableRectangle point = new InteractableRectangle(248, 220, 82, 12);
    private int[] worlds = {301,
            308,
            316,
            326,
            335,
            382,
            383,
            384,
            393,
            394};
    private Random rand = new Random();
    private int randomWorld;
    private long is829 = 0;

    @Override
    public void execute() {
        Player me = Players.getLocal();

        long prevTime = System.currentTimeMillis();

        if(me !=null && me.getAnimationId()!=-1 && me.getAnimationId()==723){ //&& (is829 < currentTime)) {
            long currentTime = System.currentTimeMillis();
            System.out.println(currentTime - prevTime);
        }

    }
}
