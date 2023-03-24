package dev.kugge.optikaii.config;

import org.bukkit.configuration.ConfigurationSection;

public record AfkConfig(int duration, AfkDistanceConfig view, AfkDistanceConfig simulation) {

    private static final String DURATION = "duration";
    private static final String VIEW = "view-distance";
    private static final String SIMULATION = "simulation-distance";

    public static AfkConfig fromConfig(ConfigurationSection section, ConfigurationSection defaultSection) {
        if (section == null) section = defaultSection;
        int duration = section.getInt(DURATION, defaultSection.getInt(DURATION));
        return new AfkConfig(duration,
                             AfkDistanceConfig.fromConfig(section.getConfigurationSection(VIEW),
                                                          defaultSection.getConfigurationSection(VIEW)),
                             AfkDistanceConfig.fromConfig(section.getConfigurationSection(SIMULATION),
                                                          defaultSection.getConfigurationSection(SIMULATION)));
    }
}
