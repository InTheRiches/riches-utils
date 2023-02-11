package net.projektcontingency.titanium.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PlayerCommand implements CommandExecutor, TabCompleter {

    protected final String commandName;
    protected Map<String, SubCommand> subCommands;
    protected Map<String, StaffSubCommand> staffSubCommands;

    public PlayerCommand(String name) {
        this.subCommands = new HashMap<>();
        this.staffSubCommands = new HashMap<>();

        this.commandName = name;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1)
            return new ArrayList<>(subCommands.keySet());

        if (args.length == 2) {
            return this.subCommands.get(args[0]).getAutocomplete(sender);

//            switch (args[0]) {
//
//                case "add" -> {
//                    ArrayList<OfflinePlayer> players = new ArrayList<>(Arrays.asList(Bukkit.getOfflinePlayers()));
//                    players.remove((Player) sender);
//                    for (Friend friend : onlinePlayer.getFriendsHandler().getFriends())
//                        players.remove(friend.getPlayer());
//                    List<String> names = new ArrayList<>();
//                    for (OfflinePlayer offlinePlayer : players) names.add(offlinePlayer.getName());
//                    return names;
//                }
//            }
        }
        return null;
    }

    public String getCommandName() {
        return this.commandName;
    }
}
