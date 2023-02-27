# AFK View Distance

## About
A Simple plugin that set a view/simulation distance to AFK players.

## Config
```yaml
# Configuration for AFK View Distance.
# Github: https://github.com/KaiijuMC/AFKViewDistance

# Please don't change this!
version: 1

# How many seconds between AFK checks
check-frequency: 10
# How many seconds before setting a player AFK
duration: 120

# Change simulation when a player is AFK
simulation-distance-enable: true
# AFK simulation distance value
simulation-distance: 10

# Change view when a player is AFK
view-distance-enable: false
# AFK view distance value
view-distance: 10

```

## Building

1. Clone AFKViewDistance and build
```bash
git clone https://github.com/froobynooby/ViewDistanceTweaks
cd ViewDistanceTweaks
./gradlew clean build
```

2. Find jar in `./build/libs`
