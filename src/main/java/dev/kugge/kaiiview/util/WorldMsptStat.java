package dev.kugge.kaiiview.util;

public class WorldMsptStat {
    private int checksPassedSimulation;
    private int checksPassedView;

    public WorldMsptStat() {
        checksPassedView = 0;
        checksPassedSimulation = 0;
    }

    public int getChecksPassedView() {
        return checksPassedView;
    }

    public int getChecksPassedSimulation() {
        return checksPassedSimulation;
    }

    public void incrementView() {
        checksPassedView++;
    }

    public void incrementSimulation() {
        checksPassedSimulation++;
    }

    public void resetView() {
        checksPassedView = 0;
    }
    
    public void resetSimulation() {
        checksPassedSimulation = 0;
    }
}