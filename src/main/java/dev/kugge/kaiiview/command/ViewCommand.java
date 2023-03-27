package dev.kugge.kaiiview.command;

import dev.kugge.kaiiview.Kaiiview;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ViewCommand implements CommandExecutor {

    public static final String PERMISSION_RELOAD = "kaiiview.command.reload";
    public static final String PERMISSION_DUMP = "kaiiview.command.dump";
    private final Kaiiview plugin;
    private final ReloadCommand reloadCommand;
    private final PlayerDumpCommand playerDumpCommand;
    private final WorldDumpCommand worldDumpCommand;

    public ViewCommand(Kaiiview plugin) {
        this.plugin = plugin;
        reloadCommand = new ReloadCommand(plugin);
        playerDumpCommand = new PlayerDumpCommand();
        worldDumpCommand = new WorldDumpCommand();
    }

    public static final TabCompleter tabCompleter = (sender,command,alias,args) -> {
        if (args.length == 0) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            List<String> possibleCompletions = new ArrayList<>(Arrays.asList("reload", "playerdump", "worlddump"));
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
            if (args[0].equalsIgnoreCase("playerdump")) {
                if (sender.hasPermission(PERMISSION_DUMP)) {
                    return playerDumpCommand.onCommand(sender, command, cl, args);
                } else {
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("worlddump")) {
                if (sender.hasPermission(PERMISSION_DUMP)) {
                    return worldDumpCommand.onCommand(sender, command, cl, args);
                } else {
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.YELLOW + "Kaiiview v" + plugin.getPluginMeta().getVersion());
        sender.sendMessage("");
        sender.sendMessage("/opti reload: Reload plugin");
        sender.sendMessage("/opti playerdump: Show player distances");
        sender.sendMessage("/opti worlddump: Show world distances");
        return true;
    }
}
