package dev.kugge.kaiiview.util;

import dev.kugge.kaiiview.Kaiiview;

import dev.kugge.kaiiview.config.WorldConfig;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

import static java.lang.Math.max;

public class DistanceManager {
    public static void setViewDistance(Kaiiview plugin, Player player, int distance) {
        if (Kaiiview.coreConfig.verbose())
            Kaiiview.logger.info("Changed " + player.getName() + " view distance in "
                                 + player.getWorld().getName() + ". "
                                 + "(" + player.getViewDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> player.setViewDistance(distance));
    }

    public static void setSimulationDistance(Kaiiview plugin, Player player, int distance) {
        if (Kaiiview.coreConfig.verbose())
            Kaiiview.logger.info("Changed " + player.getName() + " simulation distance in "
                                 + player.getWorld().getName() + ". "
                                 + "(" + player.getSimulationDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> player.setSimulationDistance(distance));
    }

    public static void setViewDistance(Kaiiview plugin, World world, int distance) {
        if (Kaiiview.coreConfig.verbose())
            Kaiiview.logger.info("Changed view distance in " + world.getName() + ". "
                                 + "(" + world.getViewDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> world.setViewDistance(distance));
    }

    public static void setSimulationDistance(Kaiiview plugin, World world, int distance) {
        if (Kaiiview.coreConfig.verbose())
            Kaiiview.logger.info("Changed simulation distance in " + world.getName() + ". "
                                 + "(" + world.getSimulationDistance() + " -> " + distance + ")");
        Bukkit.getScheduler().runTask(plugin, () -> world.setSimulationDistance(distance));
    }

    public static void setStarterDistances(Kaiiview plugin, List<World> worlds) {
        Kaiiview.logger.info("Setting starter distances.");
        for (World world : worlds) {
            WorldConfig config = Kaiiview.worldConfig.get(world);
            setSimulationDistance(plugin, world, max(config.mspt().simulationDistance().min(), world.getSimulationDistance()));
            setViewDistance(plugin, world, max(config.mspt().viewDistance().min(), world.getViewDistance()));
        }
    }
}
