package com.redeyed600.bots.wilderness_wine;

import com.runemate.game.api.script.framework.task.Task;

import java.io.*;

public class ConsoleWrite {

    wilderness_wine ww;

    public ConsoleWrite(wilderness_wine ww){
        this.ww = ww;
    }

    public void writeToConsole(String output, Class c){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\output.txt"), "utf-8"));
            writer.append(c.getSimpleName() +":"+output +"\n");

        } catch (IOException ex) {
            // report
        } finally {
            try {writer.flush(); writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }
}
