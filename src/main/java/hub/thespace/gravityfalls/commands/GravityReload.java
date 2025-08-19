package hub.thespace.gravityfalls.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class GravityReload implements CommandExecutor, TabExecutor {

    private Plugin plugin;

    public GravityReload(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("gravity.reload")) {
            commandSender.sendMessage(plugin.getConfig().getString("messages.no-perms-to-reload")
                    .replace("&", "§"));
            return true;
        }

        if (strings.length != 0) {
            commandSender.sendMessage(plugin.getConfig().getString("messages.use-reload")
                    .replace("&", "§"));
            return true;
        }

        plugin.reloadConfig();

        commandSender.sendMessage(plugin.getConfig().getString("messages.gravity-reloaded")
                .replace("&", "§"));
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return List.of();
    }
}
