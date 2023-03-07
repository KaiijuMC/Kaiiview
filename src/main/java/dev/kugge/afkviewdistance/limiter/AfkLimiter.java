package dev.kugge.afkviewdistance.limiter;

import dev.kugge.afkviewdistance.AfkViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class AfkLimiter implements Runnable {
    private final HashMap<UUID, Boolean> playerAfk = new HashMap<>();
    private final HashMap<UUID, Location> playerLastLocation = new HashMap<>();
    private final HashMap<UUID, Long> playerLastCheck = new HashMap<>();
    private final boolean viewEnabled;
    private final boolean simulationEnabled;
    private final int duration;
    private final int viewDistance;
    private final int simulationDistance;

    public AfkLimiter(AfkViewDistance plugin) {
        this.viewEnabled = plugin.getConfig().getBoolean("view-distance-enable");
        this.simulationEnabled = plugin.getConfig().getBoolean("simulation-distance-enable");
        this.duration = plugin.getConfig().getInt("duration");
        this.viewDistance = plugin.getConfig().getInt("view-distance");
        this.simulationDistance = plugin.getConfig().getInt("simulation-distance");
    }

    @Override
    public void run () {
        if (!(viewEnabled || simulationEnabled)) return;
        for (Player player: Bukkit.getOnlinePlayers()) {
            UUID playerId = player.getUniqueId();
            Location lastLocation = playerLastLocation.get(playerId);

            if (lastLocation == null) {
                playerLastLocation.put(playerId, player.getLocation());
                playerLastCheck.put(playerId, System.currentTimeMillis());
                playerAfk.put(playerId, false);
                continue;
            }

            if (!lastLocation.equals(player.getLocation())) {
                playerLastLocation.put(playerId, player.getLocation());
                playerLastCheck.put(playerId, System.currentTimeMillis());
                if (playerAfk.get(playerId)) {
                    Bukkit.getLogger().info("Unset AFK Distances for player " + player.getName());
                    playerAfk.put(playerId, false);
                    if (viewEnabled) player.setViewDistance(player.getWorld().getViewDistance());
                    if (simulationEnabled) player.setSimulationDistance(player.getWorld().getSimulationDistance());
                }
                continue;
            }

            long lastCheck = playerLastCheck.get(playerId);

            if (System.currentTimeMillis() - lastCheck >= duration * 1000L) {
                if (!playerAfk.get(playerId)) {
                    Bukkit.getLogger().info("Set AFK Distances for player " + player.getName());
                    playerAfk.put(playerId, true);
                    if (viewEnabled) player.setViewDistance(viewDistance);
                    if (simulationEnabled) player.setSimulationDistance(simulationDistance);
                }
            }
        }
    }
}
