package com.jacksonchen666.hypixelskyblockrecreations.utils;

import com.jacksonchen666.hypixelskyblockrecreations.utils.supercompactor.SuperCompactor;
import org.bukkit.inventory.ItemStack;

public class CustomUtils {
    public final ItemStack SUPER_COMPACTOR;

    public CustomUtils() {
        SUPER_COMPACTOR = new SuperCompactor().createItem();
    }
}
