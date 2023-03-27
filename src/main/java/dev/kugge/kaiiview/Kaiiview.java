package dev.kugge.kaiiview;

import dev.kugge.kaiiview.command.ViewCommand;
import dev.kugge.kaiiview.config.CoreConfig;
import dev.kugge.kaiiview.watcher.AfkWatcher;
import dev.kugge.kaiiview.config.WorldConfig;
import dev.kugge.kaiiview.watcher.MsptWatcher;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.logging.Logger;
import static dev.kugge.kaiiview.util.DistanceManager.setStarterDistances;

public class Kaiiview extends JavaPlugin {


    private BukkitTask afkTaskId = null;
    private BukkitTask msptTaskId = null;
    public static CoreConfig coreConfig;
    public static Logger logger;
    public static HashMap<World, WorldConfig> worldConfig = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        logger = getLogger();
        load();
        registerCommand();
        new Metrics(this, 18071);
    }

    public void load() {
        saveConfig();
        reload();
        setStarterDistances(this, Bukkit.getWorlds());
    }

    public void reload() {
        reloadConfig();
        worldConfig.clear();
        loadPerWorldConfig();
        coreConfig = CoreConfig.fromConfig(getConfig());
        registerTasks();
    }

    private void loadPerWorldConfig() {
        for (World world: Bukkit.getWorlds())
            worldConfig.put(world, WorldConfig.fromConfig(getConfig(), world.getName()));
    }

    private void registerTasks() {
        if (afkTaskId != null) afkTaskId.cancel();
        if (msptTaskId != null) msptTaskId.cancel();
        afkTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AfkWatcher(this), 20L,
                                                                     coreConfig.afkCheckFrequency());
        msptTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MsptWatcher(this), 20L,
                                                                      coreConfig.msptCheckFrequency());
    }

    private void registerCommand(){
        getCommand("view").setExecutor(new ViewCommand(this));
        getCommand("view").setTabCompleter(ViewCommand.tabCompleter);
    }
}
