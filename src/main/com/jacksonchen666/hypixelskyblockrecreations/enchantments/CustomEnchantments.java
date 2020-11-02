package com.jacksonchen666.hypixelskyblockrecreations.enchantments;

import com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting.Replanting;
import org.bukkit.enchantments.Enchantment;
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
        REPLANTING = new Replanting(plugin);
        Enchantment.stopAcceptingRegistrations();
    }
}
