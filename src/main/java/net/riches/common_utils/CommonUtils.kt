package net.riches.common_utils

import org.bukkit.plugin.java.JavaPlugin

class CommonUtils: JavaPlugin() {

    private var instance: CommonUtils? = null

    override fun onEnable() {
        instance = this;
    }

    override fun onDisable() {

    }

    fun getInstance(): CommonUtils? {
        return instance;
    }
}