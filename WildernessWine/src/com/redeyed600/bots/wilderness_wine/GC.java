package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.Worlds;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;

import java.io.*;
import java.util.List;

public class GC {

    public int[] FOODIDS = {1993};//Jug of wine,needs swordfish!!
    public int[] ENERGYPOTION = {3008,3010,3012,3014};//ENERGYPOTION,1,2,3,4
    public int ENERGYPOTIONQUANTITY = 1;
    public int MINIMUMHP = 25;
    public int MINIMUMFOOD = 16;
    public int LAWRUNEQUANTITY = 16;
    public int AIRRUNEQUANTITY = 84;
    public int FIRERUNEQUANTITY = 2;
    public int AIRSTAFF = 1381;
    public final int NOTHOFDITCH = 3544;
    public int wildernessDitch = 3522;
    public int ALTERDOORX = 2959;
    public int ALTERDOORY = 3816;
    public int STAFFOFAIR = 1381;
    public int AIRRUNE = 556;
    public int WINEOFZAMORAK = 245;
    public int FIRERUNE = 554;
    public int LAWRUNE = 563;
    public int LEVEL20WILDERNESS = 3668;
    public long underAttackTimer = 0;
    public int SOUTHOFDITCH = 3510;
    public wilderness_wine ww;

    public GC(wilderness_wine ww){
        this.ww = ww;
    }

    public void setUnderAttackTimer(long underAttackTimer){
        this.underAttackTimer = underAttackTimer;
    }

    public boolean greaterThanSouthOfDitch(){
        Player me = Players.getLocal();
        return me.getPosition().getY() > SOUTHOFDITCH;
    }

    public boolean outOfSuppies(){

        return (Inventory.isFull() || Inventory.getQuantity(LAWRUNE) < 2 ||
                Inventory.getQuantity(FIRERUNE) < 1|| Inventory.getItems(FOODIDS).size() < 6 || underAttackPker()|| Inventory.getItems(ENERGYPOTION).size() < 1 ||
        (!Equipment.contains(STAFFOFAIR) && !Inventory.contains(STAFFOFAIR) && Inventory.getQuantity(AIRRUNE) < 3));
    }

    private final int WILDERNESSALTERDEPTH = 35;

    public boolean pkersSpotted(){

        List<Player> pker = Players.getLoaded().asList();

        Player me = Players.getLocal();

        if(me!=null && pker!=null && pker.size() > 1) {

            for(Player p: pker)
            {
                if(!p.equals(me) && (p.getCombatLevel() >= 30) && (p.getCombatLevel() <= me.getCombatLevel() + WILDERNESSALTERDEPTH)){

                    if(Mouse.getSpeedMultiplier()==1.0)
                        Mouse.setSpeedMultiplier(2.5);

//                    if(ww.GC.greaterThanAlter()) {
//                       // ww.listOfPkers.put(p.getName(), "Alter World: " + Worlds.getCurrent() + ", Player Name:" + p.getName() + ", Combat:" + p.getCombatLevel() +"/n");
//                    }
//                    if(!ww.GC.greaterThanAlter() && ww.GC.greaterThanLevel20Wilderness()) {
//                      //  ww.listOfPkers.put(p.getName(), "Wilderness  World: " + Worlds.getCurrent() + ", Player Name: " + p.getName() + ", Combat: " + p.getCombatLevel() +"/n");
//                    }

                    //ww.CW.writeToConsole();

                    return true;
                }else{
                    if(Mouse.getSpeedMultiplier()>1.0)
                        Mouse.setSpeedMultiplier(1.0);
                }
            }

        }
        return false;
    }

