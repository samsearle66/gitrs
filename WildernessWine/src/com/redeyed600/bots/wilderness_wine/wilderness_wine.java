package com.redeyed600.bots.wilderness_wine;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.task.TaskBot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class wilderness_wine extends TaskBot {

    public GC GC = new GC(this);
   // public  ConsoleWrite CW = new ConsoleWrite(this);
    public Random rand = new Random();

    public Map<String, String> listOfPkers;

    public int NUMBEROFWINETELEGRABED;
    public int NUMBEROFWINELOST;
    public int WINELOSTATTEMP;
    public final StopWatch runtime;
    //public final Area.Circular alterDoorArea = new Area.Circular(new Coordinate(2956,3820,0), 4);//was 4

    public final Area.Rectangular alterDoorArea = new Area.Rectangular(new Coordinate(2955,3819,0),new Coordinate(2960,3822,0));//2957.2958
    public final Area.Circular alter = new Area.Circular(new Coordinate(2951,3818,0),2);


    public wilderness_wine(){
        runtime = new StopWatch();
        listOfPkers = new HashMap<>();
        NUMBEROFWINETELEGRABED = 0;
        WINELOSTATTEMP = 5;
        NUMBEROFWINELOST = 0;
    }

    @Override
    public void onStart(String... strings){
        super.onStart(strings);
        setLoopDelay(300,666);
        GameEvents.Universal.LOBBY_HANDLER.disable();
        runtime.start();

        //add(new Task1(this));
        //add(new Heal(this), new WalkToBank(this), new BankInterface(this), new WalkToNorthOfDitch(this), new WalkToLevel20Wilderness(this), new WalkToAlterDoor(this), new WalkToAlter(this), new GrabWine(this), new WalkToAlterDoor(this), new WalkToLevel20Wilderness(this), new WalkToNorthOfDitch(this), new WalkToSouthOfDitch(this), new WalkToLumbridgeCastleStairs(this));
        //add(new WalkToBank(), new BankInterface());
        //add(new GrabWine());
        //add(new Task1());
        //add(new Regear(this), new Teleport(this), new WalkToLumbridgeCastleStairs(this) ,new IsAtStairs(this),new WalkToLumbridgeCastleBank(this), new BankInterfaceCastle(this), new Regear(this), new Teleport(this), new WalkToSouthOfDitch(this));


        add(new EndBot(this),new SwitchWorld(this), new Heal(this), new Teleport(this), new EnergyPotion(this),new RunEnergy(this),new Regear(this), new WalkToBank(this), new BankInterfaceCastle(this), new WalkToNorthOfDitch(this), new WalkToLevel20Wilderness(this), new WalkToAlterDoor(this), new WalkToAlter(this), new GrabWine(this), new WalkToAlterDoor(this), new WalkToLevel20Wilderness(this), new WalkToNorthOfDitch(this), new WalkToSouthOfDitch(this), new WalkToLumbridgeCastleStairs(this) ,new IsAtStairs(this),new WalkToLumbridgeCastleBank(this), new BankInterfaceCastle(this), new WalkToSouthOfDitch(this));

        //add(new SwitchWorld(this));

        //add(new WalkToNorthOfDitch(this));
        //



        //add(new Task1(this));

    }
}
