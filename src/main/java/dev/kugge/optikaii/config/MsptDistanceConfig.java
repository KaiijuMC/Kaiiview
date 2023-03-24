package dev.kugge.optikaii.config;

import org.bukkit.configuration.ConfigurationSection;

public record MsptDistanceConfig(boolean enabled, int checksIncrease, int checksDecrease,
                             int msptIncrease, int msptDecrease, int min, int max) {
    private static final String APPLY = "apply";
    private static final String CHECKS_INCREASE = "checks-increase";
    private static final String CHECKS_DECREASE = "checks-decrease";
    private static final String MSPT_INCREASE = "mspt-increase";
    private static final String MSPT_DECREASE = "mspt-decrease";
    private static final String MIN = "min-value";
    private static final String MAX = "max-value";

    public static MsptDistanceConfig fromConfig(ConfigurationSection section, ConfigurationSection defaultSection) {
        if (section == null) section = defaultSection;
        boolean enabled = section.getBoolean(APPLY, defaultSection.getBoolean(APPLY));
        int checksIncrease = section.getInt(CHECKS_INCREASE, defaultSection.getInt(CHECKS_INCREASE));
        int checksDecrease = section.getInt(CHECKS_DECREASE, defaultSection.getInt(CHECKS_DECREASE));
        int msptIncrease = section.getInt(MSPT_INCREASE, defaultSection.getInt(MSPT_INCREASE));
        int msptDecrease = section.getInt(MSPT_DECREASE, defaultSection.getInt(MSPT_DECREASE));
        int minValue = section.getInt(MIN, defaultSection.getInt(MIN));
        int maxValue = section.getInt(MAX, defaultSection.getInt(MAX));
        return new MsptDistanceConfig(enabled, checksIncrease, checksDecrease,
                                  msptIncrease, msptDecrease, minValue, maxValue);
    }
}
