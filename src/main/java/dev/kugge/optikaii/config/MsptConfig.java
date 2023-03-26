package dev.kugge.optikaii.config;

import org.bukkit.configuration.ConfigurationSection;

public record MsptConfig (MsptDistanceConfig viewDistance,
                          MsptDistanceConfig simulationDistance) {

    private static final String SIMULATION = "simulation-distance";
    private static final String VIEW = "view-distance";

    public static MsptConfig fromConfig(ConfigurationSection section, ConfigurationSection defaultSection) {
        if (section == null) section = defaultSection;
        return new MsptConfig(MsptDistanceConfig.fromConfig(section.getConfigurationSection(VIEW),
                                                            defaultSection.getConfigurationSection(VIEW)),
                              MsptDistanceConfig.fromConfig(section.getConfigurationSection(SIMULATION),
                                                            defaultSection.getConfigurationSection(SIMULATION)));
    }
}