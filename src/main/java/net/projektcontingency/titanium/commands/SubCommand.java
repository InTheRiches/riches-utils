package net.projektcontingency.titanium.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {
    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract String getHelp();

    public List<String> getAutocomplete(CommandSender sender) {
        return new ArrayList<>();
    }
}
