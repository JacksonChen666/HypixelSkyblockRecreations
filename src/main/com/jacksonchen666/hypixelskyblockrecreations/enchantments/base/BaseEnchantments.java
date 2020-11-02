package com.jacksonchen666.hypixelskyblockrecreations.enchantments.base;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public abstract class BaseEnchantments extends Enchantment {
    public BaseEnchantments(NamespacedKey key) {
        super(key);
        Enchantment.registerEnchantment(this);
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return this.getItemTarget().includes(item.getType());
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }
}