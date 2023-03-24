package dev.kugge.optikaii.util;

import org.bukkit.configuration.ConfigurationSection;

public record AfkConfig(boolean viewEnabled, boolean simulationEnabled,
                          int viewDistance, int simulationDistance,
                          int duration) {

    private static final String AFK = "afk-settings";
    private static final String AFK_DURATION = AFK + "." + "duration";
    private static final String AFK_VIEW_DISTANCE = AFK + "." + "view-distance";
    private static final String AFK_SIMULATION_DISTANCE = AFK + "." + "simulation-distance";
    private static final String AFK_VIEW_DISTANCE_APPLY = AFK_VIEW_DISTANCE + "." + "apply";
    private static final String AFK_VIEW_DISTANCE_VALUE = AFK_VIEW_DISTANCE + "." + "value";
    private static final String AFK_SIMULATION_DISTANCE_APPLY = AFK_SIMULATION_DISTANCE + "." + "apply";
    private static final String AFK_SIMULATION_DISTANCE_VALUE = AFK_SIMULATION_DISTANCE + "." + "value";

    public static AfkConfig fromConfig(ConfigurationSection section, ConfigurationSection defaultSection) {
        boolean afkViewEnabled = section.getBoolean(AFK_VIEW_DISTANCE_APPLY, defaultSection.getBoolean(AFK_VIEW_DISTANCE_APPLY));
        boolean afkSimulationEnabled = section.getBoolean(AFK_SIMULATION_DISTANCE_APPLY, defaultSection.getBoolean(AFK_SIMULATION_DISTANCE_APPLY));
        int afkViewDistance = section.getInt(AFK_VIEW_DISTANCE_VALUE, defaultSection.getInt(AFK_VIEW_DISTANCE_VALUE));
        int afkSimulationDistance = section.getInt(AFK_SIMULATION_DISTANCE_VALUE, defaultSection.getInt(AFK_SIMULATION_DISTANCE_VALUE));
        int afkDuration = section.getInt(AFK_DURATION, defaultSection.getInt(AFK_DURATION));
        return new AfkConfig(afkViewEnabled,
                               afkSimulationEnabled,
                               afkViewDistance,
                               afkSimulationDistance,
                               afkDuration);
    }
}
