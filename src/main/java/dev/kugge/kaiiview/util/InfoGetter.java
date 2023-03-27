package dev.kugge.kaiiview.util;

import dev.kugge.kaiiview.Kaiiview;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class InfoGetter {
    public static List<World> getWorlds(Kaiiview plugin) {
        try {
            return Bukkit.getScheduler().callSyncMethod(plugin, Bukkit::getWorlds).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getAverageTickTime(Kaiiview plugin) {
        long[] tickTimes;
        try {
            tickTimes = Bukkit.getScheduler().callSyncMethod(plugin, Bukkit::getTickTimes).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long totalTickTime = 0;
        for(long tickTime : tickTimes) totalTickTime += tickTime;
        return totalTickTime / tickTimes.length / 1000000;
    }

    public static Collection<? extends Player> getServerPlayers(Kaiiview plugin) {
        try {
            return Bukkit.getScheduler().callSyncMethod(plugin, Bukkit::getOnlinePlayers).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
