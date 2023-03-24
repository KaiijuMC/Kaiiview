package dev.kugge.optikaii.watcher;

import dev.kugge.optikaii.Optikaii;
import dev.kugge.optikaii.config.AfkConfig;
import dev.kugge.optikaii.util.PlayerAfkStat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static dev.kugge.optikaii.util.DistanceManager.setSimulationDistance;
import static dev.kugge.optikaii.util.DistanceManager.setViewDistance;

public class AfkWatcher implements Runnable {

    private HashMap<UUID, PlayerAfkStat> statHashMap = new HashMap<>();

    private final Optikaii plugin;

    public AfkWatcher(Optikaii plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run () {
        Collection<? extends Player> players;
        // Get players sync
        try {
             players = Bukkit.getScheduler().callSyncMethod(this.plugin, Bukkit::getOnlinePlayers).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Player player : players) {
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
                    if (afk.view().enabled()) setViewDistance(this.plugin, player, player.getWorld().getViewDistance());
                    if (afk.simulation().enabled()) setSimulationDistance(this.plugin, player, player.getWorld().getSimulationDistance());
                }
                return;
            }

            // Same pos: set AFK if needed
            if (System.currentTimeMillis() - playerStat.getLastCheck() >= afk.duration() * 1000L && !playerStat.isAfk()) {
                playerStat.setAfk(true);
                if (afk.view().enabled()) setViewDistance(this.plugin, player, afk.view().distance());
                if (afk.simulation().enabled()) setSimulationDistance(this.plugin, player, afk.simulation().distance());
            }
        }
    }
}
