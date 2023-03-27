package dev.kugge.kaiiview.config;

import dev.kugge.kaiiview.Kaiiview;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public record WorldConfig(AfkConfig afk, MsptConfig mspt) {
    private static final String WORLD = "world";
    private static final String DEFAULT = "default";
    private static final String AFK = "afk-settings";
    private static final String MSPT = "mspt-settings";

    public static WorldConfig fromConfig(FileConfiguration config, String worldName) {
        ConfigurationSection section = getSection(config, worldName);
        ConfigurationSection defaultSection = getSection(config, DEFAULT);
        Kaiiview.logger.info("Loading " + worldName + " configuration. (" + section.getName() + ")");
        return new WorldConfig(AfkConfig.fromConfig(section.getConfigurationSection(AFK),
                                                    defaultSection.getConfigurationSection(AFK)),
                               MsptConfig.fromConfig(section.getConfigurationSection(MSPT),
                                                     defaultSection.getConfigurationSection(MSPT)));
    }

    private static ConfigurationSection getSection(FileConfiguration config, String worldName) {
        String key = getConfigKey(config, worldName);
        return config.getConfigurationSection(WORLD + "." + key);
    }

    private static String getConfigKey(FileConfiguration config, String worldName) {
        return config.contains(WORLD + "." + worldName) ? worldName : DEFAULT;
    }
}
