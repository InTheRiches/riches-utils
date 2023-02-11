package net.projektcontingency.titanium.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public void setCooldown(UUID player, long time){
        if (cooldowns.containsKey(player)) {
            cooldowns.replace(player, System.currentTimeMillis() + time);
        } else {
            cooldowns.put(player, System.currentTimeMillis() + time);
        }
    }

    public long getCooldown(UUID player){
        return cooldowns.getOrDefault(player, 0L);
    }
}
