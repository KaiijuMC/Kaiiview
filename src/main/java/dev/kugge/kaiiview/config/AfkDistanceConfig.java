package dev.kugge.kaiiview.config;

import org.bukkit.configuration.ConfigurationSection;

public record AfkDistanceConfig(boolean enabled, int distance) {

    private static final String APPLY = "apply";
    private static final String VALUE = "value";

    public static AfkDistanceConfig fromConfig(ConfigurationSection section, ConfigurationSection defaultSection) {
        if (section == null) section = defaultSection;
        boolean enabled = section.getBoolean(APPLY, defaultSection.getBoolean(APPLY));
        int distance = section.getInt(VALUE, defaultSection.getInt(VALUE));
        return new AfkDistanceConfig(enabled, distance);
    }
}
