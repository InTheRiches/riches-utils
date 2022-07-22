package net.projektcontingency.titanium.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class StaffSubCommand {
    public abstract String getPermission();

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract String getHelp();

    public List<String> getAutocomplete(Player player) {
        return new ArrayList<>();
    }

    public List<String> getAutocomplete() {
        return new ArrayList<>();
    };
}
