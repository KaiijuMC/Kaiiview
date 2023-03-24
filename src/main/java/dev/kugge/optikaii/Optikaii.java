package dev.kugge.optikaii;

import dev.kugge.optikaii.command.OptiCommand;
import dev.kugge.optikaii.watcher.AfkWatcher;
import dev.kugge.optikaii.config.WorldConfig;

import dev.kugge.optikaii.watcher.MsptWatcher;import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.logging.Logger;

public class Optikaii extends JavaPlugin {

    private static final String AFK_CHECK_FREQUENCY = "afk-check-frequency";
    private static final String MSPT_CHECK_FREQUENCY = "mspt-check-frequency";
    private BukkitTask afkTaskId = null;
    private BukkitTask msptTaskId = null;
    public static Logger logger;
    public static HashMap<String, WorldConfig> worldConfig = new HashMap<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        logger = this.getLogger();
        this.load();
        registerCommand();
        new Metrics(this, 18043);
    }

    public void load() {
        this.saveConfig();
        reload();
    }

    public void reload() {
        this.reloadConfig();
        worldConfig.clear();
        loadPerWorldConfig();
        registerTasks();
    }

    private void loadPerWorldConfig() {
        for (World world: Bukkit.getWorlds()) {
            String name = world.getName();
            worldConfig.put(name, WorldConfig.fromConfig(this.getConfig(), name));
        }
    }

    private void registerTasks() {
        if (this.afkTaskId != null) this.afkTaskId.cancel();
        if (this.msptTaskId != null) this.msptTaskId.cancel();
        this.afkTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AfkWatcher(this), 20L,
                                                                          this.getConfig().getInt(AFK_CHECK_FREQUENCY));
        this.msptTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MsptWatcher(this), 20L,
                                                                           this.getConfig().getInt(MSPT_CHECK_FREQUENCY));
    }

    private void registerCommand(){
        this.getCommand("opti").setExecutor(new OptiCommand(this));
        this.getCommand("opti").setTabCompleter(OptiCommand.tabCompleter);
    }
}
