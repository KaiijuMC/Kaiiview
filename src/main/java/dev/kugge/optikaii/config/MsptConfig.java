package dev.kugge.optikaii.config;

import org.bukkit.configuration.ConfigurationSection;

public record MsptConfig(String mode, int meanDuration, MsptDistanceConfig viewDistance,
                         MsptDistanceConfig simulationDistance) {

    private static final String MODE = "mode";
    private static final String SIMULATION = "simulation-distance";
    private static final String VIEW = "view-distance";
    private static final String MEAN_DURATION = "mean-duration";

    public static MsptConfig fromConfig(ConfigurationSection section, ConfigurationSection defaultSection) {
        if (section == null) section = defaultSection;
        String mode = section.getString(MODE, defaultSection.getString(MODE));
        int meanDuration = section.getInt(MEAN_DURATION, defaultSection.getInt(MEAN_DURATION));
        return new MsptConfig(mode, meanDuration,
                              MsptDistanceConfig.fromConfig(section.getConfigurationSection(VIEW),
                                                            defaultSection.getConfigurationSection(VIEW)),
                              MsptDistanceConfig.fromConfig(section.getConfigurationSection(SIMULATION),
                                                            defaultSection.getConfigurationSection(SIMULATION)));
    }
}