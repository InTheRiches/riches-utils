package net.riches.common_utils

import net.riches.common_utils.DELETEMELATER.TestingEvent
import org.bukkit.plugin.java.JavaPlugin

class CommonUtils: JavaPlugin() {

    private var instance: CommonUtils? = null

    override fun onEnable() {
        instance = this

        server.pluginManager.registerEvents(TestingEvent(), this)
    }

    override fun onDisable() {

    }

    fun getInstance(): CommonUtils? {
        return instance;
    }
}