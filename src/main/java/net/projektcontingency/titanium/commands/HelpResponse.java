package net.projektcontingency.titanium.commands;

import net.projektcontingency.titanium.internal.Unicode;
import org.bukkit.ChatColor;

import java.util.Map;

public record HelpResponse(Map<String, String> params, String name, String subCommand, String info) {

    public Map<String, String> getParams() {
        return params;
    }

    public String getName() {
        return name;
    }

    public String generateMessage() {
        StringBuilder msg = new StringBuilder();
        msg.append(Unicode.DIVIDER.getText()).append("\n");
        msg.append(ChatColor.GOLD).append("Help: ").append(ChatColor.AQUA).append(this.name).append("\n");
        if (!subCommand.equals(""))
            msg.append(ChatColor.WHITE).append("  SubCommand: ").append(ChatColor.GRAY).append(subCommand).append("\n");
        if (info != null)
            msg.append(ChatColor.WHITE).append("  About: ").append(ChatColor.GRAY).append(info).append("\n");
        msg.append(ChatColor.GOLD).append("  Params:");
        params.forEach((s, s2) -> {
            msg.append("\n     ").append(ChatColor.WHITE).append(s).append(ChatColor.GRAY).append(" - ").append(s2);
        });
        msg.append(Unicode.DIVIDER.getText());
        return msg.toString();
    }
}
