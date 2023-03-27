package dev.kugge.kaiiview.watcher;

import dev.kugge.kaiiview.Kaiiview;
import dev.kugge.kaiiview.config.AfkConfig;
import dev.kugge.kaiiview.util.PlayerAfkStat;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

import static dev.kugge.kaiiview.util.DistanceManager.setSimulationDistance;
import static dev.kugge.kaiiview.util.DistanceManager.setViewDistance;
import static dev.kugge.kaiiview.util.InfoGetter.getServerPlayers;

public class AfkWatcher implements Runnable {

    private final HashMap<Player, PlayerAfkStat> statHashMap = new HashMap<>();

    private final Kaiiview plugin;

    public AfkWatcher(Kaiiview plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run () {
        Collection<? extends Player> players = getServerPlayers(plugin);
        for (Player player : players) {
            PlayerAfkStat playerStat = statHashMap.get(player);
            AfkConfig afk = Kaiiview.worldConfig.get(player.getWorld()).afk();

            // Not in stats: Create player stats
            if (playerStat == null) {
                createStat(player);
                continue;
            }

            // Changed pos: refresh pos
            if (!playerStat.getLastLocation().equals(player.getLocation())) {
                refreshPos(player, playerStat, afk);
                continue;
            }

            // Same pos: set AFK if needed
            if (System.currentTimeMillis() - playerStat.getLastCheck() >= afk.duration() * 1000L && !playerStat.isAfk())
                setPlayerAfk(player, playerStat, afk);
        }
    }

    public void createStat(Player player) {
        statHashMap.put(player, PlayerAfkStat.create(player.getLocation()));
    }

    public void refreshPos(Player player, PlayerAfkStat playerStat, AfkConfig afk) {
        playerStat.setLastLocation(player.getLocation());
        playerStat.setLastCheck(System.currentTimeMillis());
        if (playerStat.isAfk()) {
            playerStat.setAfk(false);
            if (afk.view().enabled()) setViewDistance(plugin, player, player.getWorld().getViewDistance());
            if (afk.simulation().enabled()) setSimulationDistance(plugin, player, player.getWorld().getSimulationDistance());
        }
    }

    public void setPlayerAfk(Player player, PlayerAfkStat playerStat, AfkConfig afk) {
        playerStat.setAfk(true);
        if (afk.view().enabled()) setViewDistance(plugin, player, afk.view().distance());
        if (afk.simulation().enabled()) setSimulationDistance(plugin, player, afk.simulation().distance());
    }
}
