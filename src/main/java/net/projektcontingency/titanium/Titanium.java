package net.projektcontingency.titanium;

import net.projektcontingency.titanium.commands.CommandHandler;
import net.projektcontingency.titanium.database.Database;
import org.bukkit.plugin.java.JavaPlugin;

public class Titanium extends JavaPlugin {

    private static Titanium instance;
    private CommandHandler commandHandler;

    private Database database;

    @Override
    public void onEnable() {
        instance = this;

        this.database = new Database();
        this.commandHandler = new CommandHandler();
    }

    @Override
    public void onDisable() {

    }

    public static Titanium getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return database;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
