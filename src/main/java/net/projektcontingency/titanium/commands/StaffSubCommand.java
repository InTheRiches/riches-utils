package net.projektcontingency.titanium.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class StaffSubCommand {

    private final String permission;
    private final String help;
    private final List<String> defaultAutocomplete;
    public StaffSubCommand(String permission, String help, List<String> defaultAutocomplete) {
        this.permission = permission;
        this.help = help;
        this.defaultAutocomplete = defaultAutocomplete;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public List<String> getAutocomplete(Player player) {
        return new ArrayList<>();
    }

    public List<String> getAutocomplete() {
        return this.defaultAutocomplete;
    };
}
