package dev.kugge.optikaii.limiter;

import dev.kugge.optikaii.Optikaii;
import dev.kugge.optikaii.util.PlayerAfkStat;
import dev.kugge.optikaii.util.WorldConfig;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

import static dev.kugge.optikaii.util.DistanceManager.setSimulationDistance;
import static dev.kugge.optikaii.util.DistanceManager.setViewDistance;

public class AfkWatcher implements Runnable {

    private HashMap<UUID, PlayerAfkStat> statHashMap = new HashMap<>();

    @Override
    public void run () {
        Bukkit.getOnlinePlayers().forEach(player -> {
            UUID playerId = player.getUniqueId();
            PlayerAfkStat playerStat = this.statHashMap.get(playerId);
            WorldConfig worldConfig = Optikaii.worldConfig.get(player.getWorld().getName());

            // Not in stats: Create player stats
            if (playerStat == null) {
                this.statHashMap.put(playerId, PlayerAfkStat.create(player.getLocation()));
                return;
            }

            // Changed pos: refresh pos
            if (!playerStat.getLastLocation().equals(player.getLocation())) {
                playerStat.setLastLocation(player.getLocation());
                playerStat.setLastCheck(System.currentTimeMillis());
                if (playerStat.isAfk()) {
                    playerStat.setAfk(false);
                    if (worldConfig.afkViewEnabled()) setViewDistance(player, player.getWorld().getViewDistance());
                    if (worldConfig.afkSimulationEnabled()) setSimulationDistance(player, player.getWorld().getSimulationDistance());
                }
                return;
            }

            // Same pos: set AFK if needed
            if (System.currentTimeMillis() - playerStat.getLastCheck() >= worldConfig.afkDuration() * 1000L && !playerStat.isAfk()) {
                playerStat.setAfk(true);
                if (worldConfig.afkViewEnabled()) setViewDistance(player, worldConfig.afkViewDistance());
                if (worldConfig.afkSimulationEnabled()) setSimulationDistance(player, worldConfig.afkSimulationDistance());
            }
        });
    }
}
