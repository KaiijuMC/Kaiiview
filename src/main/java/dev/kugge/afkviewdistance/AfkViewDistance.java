package dev.kugge.afkviewdistance;

import dev.kugge.afkviewdistance.limiter.AfkLimiter;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AfkViewDistance extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(
            this,
            new AfkLimiter(this),
            20L,
            20L * this.getConfig().getInt("check-frequency")
        );
        Metrics metrics = new Metrics(this, 17823);
        getLogger().info("Finished startup.");
    }
}
