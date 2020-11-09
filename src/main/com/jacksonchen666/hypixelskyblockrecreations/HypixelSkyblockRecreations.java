package com.jacksonchen666.hypixelskyblockrecreations;

import com.jacksonchen666.hypixelskyblockrecreations.commands.HSRCommand;
import com.jacksonchen666.hypixelskyblockrecreations.enchantments.CustomEnchantments;
import com.jacksonchen666.hypixelskyblockrecreations.utils.CustomUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.io.IOException;

public class HypixelSkyblockRecreations extends JavaPlugin {
    private static CustomEnchantments customEnchantments;
    private static CustomUtils customUtils;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new HSRCommand(this);
        customEnchantments = new CustomEnchantments(this);
        customUtils = new CustomUtils();
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

    public static CustomEnchantments getCustomEnchantments() {
        return customEnchantments;
    }

    public static CustomUtils getCustomUtils() {
        return customUtils;
    }

    public HypixelSkyblockRecreations() {
        super();
    }

    protected HypixelSkyblockRecreations(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) { // unit testing
        super(loader, description, dataFolder, file);
    }
}
