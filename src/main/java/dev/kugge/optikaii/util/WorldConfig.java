package dev.kugge.optikaii.util;

import dev.kugge.optikaii.Optikaii;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public record WorldConfig(AfkConfig afk) {

    private static final String WORLD = "world";
    private static final String DEFAULT = "default";

    public static WorldConfig fromConfig(FileConfiguration config, String worldName) {
        ConfigurationSection section = getSection(config, worldName);
        ConfigurationSection defaultSection = getSection(config, DEFAULT);
        Optikaii.logger.info("Loading " + worldName + " configuration. (" + section.getName() + ")");
        return new WorldConfig(AfkConfig.fromConfig(section, defaultSection));
    }

    private static ConfigurationSection getSection(FileConfiguration config, String worldName) {
        String key = getConfigKey(config, worldName);
        return config.getConfigurationSection(WORLD + "." + key);
    }

    private static String getConfigKey(FileConfiguration config, String worldName) {
        return config.contains(WORLD + "." + worldName) ? worldName : DEFAULT;
    }
}
