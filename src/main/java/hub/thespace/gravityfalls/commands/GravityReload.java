package hub.thespace.gravityfalls.commands;

import hub.thespace.gravityfalls.executors.GravityExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class GravityReload implements CommandExecutor, TabExecutor {

    private final Plugin plugin;
    private final GravityExecutor gravityExecutor;

    public GravityReload(Plugin plugin, GravityExecutor gravityExecutor) {
        this.plugin = plugin;
        this.gravityExecutor = gravityExecutor;
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
        gravityExecutor.updateAllWorlds();

        commandSender.sendMessage(plugin.getConfig().getString("messages.gravity-reloaded")
                .replace("&", "§"));
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return List.of();
    }
}
