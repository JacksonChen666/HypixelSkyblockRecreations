package com.jacksonchen666.template;

import org.bukkit.ChatColor;

public class ChatColors {
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
