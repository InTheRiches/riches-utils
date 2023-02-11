package net.projektcontingency.titanium.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    private final String help;

    public SubCommand(String help) {
        this.help = help;
    }
    public abstract boolean execute(CommandSender sender, String[] args);

    public String getHelp() {
        return this.help;
    };

    public List<String> getAutocomplete(CommandSender sender) {
        return new ArrayList<>();
    }
}
