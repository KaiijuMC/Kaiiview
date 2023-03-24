# Optikaii

## About
A Simple plugin that set a view/simulation distance to AFK players.

## Config
```yaml
# Configuration for Optikaii.
# Github: https://github.com/KaiijuMC/Optikaii

# Please don't change this!
version: 1

# How many ticks between AFK checks
afk-check-frequency: 20

# Per world configuration
world:
  default:
    # AFK settings
    afk-settings:
      # How many seconds before setting a player AFK
      duration: 120
      # Simulation distance settings
      simulation-distance:
        apply: true
        value: 10
      # View distance settings
      view-distance:
        apply: false
        value: 10
```

## Building

1. Clone Optikaii and build
```bash
git clone https://github.com/KaiijuMC/Optikaii.git
cd Optikaii
./gradlew clean build
```

2. Find jar in `./build/libs`
