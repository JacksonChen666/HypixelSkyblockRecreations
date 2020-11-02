package com.jacksonchen666.template;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Template extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        new com.jacksonchen666.template.commands.Template(this);
        getServer().getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void saveDefaultConfig() { // Allows you to save your config file as from your plugin
        String config = "config.yml";
        File file = new File(getDataFolder(), config);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
            saveResource(config, false);
        }
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(file);
        }
        catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
