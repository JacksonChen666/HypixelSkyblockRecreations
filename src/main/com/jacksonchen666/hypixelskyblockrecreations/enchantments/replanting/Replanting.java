package com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting;

import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import com.jacksonchen666.hypixelskyblockrecreations.commands.HSRCommand;
import com.jacksonchen666.hypixelskyblockrecreations.enchantments.base.BaseEnchantments;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Replanting extends BaseEnchantments {
    private static final String name = "Replanting";

    public Replanting(JavaPlugin plugin) {
        super(new NamespacedKey(plugin, name));
        ItemStack item = new ItemStack(Material.STONE_HOE);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).addEnchant(HypixelSkyblockRecreations.getCustomEnchantments().REPLANTING, 1, false);
        item.setItemMeta(meta);
        HSRCommand.putItems(name.toLowerCase(), item);
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
