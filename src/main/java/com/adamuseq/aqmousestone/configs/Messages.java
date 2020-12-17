package com.adamuseq.aqmousestone.configs;

import com.adamuseq.aqmousestone.Main;
import com.adamuseq.aqmousestone.configs.core.Configuration;
import com.adamuseq.aqmousestone.configs.core.impl.ConfigurationImpl;

import java.io.File;

public final class Messages {

    private static Configuration configuration;

    public Messages(final Main plugin){
        configuration = new ConfigurationImpl(new File(plugin.getDataFolder(), "messages.yml"), this.getClass());
    }

    public static String MESSAGES_ONMESSAGE = "&aStoniarka on.";
    public static String MESSAGES_OFFMESSAGE = "&cStoniarka off.";


    public static void save(){
        configuration.save();
    }

    public static void reload(){
        configuration.reload();
    }
}
