package net.projektcontingency.titanium.commands;

import net.projektcontingency.titanium.Titanium;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements Listener {

    private final List<StaffCommand> staffCommands = new ArrayList<>();
    private final List<PlayerCommand> playerCommands = new ArrayList<>();

    public CommandHandler() {
        Titanium.getInstance().getServer().getPluginManager().registerEvents(this, Titanium.getInstance());
    }

    public void addPlayerCommand(PlayerCommand command, JavaPlugin plugin) {
        playerCommands.add(command);

        plugin.getCommand(command.getCommandName()).setExecutor(command);
        plugin.getCommand(command.getCommandName()).setTabCompleter(command);
    }

    public void addStaffCommand(StaffCommand command, JavaPlugin plugin) {
        staffCommands.add(command);

        plugin.getCommand(command.getCommandName()).setExecutor(command);
        plugin.getCommand(command.getCommandName()).setTabCompleter(command);
    }

    @EventHandler
    public void onCommandType(PlayerCommandSendEvent e) {
        List<String> blockedCommands = new ArrayList<>();
        blockedCommands.add("bukkit:ver");
        blockedCommands.add("bukkit:plugins");
        blockedCommands.add("spark:spark");

        Player p = e.getPlayer();

        for (StaffCommand staffCommand : staffCommands) {
            if (e.getPlayer().hasPermission(staffCommand.getPermission())) continue;
            blockedCommands.add("lobbysystem:" + staffCommand.getCommandName());
        }
//        for (PlayerCommand playerCommand : playerCommands) {
//            e.getCommands().addAll(List.of(playerCommand.getAliases()));
//        }

        e.getCommands().removeAll(blockedCommands);
    }

}
