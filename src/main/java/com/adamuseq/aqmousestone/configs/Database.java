package com.adamuseq.aqmousestone.configs;

import com.adamuseq.aqmousestone.Main;
import com.adamuseq.aqmousestone.configs.core.Configuration;
import com.adamuseq.aqmousestone.configs.core.impl.ConfigurationImpl;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public final class Database {

    private static Configuration configuration;

    public Database(final Main plugin){
        configuration = new ConfigurationImpl(new File(plugin.getDataFolder(), "database.yml"), this.getClass());
    }

    public static ArrayList<Player> LIST_DIGINGPLAYERS = Lists.newArrayList();

    public static void save(){
        configuration.save();
    }

    public static void reload(){
        configuration.save();
    }

}
