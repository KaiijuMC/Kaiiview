package dev.kugge.optikaii.limiter;

import dev.kugge.optikaii.Optikaii;
import dev.kugge.optikaii.util.AfkConfig;import dev.kugge.optikaii.util.PlayerAfkStat;

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
            AfkConfig afk = Optikaii.worldConfig.get(player.getWorld().getName()).afk();

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
                    if (afk.viewEnabled()) setViewDistance(player, player.getWorld().getViewDistance());
                    if (afk.simulationEnabled()) setSimulationDistance(player, player.getWorld().getSimulationDistance());
                }
                return;
            }

            // Same pos: set AFK if needed
            if (System.currentTimeMillis() - playerStat.getLastCheck() >= afk.duration() * 1000L && !playerStat.isAfk()) {
                playerStat.setAfk(true);
                if (afk.viewEnabled()) setViewDistance(player, afk.viewDistance());
                if (afk.simulationEnabled()) setSimulationDistance(player, afk.simulationDistance());
            }
        });
    }
}
