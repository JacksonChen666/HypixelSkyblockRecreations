package com.jacksonchen666.hypixelskyblockrecreations.enchantments;

import com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting.Replanting;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CustomEnchantments {
    public Enchantment REPLANTING;

    public CustomEnchantments(JavaPlugin plugin) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents((Listener) (REPLANTING = new Replanting(plugin)), plugin);
        Enchantment.stopAcceptingRegistrations();
    }
}
