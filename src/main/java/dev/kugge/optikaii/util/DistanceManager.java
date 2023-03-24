package dev.kugge.optikaii.util;

import dev.kugge.optikaii.Optikaii;

import org.bukkit.entity.Player;

public class DistanceManager {

    public static void setViewDistance(Player player, int distance) {
        Optikaii.logger.info("Changed " + player.getName() + " view distance in "
                             + player.getWorld().getName() + ". "
                             + "(" + player.getViewDistance() + " -> " + distance + ")");
        player.setViewDistance(distance);
    }

    public static void setSimulationDistance(Player player, int distance) {
        Optikaii.logger.info("Changed " + player.getName() + " simulation distance in "
                             + player.getWorld().getName() + ". "
                             + "(" + player.getSimulationDistance() + " -> " + distance + ")");
        player.setSimulationDistance(distance);
    }
}
