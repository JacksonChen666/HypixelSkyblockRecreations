package com.jacksonchen666.hypixelskyblockrecreations.base;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface BaseItem {
    void giveItem(Player player);

    ItemStack createItem();
}
