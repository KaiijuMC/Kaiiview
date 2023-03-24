package dev.kugge.optikaii;

import dev.kugge.optikaii.command.OptiCommand;import dev.kugge.optikaii.limiter.AfkWatcher;
import dev.kugge.optikaii.util.WorldConfig;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;import java.util.logging.Logger;

public class Optikaii extends JavaPlugin {

    private static String AFK_CHECK_FREQUENCY_KEY = "afk-check-frequency";
    private int afkTaskId = -1;

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
        loadPerWorldConfig();
        this.afkTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AfkWatcher(), 20L,
                                                                         this.getConfig().getInt(AFK_CHECK_FREQUENCY_KEY));
    }

    public void reload() {
        this.reloadConfig();
        worldConfig.clear();
        loadPerWorldConfig();
        if (this.afkTaskId != -1) Bukkit.getScheduler().cancelTask(this.afkTaskId);
        this.afkTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AfkWatcher(), 20L,
                                                                         this.getConfig().getInt(AFK_CHECK_FREQUENCY_KEY));
    }

    public void loadPerWorldConfig() {
        for (World world: Bukkit.getWorlds()) {
            String name = world.getName();
            worldConfig.put(name, WorldConfig.fromConfig(this.getConfig(), name));
        }
    }

    private void registerCommand(){
        this.getCommand("opti").setExecutor(new OptiCommand(this));
        this.getCommand("opti").setTabCompleter(OptiCommand.tabCompleter);
    }

}
