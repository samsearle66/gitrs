package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Skills;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.player_sense.PlayerSense;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

public class GC {

    public static String[] FOODIDS = {"Jug of wine", "Swordfish"};
    public static int MINIMUMHP = 26;
    public static int MINIMUMFOOD = 20;
    public static int LAWRUNEQUANTITY = 3;
    public static int AIRRUNEQUANTITY = 81;
    public static Boolean AIRSTAFF = true;
    public static final int wildernessArea = 3535;
    public static int wildernessDitch = 3522;
    public static int ALTERDOORX = 2959;
    public static int ALTERDOORY = 3816;
    public static int STAFFOFAIR = 1381;
    public static int AIRRUNE = 556;
    public static int WINEOFZAMORAK = 245;
    public static int FIRERUNE = 554;
    public static int LAWRUNE = 563;
    public static int LEVEL20WILDERNESS = 3660;
    public static long underAttackTimer = 0;

    public static void setUnderAttackTimer(long underAttackTimer){
        underAttackTimer = underAttackTimer;
    }

    public static boolean outOfSuppies(){

        System.out.println("outOfSupplies:("+Inventory.isFull()+" || "+(Inventory.getQuantity(LAWRUNE) < 2) +"||"+
                (Inventory.getQuantity(FIRERUNE) < 1)+"||"+ Inventory.getItems(GC.FOODIDS).size()+ "||"+ underAttack() +"||"+" ("+
                !Equipment.contains(STAFFOFAIR) + " && " + (Inventory.getQuantity(AIRRUNE) < 3)+")");

        return (Inventory.isFull() || Inventory.getQuantity(LAWRUNE) < 2 ||
                Inventory.getQuantity(FIRERUNE) < 1|| Inventory.getItems(GC.FOODIDS).size() < 6 || underAttack()||
        (!Equipment.contains(STAFFOFAIR) && Inventory.getQuantity(AIRRUNE) < 3));
    }

    public static boolean underAttack(){

        long currentTime = System.currentTimeMillis();

        LocatableEntityQueryResults target = Players.getLoaded(Players.getLocal());

        if(Players.getLocal() !=null && target.nearest() != null) {
            setUnderAttackTimer(System.currentTimeMillis() + 100000);//1min 40sec
        }

        return underAttackTimer > currentTime;

    }

    public static boolean bankingCompleted(){

        System.out.println("bankingCompleted::"+!Inventory.isFull()+","+ (Inventory.getQuantity(LAWRUNE) > 0) +"&&("+Equipment.contains(STAFFOFAIR)+"||"+ (Inventory.getQuantity(AIRRUNE) >= 3)+")");

        return  !Inventory.isFull() && Inventory.getQuantity(LAWRUNE) > 0 &&
                Inventory.getQuantity(FIRERUNE) > 0 &&
                (Equipment.contains(STAFFOFAIR) || Inventory.getQuantity(AIRRUNE) >= 3);
    }

    public static boolean greaterThanLevel20Wilderness(){
        return Players.getLocal().getPosition().getY() > LEVEL20WILDERNESS;
    }

    public static boolean greaterThanWildernessArea(){

        return Players.getLocal() != null && Players.getLocal().getPosition().getY() > wildernessArea;
    }

    public static boolean greaterThanDitch(){

        return Players.getLocal() != null && Players.getLocal().getPosition().getY() > wildernessDitch;
    }

    //private final Area.Circular alter = new Area.Circular(new Coordinate(2955,2821,0),2);
    //2821 alter inside
    //2950,3821
    //2958

    public static String[] getFoodId() {
        return FOODIDS;
    }

    public static boolean hasEnoughHealth() {
        return Skill.CONSTITUTION.getCurrentLevel() > MINIMUMHP;
    }

    public static boolean greaterThanAlter(){
        System.out.println("greaterThanAlter:"+Players.getLocal().getPosition().getY() +" > "+ ALTERDOORY);
        return Players.getLocal() != null && Players.getLocal().getPosition().getY() > ALTERDOORY;
    }
}
