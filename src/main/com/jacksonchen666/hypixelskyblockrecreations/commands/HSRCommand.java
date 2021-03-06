package com.jacksonchen666.hypixelskyblockrecreations.commands;

import com.jacksonchen666.hypixelskyblockrecreations.base.BaseItem;
import com.jacksonchen666.hypixelskyblockrecreations.utils.ChatColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class HSRCommand implements CommandExecutor, TabCompleter {
    @SuppressWarnings("SpellCheckingInspection")
    public static final String commandName = "hypixelskyblockrecreations";
    private static final Map<String, BaseItem> items = new HashMap<>();
    private final JavaPlugin plugin;

    public HSRCommand(JavaPlugin plugin) {
        this.plugin = plugin;

        Objects.requireNonNull(plugin.getCommand(commandName)).setExecutor(this);
    }

    public static void putItem(String key, BaseItem item) {
        items.put(key, item);
    }

    public static Map<String, BaseItem> getItems() {
        return items;
    }

    private String getText(String path) {
        return plugin.getConfig().getString(path);
    }

    private String getText(String path, String prefix) {
        return getText(path).replace("{prefix}", prefix);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) { // Console. Do your thing or just ignore.
            plugin.getServer().getConsoleSender().sendMessage(ChatColors.color(getText("errors.console_execution").replace("{prefix}", getText("errors.error_prefix"))));
            return false;
        }

        Player p = (Player) commandSender;
        String prefix = getText("prefix");
        if (args.length >= 2) {
            if (args[0].equals("giveItem")) {
                for (String key : items.keySet()) {
                    if (args[1].equals(key)) {
                        items.get(key).giveItem(p);
                        return true;
                    }
                }
                p.sendMessage(ChatColors.color(getText("errors.unknown_item", prefix).replace("{name}", args[1])));
                return false;
            }
        }
        else {
            p.sendMessage(ChatColors.color(getText("errors.missing_argument", prefix).replace("{argument}", "action")));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        switch (args.length - 1) {
            case 0:
                return Collections.singletonList("giveItem");
            case 1:
                if (args[0].equals("giveItem")) {
                    return new ArrayList<>(items.keySet());
                }
            default:
                return Collections.emptyList();
        }
    }
}
