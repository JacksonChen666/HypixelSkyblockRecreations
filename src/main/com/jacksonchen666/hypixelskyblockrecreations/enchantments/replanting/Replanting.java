package com.jacksonchen666.hypixelskyblockrecreations.enchantments.replanting;

import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import com.jacksonchen666.hypixelskyblockrecreations.commands.HSRCommand;
import com.jacksonchen666.hypixelskyblockrecreations.enchantments.base.BaseEnchantments;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Replanting extends BaseEnchantments implements Listener {
    private static final String name = "Replanting";

    public Replanting(JavaPlugin plugin) {
        super(new NamespacedKey(plugin, name));
        HSRCommand.putItem(name.toLowerCase(), this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void giveItem(Player player) {
        player.getInventory().addItem(createItem());
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.STONE_HOE);
        item.addEnchantment(HypixelSkyblockRecreations.getCustomEnchantments().REPLANTING, 1);
        return item;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    public static final Map<Material, Material> materials = new HashMap<Material, Material>() {{
        // block broken, required item to replant
        put(Material.WHEAT, Material.WHEAT_SEEDS);
        put(Material.COCOA, Material.COCOA_BEANS);
        put(Material.BEETROOTS, Material.BEETROOT_SEEDS);
        put(Material.CARROTS, Material.CARROT);
        put(Material.POTATOES, Material.POTATO);
        put(Material.SWEET_BERRY_BUSH, Material.SWEET_BERRIES);
        put(Material.NETHER_WART, Material.NETHER_WART);
    }};

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        PlayerInventory inventory = event.getPlayer().getInventory();
        ItemStack handItem = inventory.getItemInMainHand();
        ItemMeta meta;
        try {
            meta = Objects.requireNonNull(handItem.getItemMeta());
        }
        catch (NullPointerException e) {
            return;
        }
        Material requiredItem = materials.get(material);
        if (meta.hasEnchant(HypixelSkyblockRecreations.getCustomEnchantments().REPLANTING) &&
                materials.containsKey(material) && inventory.contains(requiredItem)) {
            ItemStack[] contents = inventory.getContents();
            for (int i = 0, contentsLength = contents.length; i < contentsLength; i++) {
                ItemStack itemStack = contents[i];
                if (itemStack.getType() == requiredItem) {
                    Block block = event.getBlock();
                    block.breakNaturally(handItem);
                    block.setType(material);
                    inventory.setItem(i, new ItemStack(itemStack.getType(), itemStack.getAmount() - 1));
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }
}
