package com.adamuseq.aqmousestone.configs.core.impl;

import com.adamuseq.aqmousestone.Main;
import com.adamuseq.aqmousestone.configs.core.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigurationImpl implements Configuration {

    private final File file;
    private final Class<?> clazz;

    public ConfigurationImpl(final File file, final Class<?> clazz) {
        this.clazz = clazz;
        this.file = file;
        this.reload();
    }

    @Override
    public void reload() {
        final File config = this.check(this.file);
        try {
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(config);
            this.parse(this.clazz, configuration);
            configuration.save(config);
        }
        catch (Exception ex) {
            Main.getPlugin().getLogHelper().warn("Config error: "+ex.getMessage());
        }
    }

    @Override
    public void save() {
        final File config = this.check(this.file);
        try {
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(config);
            this.parseSave(this.clazz, configuration);
            configuration.save(config);
        }
        catch (Exception ex) {
            Main.getPlugin().getLogHelper().warn("Config error: "+ex.getMessage());
        }
    }
}
