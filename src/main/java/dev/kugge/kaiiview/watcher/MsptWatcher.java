package dev.kugge.kaiiview.watcher;

import dev.kugge.kaiiview.Kaiiview;
import dev.kugge.kaiiview.config.MsptConfig;import dev.kugge.kaiiview.util.WorldMsptStat;

import org.bukkit.World;
import java.util.HashMap;
import java.util.List;

import static dev.kugge.kaiiview.util.DistanceManager.setSimulationDistance;
import static dev.kugge.kaiiview.util.DistanceManager.setViewDistance;
import static dev.kugge.kaiiview.util.InfoGetter.getAverageTickTime;
import static dev.kugge.kaiiview.util.InfoGetter.getWorlds;

public class MsptWatcher implements Runnable {

    private final Kaiiview plugin;
    private static final HashMap<World, WorldMsptStat> statDecrease = new HashMap<>();
    private static final HashMap<World, WorldMsptStat> statIncrease = new HashMap<>();

    public MsptWatcher(Kaiiview plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run () {
        long averageTickTime = getAverageTickTime(plugin);
        List<World> worlds = getWorlds(plugin);
        for (World world : worlds) {
            initWorld(world);
            manage(world, averageTickTime);
        }
    }

    private void initWorld(World world) {
        if (statDecrease.get(world) == null) statDecrease.put(world, new WorldMsptStat());
        if (statIncrease.get(world) == null) statIncrease.put(world, new WorldMsptStat());
    }

    private void manage(World world, long averageTickTime) {
        MsptConfig config = Kaiiview.worldConfig.get(world).mspt();
        manageDecrease(world, config, averageTickTime);
        manageIncrease(world, config, averageTickTime);
    }

    private void manageDecrease(World world, MsptConfig config, long averageTickTime) {
        WorldMsptStat stat = statDecrease.get(world);
        if (averageTickTime > config.viewDistance().msptDecrease())
            stat.incrementView();
        if (averageTickTime > config.simulationDistance().msptDecrease())
            stat.incrementSimulation();
        if (stat.getChecksPassedSimulation() >= config.simulationDistance().checksDecrease()) {
            simulationDecrease(world, config);
            stat.resetSimulation();
        }
        if (stat.getChecksPassedView() >= config.viewDistance().checksDecrease()) {
            viewDecrease(world, config);
            stat.resetView();
        }
}

    private void manageIncrease(World world, MsptConfig config, long averageTickTime) {
        WorldMsptStat stat = statIncrease.get(world);
        if (averageTickTime < config.viewDistance().msptIncrease())
            stat.incrementView();
        if (averageTickTime < config.simulationDistance().msptIncrease())
            stat.incrementSimulation();
        if (stat.getChecksPassedSimulation() >= config.simulationDistance().checksIncrease()) {
            simulationIncrease(world, config);
            stat.resetSimulation();
        }
        if (stat.getChecksPassedView() >= config.viewDistance().checksIncrease()) {
            viewIncrease(world, config);
            stat.resetView();
        }
    }

    private void viewIncrease(World world, MsptConfig config) {
        if (world.getViewDistance() < config.viewDistance().max() && config.viewDistance().enabled())
            setViewDistance(plugin, world, world.getViewDistance() + 1);
    }

    private void simulationIncrease(World world, MsptConfig config) {
        if (world.getSimulationDistance() < config.simulationDistance().max() && config.simulationDistance().enabled())
            setSimulationDistance(plugin, world, world.getSimulationDistance() + 1);
    }

    private void viewDecrease(World world, MsptConfig config) {
        if (world.getViewDistance() > config.viewDistance().min() && config.viewDistance().enabled())
            setViewDistance(plugin, world, world.getViewDistance() - 1);
    }

    private void simulationDecrease(World world, MsptConfig config) {
        if (world.getSimulationDistance() > config.simulationDistance().min() && config.simulationDistance().enabled())
            setSimulationDistance(plugin, world, world.getSimulationDistance() - 1);
    }
}
