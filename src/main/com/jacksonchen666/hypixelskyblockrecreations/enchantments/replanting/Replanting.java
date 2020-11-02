package com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting;

import com.jacksonchen666.hypixelskyblockrecreations.enchantments.base.BaseEnchantments;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.plugin.java.JavaPlugin;

public class Replanting extends BaseEnchantments {
    private static final String name = "Replanting";

    public Replanting(JavaPlugin plugin) {
        super(new NamespacedKey(plugin, name));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }
}
