package dev.kugge.kaiiview.util;

import org.bukkit.Location;

public class PlayerAfkStat {
    private Location lastLocation;
    private Boolean isAfk;
    private Long lastCheck;

    public PlayerAfkStat(Location lastLocation, Boolean isAfk, Long lastCheck) {
        this.lastLocation = lastLocation;
        this.isAfk = isAfk;
        this.lastCheck = lastCheck;
    }

    public static PlayerAfkStat create(Location location) {
        return new PlayerAfkStat(location, false, System.currentTimeMillis());
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public Boolean isAfk() {
        return isAfk;
    }

    public void setAfk(Boolean isAfk) {
        this.isAfk = isAfk;
    }

    public Long getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Long lastCheck) {
        this.lastCheck = lastCheck;
    }
}