package com.jacksonchen666.hypixelskyblockrecreations.commands;

import com.jacksonchen666.hypixelskyblockrecreations.HypixelSkyblockRecreations;
import com.jacksonchen666.hypixelskyblockrecreations.enchantments.CustomEnchantments;
import com.jacksonchen666.hypixelskyblockrecreations.utils.ChatColors;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class HSRCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    public static final String commandName = "hypixelskyblockrecreations";

    public HSRCommand(JavaPlugin plugin) {
        this.plugin = plugin;

        Objects.requireNonNull(plugin.getCommand(commandName)).setExecutor(this);
    }

    private String getText(String path) {
        return plugin.getConfig().getString(path);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) { // Console. Do your thing or just ignore.
            plugin.getServer().getConsoleSender().sendMessage(ChatColors.color(getText("errors.console_execution").replace("{prefix}", getText("errors.error_prefix"))));
            return false;
        }

        Player p = (Player) commandSender;
        ItemStack book = new ItemStack(Material.STONE_HOE);
        ItemMeta meta = book.getItemMeta();
        Objects.requireNonNull(meta).addEnchant(HypixelSkyblockRecreations.getCustomEnchantments().REPLANTING, 1, false);
        book.setItemMeta(meta);
        p.getInventory().addItem(book);
        return true;
    }
}
