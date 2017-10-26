package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.sun.org.apache.bcel.internal.generic.ARETURN;

public class GC {

    public static int LAWRUNEQUANTITY = 3;
    public static int AIRRUNEQUANTITY = 81;
    public static Boolean AIRSTAFF = true;
    public static final int wildernessArea = 3535;
    public static int wildernessDitch = 3522;
    public static int ALTERDOORX = 2959;
    public static int ALTERDOORY = 3822;
    public static int STAFFOFAIR = 1381;
    public static int AIRRUNE = 556;
    public static int WINEOFZAMORAK = 245;
    public static int FIRERUNE = 554;
    public static int LAWRUNE = 563;
    public static Player me = Players.getLocal();

    public static boolean outOfSuppies(){

        System.out.println("outOfSupplies::::("+Inventory.isFull()+" || "+(Inventory.getQuantity(LAWRUNE) < 1) +"||"+(Inventory.getQuantity(FIRERUNE) < 1)+"|| ("+ !Equipment.contains(STAFFOFAIR) + " && " + (Inventory.getQuantity(AIRRUNE) < 3)+")");
        return (Inventory.isFull() || Inventory.getQuantity(LAWRUNE) < 1 ||
                Inventory.getQuantity(FIRERUNE) < 1||
                (!Equipment.contains(STAFFOFAIR) && Inventory.getQuantity(AIRRUNE) < 3));
    }

    public static boolean bankingCompleted(){

        System.out.println("bankingCompleted::"+!Inventory.isFull()+","+ (Inventory.getQuantity(LAWRUNE) > 0) +"&&("+Equipment.contains(STAFFOFAIR)+"||"+ (Inventory.getQuantity(AIRRUNE) >= 3)+")");

        return  !Inventory.isFull() && Inventory.getQuantity(LAWRUNE) > 0 &&
                Inventory.getQuantity(FIRERUNE) > 0 &&
                (Equipment.contains(STAFFOFAIR) || Inventory.getQuantity(AIRRUNE) >= 3);
    }

    public static boolean greaterThanWildernessArea(){

        return me.getPosition().getY() > wildernessArea;
    }

    public static boolean lessThanWildernessArea(){

        return me.getPosition().getY() < wildernessArea;
    }

    public static boolean greaterThanDitch(){

        return me.getPosition().getY() > wildernessDitch;
    }

    //private final Area.Circular alter = new Area.Circular(new Coordinate(2955,2821,0),2);
    //2821 alter inside
    //2950,3821
    //2958

    public static boolean lessThanAlterDoor(){

        System.out.println(me.getPosition().getX() +" <= "+ ALTERDOORX +" && "+ me.getPosition().getY() +" <= "+ ALTERDOORY);

        return me.getPosition().getX() <= ALTERDOORX && me.getPosition().getY() <= ALTERDOORY;
    }
}
