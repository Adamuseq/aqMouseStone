package com.adamuseq.aqmousestone;


import com.adamuseq.aqmousestone.configs.Database;
import com.adamuseq.aqmousestone.configs.Messages;
import com.adamuseq.aqmousestone.helpers.LogHelper;
import com.adamuseq.aqmousestone.listeners.MouseStoneListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private LogHelper logHelper;
    private static Main inst;


    @Override
    public void onLoad(){
        this.logHelper = new LogHelper(this);
    }

    @Override
    public void onEnable(){
        this.initialize();
    }


    private void initialize(){
        final long start = System.currentTimeMillis();
        logHelper.info("Inicjacja pluginu aqMouseStone...");
        inst = this;

        new Database(this);
        new Messages(this);

        new MouseStoneListeners(this);


        logHelper.info("Zaladowano plugin aqMouseStone w "+(System.currentTimeMillis()-start)+"ms");
    }

    public static Main getInst() {
        return inst;
    }

    public static Main getPlugin(){
        return Main.getPlugin(Main.class);
    }

    public LogHelper getLogHelper(){
        return logHelper;
    }

}
