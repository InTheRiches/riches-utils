package net.projektcontingency.titanium.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class StaffCommand implements CommandExecutor, TabCompleter {
    private final String commandName;
    private final String permission;

    public StaffCommand(String permission, String commandName) {
        this.permission = permission;
        this.commandName = commandName;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public String getPermission() {
        return this.permission;
    }
}
