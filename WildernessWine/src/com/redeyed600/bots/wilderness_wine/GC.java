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
import com.sun.scenario.effect.InvertMask;

public class GC {

    public int[] FOODIDS = {1993};//Jug of wine,needs swordfish!!
    public int[] ENERGYPOTION = {3008,3010,3012,3014};//ENERGYPOTION,1,2,3,4
    public int ENERGYPOTIONQUANTITY = 1;
    public int MINIMUMHP = 25;
    public int MINIMUMFOOD = 20;
    public int LAWRUNEQUANTITY = 20;
    public int AIRRUNEQUANTITY = 81;
    public Boolean AIRSTAFF = true;
    public final int wildernessArea = 3535;
    public int wildernessDitch = 3522;
    public int ALTERDOORX = 2959;
    public int ALTERDOORY = 3816;
    public int STAFFOFAIR = 1381;
    public int AIRRUNE = 556;
    public int WINEOFZAMORAK = 245;
    public int FIRERUNE = 554;
    public int LAWRUNE = 563;
    public int LEVEL20WILDERNESS = 3660;
    public long underAttackTimer = 0;

    public wilderness_wine ww;

    public GC(wilderness_wine ww){
        this.ww = ww;
    }

    public void setUnderAttackTimer(long underAttackTimer){
        this.underAttackTimer = underAttackTimer;
    }

    public boolean outOfSuppies(){

        System.out.println("outOfSupplies:("+Inventory.isFull()+" || "+(Inventory.getQuantity(LAWRUNE) < 2) +"||"+
                (Inventory.getQuantity(FIRERUNE) < 1)+"||"+ Inventory.getItems(FOODIDS).size()+ "||"+ underAttack() +"||"+(Inventory.getItems(ENERGYPOTION).size() < 1)+"||"+" ("+
                !Equipment.contains(STAFFOFAIR) + " && " + (Inventory.getQuantity(AIRRUNE) < 3)+")");

        return (Inventory.isFull() || Inventory.getQuantity(LAWRUNE) < 2 ||
                Inventory.getQuantity(FIRERUNE) < 1|| Inventory.getItems(FOODIDS).size() < 6 || underAttack()|| Inventory.getItems(ENERGYPOTION).size() < 1 ||
        (!Equipment.contains(STAFFOFAIR) && Inventory.getQuantity(AIRRUNE) < 3));
    }

    public boolean underAttack(){

        Player me = Players.getLocal();

        long currentTime = System.currentTimeMillis();

        LocatableEntityQueryResults target = Players.getLoaded(me);

        if(me !=null && target.nearest() != null && (underAttackTimer < currentTime)) {
            System.out.println("Is under attack");
            setUnderAttackTimer(currentTime + 40000);//10sec 1min 40sec
        }

        System.out.println("underAttack:"+underAttackTimer+">"+currentTime);

        return underAttackTimer > currentTime;

    }

    //If player is under attack and has low energy
    public boolean hasEnoughEnergy(){
        return Skill.AGILITY.getCurrentLevel() > Skill.AGILITY.getMaxLevel() * 0.10f;
    }


    public boolean bankingCompleted(){

        System.out.println("bankingCompleted::"+!Inventory.isFull()+","+ (Inventory.getQuantity(LAWRUNE) > 0) +"&&("+Equipment.contains(STAFFOFAIR)+"||"+ (Inventory.getQuantity(AIRRUNE) >= 3)+")");

        return  !Inventory.isFull() && Inventory.getQuantity(LAWRUNE) > 0 &&
                Inventory.getQuantity(FIRERUNE) > 0 &&
                (Equipment.contains(STAFFOFAIR) || Inventory.getQuantity(AIRRUNE) >= 3);
    }

    public boolean greaterThanLevel20Wilderness(){
        Player me = Players.getLocal();
        return me.getPosition().getY() > LEVEL20WILDERNESS;
    }

    public boolean greaterThanWildernessArea(){
        Player me = Players.getLocal();

        return me != null && me.getPosition().getY() > wildernessArea;
    }

    public boolean greaterThanDitch(){
        Player me = Players.getLocal();

        return me != null && me.getPosition().getY() > wildernessDitch;
    }

    //private final Area.Circular alter = new Area.Circular(new Coordinate(2955,2821,0),2);
    //2821 alter inside
    //2950,3821
    //2958

    public boolean hasEnoughHealth() {
        return Skill.CONSTITUTION.getCurrentLevel() > MINIMUMHP;
    }

    public boolean greaterThanAlter(){
        Player me = Players.getLocal();

        System.out.println("greaterThanAlter:"+me.getPosition().getY() +" > "+ ALTERDOORY);
        return me != null && me.getPosition().getY() > ALTERDOORY;
    }

    public boolean isAnimating() {
        Player me = Players.getLocal();
        if (me != null) {
            return me.getAnimationId() != -1;
        }
        return true;
    }
}
