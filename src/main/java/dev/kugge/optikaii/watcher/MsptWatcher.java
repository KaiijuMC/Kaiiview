package dev.kugge.optikaii.watcher;

import dev.kugge.optikaii.Optikaii;
import org.bukkit.Bukkit;


public class MsptWatcher implements Runnable {

    private final Optikaii plugin;

    public MsptWatcher(Optikaii plugin) {
        this.plugin = plugin;
    }

    // TODO: Implement mspt auto calibration
    @Override
    public void run () {
        long[] tickTimes = Bukkit.getTickTimes();

        long totalTickTime = 0;
        for(long tickTime: tickTimes) totalTickTime += tickTime;

        long averageTickTime = totalTickTime / tickTimes.length / 1000000;
        Optikaii.logger.info("average: " + averageTickTime + " MSPT");
    }
}
