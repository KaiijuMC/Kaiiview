package dev.kugge.optikaii.command;

import dev.kugge.optikaii.Optikaii;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private Optikaii plugin;

    public ReloadCommand(Optikaii plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            plugin.reload();
            sender.sendMessage(ChatColor.YELLOW + "Optikaii reloaded.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Something went wrong reloading Optikaii, see the console for more.");
            e.printStackTrace();
        }
        return true;
    }
}