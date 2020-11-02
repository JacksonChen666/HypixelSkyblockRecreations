package com.jacksonchen666.template.commands;

import com.jacksonchen666.template.ChatColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Template implements CommandExecutor {
    private final JavaPlugin plugin;
    public static final String commandName = "template";

    public Template(JavaPlugin plugin) {
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
        p.sendMessage("This is a template command. This has nothing special.");
        return true;
    }
}