    public boolean underAttackPker(){

        Player me = Players.getLocal();

        long currentTime = System.currentTimeMillis();

        LocatableEntityQueryResults target = Players.getLoaded(me);

        //System.out.println("underAttackPker:"+(me !=null) +"&&"+ (me.getHealthGauge()!=null) +"&&"+  (target.nearest() != null) +"&&"+ (underAttackTimer < currentTime));

        if(me !=null && me.getHealthGauge()!=null &&  target.nearest() != null && (underAttackTimer < currentTime)) {
            System.out.println("Under attack");
            setUnderAttackTimer(currentTime + 60000);//10sec 1min 40sec
        }

        return underAttackTimer > currentTime;

    }

    public boolean underAttackNpc(){
        Player me = Players.getLocal();
        LocatableEntityQueryResults target = Npcs.getLoaded(me);
        if(me!=null){
            return me.getHealthGauge()!=null &&  target.nearest() != null;
        }
        return false;
    }

    //If player is under attack and has low energy
    public boolean hasEnoughEnergy(){
        return Traversal.getRunEnergy() >= 10;
    }


    public boolean bankingCompleted(){

        return  !Inventory.isFull() && Inventory.getQuantity(LAWRUNE) > 0 &&
                Inventory.getQuantity(FIRERUNE) > 0 &&
                (Equipment.contains(STAFFOFAIR) || Inventory.getQuantity(AIRRUNE) >= 3);
    }


    public final int LUMBRIDGECASTLELEVEL1 = 3204;
    public boolean greaterThanLumbridgeCastleLevel1(){
        Player me = Players.getLocal();
        return me.getPosition().getX() > LUMBRIDGECASTLELEVEL1;
    }

    public final int LUMBRIDGECASTLELEVEL3 = 3211;
    public boolean greaterThanLumbridgeCastleLevel3(){
        Player me = Players.getLocal();
        return me.getPosition().getX() > LUMBRIDGECASTLELEVEL3;
    }

    public final int[] LUMBRIDGECASTLESTAIRS = {16671,16672};
    public final int[] ARMOUR = {1199,1073,1123};

    public final int VARROCKCENTER = 3410;

    public boolean greaterThanVarrockCenter(){
        Player me = Players.getLocal();
        return me!=null && me.getPosition().getY() > VARROCKCENTER;
    }

    public boolean hasVarrockTeleportRunes(){
        return((Inventory.getQuantity(AIRRUNE)>=3||Equipment.contains(AIRSTAFF))&&Inventory.getQuantity(FIRERUNE)>=1&&Inventory.getQuantity(LAWRUNE)>=1);
    }


    public boolean greaterThanLevel20Wilderness(){
        Player me = Players.getLocal();
        return me!=null && me.getPosition().getY() > LEVEL20WILDERNESS;
    }

    public boolean greaterThanNorthOfDitch(){
        Player me = Players.getLocal();

        return me != null && me.getPosition().getY() > NOTHOFDITCH;
    }

    public boolean greaterThanDitch(){
        Player me = Players.getLocal();

        return me != null && me.getPosition().getY() > wildernessDitch;
    }


    public boolean hasEnoughHealth() {
        return Skill.CONSTITUTION.getCurrentLevel() > MINIMUMHP;
    }

    public boolean greaterThanAlter(){
        Player me = Players.getLocal();
        return me != null && me.getPosition().getY() > ALTERDOORY;
    }

    public boolean isAnimating() {
        Player me = Players.getLocal();
        if (me != null) {
            return me.getAnimationId() != -1;
        }
        return true;
    }

    public byte[] getByteArray(String filesName){
        byte[] getBytes = {};

        try {
            File file = new File(filesName);
            getBytes = new byte[(int) file.length()];
            InputStream is = new FileInputStream(file);
            is.read(getBytes);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getBytes;
    }

    public int previousworldWorld = 0;

    public int getPreviousWorld(){ return this.previousworldWorld; }

    public void setPreviousWorld(int world){
        this.previousworldWorld = world;
    }


    public int newWorld = 0;

    public int getNewWorld(){
        return this.newWorld;
    }
    public void setNewWorld(int world){
        this.newWorld = world;
    }

//    public boolean IsBroken(){
//        if
//    }
}
