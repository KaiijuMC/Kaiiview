package dev.kugge.optikaii.util;

import dev.kugge.optikaii.Optikaii;
import dev.kugge.optikaii.command.OptiCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public record WorldConfig(boolean afkViewEnabled, boolean afkSimulationEnabled,
                          int afkViewDistance, int afkSimulationDistance,
                          int afkDuration) {

    private static String WORLD_KEY = "world";
    private static String AFK_KEY = "afk-settings";
    public static String AFK_DURATION_KEY = AFK_KEY + "." + "duration";
    private static String AFK_VIEW_DISTANCE_KEY = AFK_KEY + "." + "view-distance";
    private static String AFK_SIMULATION_DISTANCE_KEY = AFK_KEY + "." + "simulation-distance";
    public static String AFK_VIEW_DISTANCE_APPLY_KEY = AFK_VIEW_DISTANCE_KEY + "." + "apply";
    public static String AFK_VIEW_DISTANCE_VALUE_KEY = AFK_VIEW_DISTANCE_KEY + "." + "value";
    public static String AFK_SIMULATION_DISTANCE_APPLY_KEY = AFK_SIMULATION_DISTANCE_KEY + "." + "apply";
    public static String AFK_SIMULATION_DISTANCE_VALUE_KEY = AFK_SIMULATION_DISTANCE_KEY + "." + "value";

    public static WorldConfig fromConfig(FileConfiguration config, String worldName) {
        ConfigurationSection section = getSection(config, worldName);
        ConfigurationSection defaultSection = getSection(config, "default");
        Optikaii.logger.info("Loading " + worldName + " configuration. (" + section.getName() + ")");
        boolean afkViewEnabled = section.getBoolean(AFK_VIEW_DISTANCE_APPLY_KEY, defaultSection.getBoolean(AFK_VIEW_DISTANCE_APPLY_KEY));
        boolean afkSimulationEnabled = section.getBoolean(AFK_SIMULATION_DISTANCE_APPLY_KEY, defaultSection.getBoolean(AFK_SIMULATION_DISTANCE_APPLY_KEY));
        int afkViewDistance = section.getInt(AFK_VIEW_DISTANCE_VALUE_KEY, defaultSection.getInt(AFK_VIEW_DISTANCE_VALUE_KEY));
        int afkSimulationDistance = section.getInt(AFK_SIMULATION_DISTANCE_VALUE_KEY, defaultSection.getInt(AFK_SIMULATION_DISTANCE_VALUE_KEY));
        int afkDuration = section.getInt(AFK_DURATION_KEY, defaultSection.getInt(AFK_DURATION_KEY));
        return new WorldConfig(afkViewEnabled,
                               afkSimulationEnabled,
                               afkViewDistance,
                               afkSimulationDistance,
                               afkDuration);

    }

    private static ConfigurationSection getSection(FileConfiguration config, String worldName) {
        String key = getConfigKey(config, worldName);
        return config.getConfigurationSection(WORLD_KEY + "." + key);
    }

    private static String getConfigKey(FileConfiguration config, String worldName) {
        return config.contains(WORLD_KEY + "." + worldName) ? worldName : "default";
    }
}
