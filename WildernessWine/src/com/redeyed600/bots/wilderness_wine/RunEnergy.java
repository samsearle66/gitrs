package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.task.Task;

public class RunEnergy extends Task {
    wilderness_wine ww;

    public RunEnergy(wilderness_wine ww){
        this.ww = ww;
    }

    @Override
    public boolean validate() {
        return ww.GC.underAttackPker() && ww.GC.hasEnoughEnergy() && !Traversal.isRunEnabled();
    }

    @Override
    public void execute() {
        if(!Traversal.isRunEnabled())
            Traversal.toggleRun();
    }
}
