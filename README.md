# Kaiiview

## About
Dynamic view and simulation distances based on MSPT.

## Config
```yaml
# Configuration for Kaiiview.
# Github: https://github.com/KaiijuMC/Kaiiview

# Please don't change this!
version: 1

# How many ticks between MSPT checks
mspt-check-frequency: 200

# How many ticks between AFK checks
afk-check-frequency: 20

# Per world configuration
world:
  default:
    # MSPT dynamic distances
    mspt-settings:
      # Simulation distance management
      simulation-distance:
        # Should we manage simulation distance ?
        apply: true
        # How many checks passed before increasing simulation distance
        checks-increase: 10
        # How many checks passed before decreasing simulation distance
        checks-decrease: 1
        # Below this value we'll increase simulation distance
        mspt-increase: 40
        # Above this value we'll decrease simulation distance
        mspt-decrease: 45
        # Minimum simulation distance (YOU MIGHT WANT TO CHANGE THIS)
        min-value: 8
        # Maximum simulation distance (YOU MIGHT WANT TO CHANGE THIS)
        max-value: 12
      # View distance management
      view-distance:
        # Should we manage view distance ?
        apply: true
        # How many checks passed before increasing view distance
        checks-increase: 10
        # How many checks passed before decreasing view distance
        checks-decrease: 1
        # Below this value we'll increase view distance
        mspt-increase: 40
        # Above this value we'll decrease view distance
        mspt-decrease: 45
        # Minimum view distance (YOU MIGHT WANT TO CHANGE THIS)
        min-value: 10
        # Maximum view distance (YOU MIGHT WANT TO CHANGE THIS)
        max-value: 16
    # AFK dynamic distances
    afk-settings:
      # Ticks before setting an user AFK
      duration: 120
      # Simulation distance management
      simulation-distance:
        # Should we manage simulation distance ?
        apply: true
        # AFK simulation distance (YOU MIGHT WANT TO CHANGE THIS)
        value: 8
      view-distance:
        # Should we manage view distance ?
        apply: true
        # AFK view distance (YOU MIGHT WANT TO CHANGE THIS)
        value: 4
```

## Building

1. Clone Kaiiview and build
```bash
git clone https://github.com/KaiijuMC/Kaiiview.git
cd Kaiiview
./gradlew clean build
```

2. Find jar in `./build/libs`
