package dev.kugge.kaiiview.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayerDumpCommand implements CommandExecutor {

    private static final String format = ChatColor.GRAY + "["
                                       + ChatColor.LIGHT_PURPLE + "%02d"
                                       + ChatColor.GRAY + "/"
                                       + ChatColor.LIGHT_PURPLE + "%02d"
                                       + ChatColor.GRAY + "/"
                                       + ChatColor.LIGHT_PURPLE + "%02d"
                                       + ChatColor.GRAY + " | "
                                       + ChatColor.LIGHT_PURPLE + "%02d"
                                       + ChatColor.GRAY + "/"
                                       + ChatColor.LIGHT_PURPLE + "%02d"
                                       + ChatColor.GRAY + "]"
                                       + ChatColor.AQUA + " %s";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "Kaiiview" + ChatColor.GRAY + "] PLAYERS");
        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "View: Current/Client/Max" + ChatColor.GRAY + " | "
                         + ChatColor.LIGHT_PURPLE + "Simulation: Current/Max" + ChatColor.GRAY + "]" + ChatColor.AQUA + " name");
        Bukkit.getOnlinePlayers().forEach(player ->
                                          sender.sendMessage(String.format(format,
                                                                           player.getSendViewDistance() - 1,
                                                                           player.getClientViewDistance(),
                                                                           player.getWorld().getViewDistance(),
                                                                           player.getSimulationDistance(),
                                                                           player.getWorld().getSimulationDistance(),
                                                                           player.getName())));
        return true;
    }
}