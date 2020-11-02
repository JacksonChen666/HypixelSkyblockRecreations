package com.jacksonchen666.hypixelskyblockrecreations.enchantments;

import com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting.Replanting;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEnchantments {
    private JavaPlugin plugin;

    public CustomEnchantments(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public final Enchantment REPLANTING = new Replanting(plugin);
}
