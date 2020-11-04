package com.jacksonchen666.hypixelskyblockrecreations.enchantments;

import com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting.Replanting;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CustomEnchantments {
    public Enchantment REPLANTING;
    private JavaPlugin plugin;

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
        this.plugin = plugin;
        REPLANTING = registerEnchantAndEvent(new Replanting(plugin));
        Enchantment.stopAcceptingRegistrations();
    }

    private Enchantment registerEnchantAndEvent(Enchantment enchantment) {
        plugin.getServer().getPluginManager().registerEvents((Listener) enchantment, plugin);
        return enchantment;
    }
}
