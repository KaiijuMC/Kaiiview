package dev.kugge.optikaii.util;

import dev.kugge.optikaii.Optikaii;

import dev.kugge.optikaii.config.WorldConfig;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

import static java.lang.Math.max;

public class DistanceManager {
    public static void setViewDistance(Optikaii plugin, Player player, int distance) {
        if (Optikaii.coreConfig.verbose())
            Optikaii.logger.info("Changed " + player.getName() + " view distance in "
                                 + player.getWorld().getName() + ". "
                                 + "(" + player.getViewDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> player.setViewDistance(distance));
    }

    public static void setSimulationDistance(Optikaii plugin, Player player, int distance) {
        if (Optikaii.coreConfig.verbose())
            Optikaii.logger.info("Changed " + player.getName() + " simulation distance in "
                                 + player.getWorld().getName() + ". "
                                 + "(" + player.getSimulationDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> player.setSimulationDistance(distance));
    }

    public static void setViewDistance(Optikaii plugin, World world, int distance) {
        if (Optikaii.coreConfig.verbose())
            Optikaii.logger.info("Changed view distance in " + world.getName() + ". "
                                 + "(" + world.getViewDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> world.setViewDistance(distance));
    }

    public static void setSimulationDistance(Optikaii plugin, World world, int distance) {
        if (Optikaii.coreConfig.verbose())
            Optikaii.logger.info("Changed simulation distance in " + world.getName() + ". "
                                 + "(" + world.getSimulationDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> world.setSimulationDistance(distance));
    }

    public static void setStarterDistances(Optikaii plugin, List<World> worlds) {
        Optikaii.logger.info("Setting starter distances.");
        for (World world : worlds) {
            WorldConfig config = Optikaii.worldConfig.get(world);
            setSimulationDistance(plugin, world, max(config.mspt().simulationDistance().min(), world.getSimulationDistance()));
            setViewDistance(plugin, world, max(config.mspt().viewDistance().min(), world.getViewDistance()));
        }
    }
}
