package dev.kugge.optikaii.util;

import dev.kugge.optikaii.Optikaii;

import org.bukkit.Bukkit;import org.bukkit.entity.Player;

public class DistanceManager {

    public static void setViewDistance(Optikaii plugin, Player player, int distance) {
        Optikaii.logger.info("Changed " + player.getName() + " view distance in "
                             + player.getWorld().getName() + ". "
                             + "(" + player.getViewDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> player.setViewDistance(distance));
    }

    public static void setSimulationDistance(Optikaii plugin, Player player, int distance) {
        Optikaii.logger.info("Changed " + player.getName() + " simulation distance in "
                             + player.getWorld().getName() + ". "
                             + "(" + player.getSimulationDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> player.setSimulationDistance(distance));
    }
}
