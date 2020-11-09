package com.jacksonchen666.hypixelskyblockrecreations.utils.supercompactor;

import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import com.jacksonchen666.hypixelskyblockrecreations.base.BaseItem;
import com.jacksonchen666.hypixelskyblockrecreations.base.gui.AGUI;
import com.jacksonchen666.hypixelskyblockrecreations.commands.HSRCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

public class SuperCompactor extends AGUI implements BaseItem, Listener {
    private static final String name = "SuperCompactor";

    public SuperCompactor() {
        HSRCommand.putItem(name, this);
    }

    @Override
    public void giveItem(Player player) {
        player.getInventory().addItem(createItem());
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.DISPENSER);
        ItemMeta meta = Objects.requireNonNull(item.getItemMeta());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
        return null;
    }

    public static boolean isSuperCompactor(Inventory inventory) {
        ItemStack superCompactor = HypixelSkyblockRecreations.getCustomUtils().SUPER_COMPACTOR;
        ItemStack item = Arrays.stream(inventory.getContents()).filter(itemStack -> itemStack == superCompactor).findFirst().orElseThrow();
        return inventory.contains(superCompactor) && item.getEnchantmentLevel(Enchantment.DURABILITY) == 10;
    }

    @EventHandler
    public void onInventoryUpdate(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Inventory inventory = ((Player) event.getEntity()).getInventory();
        if (isSuperCompactor(inventory)) {
            // compact logic and checks
            System.out.println("Compactor!");
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() == HypixelSkyblockRecreations.getCustomUtils().SUPER_COMPACTOR) {
            Player player = event.getPlayer();
            Inventory GUI = Bukkit.createInventory(player, 27, "Super Compactor");
            if (GUI.contains(Material.AIR)) {
                ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemStack item2 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                ItemStack[] temp = new ItemStack[] {
                        item, item, item, item, item, item, item, item, item,
                        item, item2, item2, item2, item2, item2, item2, item2, item,
                        item, item, item, item, item, item, item, item, item
                };
                GUI.setMaxStackSize(1);
                GUI.setStorageContents(temp);
            }
            player.openInventory(GUI);
        }
    }

    @Override
    public void onGUIClick(InventoryClickEvent event) {
        int slot = event.getSlot();
        if (slot <= 10 || slot >= 18) {
            event.setCancelled(true);
        }
    }
}