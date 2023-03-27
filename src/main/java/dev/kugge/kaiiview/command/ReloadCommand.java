package dev.kugge.kaiiview.command;

import dev.kugge.kaiiview.Kaiiview;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private Kaiiview plugin;

    public ReloadCommand(Kaiiview plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            plugin.reload();
            sender.sendMessage(ChatColor.YELLOW + "Kaiiview reloaded.");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Something went wrong reloading Kaiiview, see the console for more.");
            e.printStackTrace();
        }
        return true;
    }
}