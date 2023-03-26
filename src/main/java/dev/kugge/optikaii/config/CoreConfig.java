package dev.kugge.optikaii.config;

import org.bukkit.configuration.file.FileConfiguration;

public record CoreConfig(int afkCheckFrequency, int msptCheckFrequency, boolean verbose) {
    private static final String AFK_CHECK_FREQUENCY = "afk-check-frequency";
    private static final String MSPT_CHECK_FREQUENCY = "mspt-check-frequency";
    private static final String VERBOSE = "verbose";

    public static CoreConfig fromConfig(FileConfiguration section) {
        int afkCheckFrequency = section.getInt(AFK_CHECK_FREQUENCY);
        int msptCheckFrequency = section.getInt(MSPT_CHECK_FREQUENCY);
        boolean verbose = section.getBoolean(VERBOSE);
        return new CoreConfig(afkCheckFrequency, msptCheckFrequency, verbose);
    }
}
