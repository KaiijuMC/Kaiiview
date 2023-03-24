package dev.kugge.optikaii.command;

import dev.kugge.optikaii.Optikaii;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OptiCommand implements CommandExecutor {

    public static final String PERMISSION_RELOAD = "optikaii.command.reload";
    private final Optikaii plugin;
    private final ReloadCommand reloadCommand;

    public OptiCommand(Optikaii plugin) {
        this.plugin = plugin;
        reloadCommand = new ReloadCommand(plugin);
    }

    public static final TabCompleter tabCompleter = (sender,command,alias,args) -> {
        if (args.length == 0) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            List<String> possibleCompletions = new ArrayList<>(Arrays.asList("reload"));
            return StringUtil.copyPartialMatches(args[0], possibleCompletions, new ArrayList<>());
        }
        return new ArrayList<>();
    };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cl, String[] args) {
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission(PERMISSION_RELOAD)) {
                    return reloadCommand.onCommand(sender, command, cl, args);
                } else {
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.YELLOW + "Optikaii v" + plugin.getPluginMeta().getVersion());
        sender.sendMessage("");
        sender.sendMessage("/opti reload");
        return true;
    }
}
