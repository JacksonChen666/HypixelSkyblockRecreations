package com.jacksonchen666.hypixelskyblockrecreations.base.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class AGUI implements IGUI, Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof IGUI) {
            event.setCancelled(true);
            IGUI gui = (IGUI) event.getInventory().getHolder();
            gui.onGUIClick(event);
        }
    }
}
