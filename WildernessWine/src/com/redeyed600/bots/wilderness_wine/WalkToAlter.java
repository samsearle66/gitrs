package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.game.api.hybrid.local.Camera;

import java.util.concurrent.Delayed;

public class WalkToAlter extends Task {

    wilderness_wine ww;


    public WalkToAlter(wilderness_wine ww){
        this.ww = ww;
    }

    //private final Area.Circular alter = new Area.Circular(new Coordinate(2950,3821,0),2);
    //2955,2821
    private Player me;
    private GameObject door;
    public final Area.Rectangular leftOfDoor = new Area.Rectangular(new Coordinate(2957,3819,0), new Coordinate(2957,3822,0));
    public final Area.Rectangular rightOfDoor = new Area.Rectangular(new Coordinate(2958,3820,0), new Coordinate(2958,3822,0));
    //public final Area.Rectangular rightOfDoor = new Area.Rectangular(new Coordinate(2958,3819,0), new Coordinate(2958,3822,0));//change to this if you dont want

    @Override
    public boolean validate() {


        door = GameObjects.newQuery().names("Large door").actions("Open").results().nearest();
        me = Players.getLocal();
        return (me != null && !ww.GC.getAlterPosition().contains(me) && ww.GC.bankingCompleted() && !ww.GC.outOfSuppies() && ww.GC.greaterThanDitch() && ww.GC.greaterThanDitch()); //&& ww.GC.greaterThanAlterY());//good
    }

    @Override
    public void execute() {
        System.out.println("Walking to alter");
        WebPath path = null;

        if (ww.wilderness != null) { // Make sure the web got loaded properly
            path = ww.wilderness.getPathBuilder().buildTo(ww.GC.getAlterPosition());
        }

        if (path != null) { // Although BresenhamPath technically always builds a path, it is recommended to nullcheck rather than having the bot crash
            //if it walks back put
            //if !alter.contains(me){
            if (ww.alterDoor.contains(me) && !leftOfDoor.contains(me) && door != null && door.isValid())//for the telegrab spot beside the door.
                add(new IsDoorOpen(ww));
            else {
               if (ww.GC.getAlterPosition().distanceTo(me) >= 3) {
                    path.step();//will walk with the minimap with custom path
               }
               else{
                   if(Camera.getPitch()>0.65) {

                       if (ww.rand.nextInt(1)==0) {
                           final BresenhamPath path1 = BresenhamPath.buildTo(ww.GC.getAlterPosition());
                           if(path1!=null)
                                ViewportPath.convert(path1).step();//bresenhamPath is required for this step
                       }else
                           PredefinedPath.create(ww.GC.getAlterPosition().getPosition()).step();

                       Execution.delayUntil(() -> ww.GC.getAlterPosition().contains(me) || ww.GC.pkersSpotted(),600,1000);

                   }else {
                       Camera.turnTo(0.8);
                   }
               }
            }
            add( new Heal(ww), new EnergyPotion(ww),new RunEnergy(ww));
        }
    }
}
