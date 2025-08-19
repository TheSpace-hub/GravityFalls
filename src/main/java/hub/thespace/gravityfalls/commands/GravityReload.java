package hub.thespace.gravityfalls.commands;

import hub.thespace.gravityfalls.executors.GravityExecutor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
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
        checkConfig();
        gravityExecutor.updateAllWorlds();

        commandSender.sendMessage(plugin.getConfig().getString("messages.gravity-reloaded")
                .replace("&", "§"));
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return List.of();
    }

    public void checkConfig() {
        ConfigurationSection worlds = plugin.getConfig().getConfigurationSection("worlds");
        for (World world : Bukkit.getWorlds())
            if (worlds.contains(world.getName()))
                checkWorldInConfig(world);
    }

    private void checkWorldInConfig(World world) {
        Object value = plugin.getConfig().getConfigurationSection("worlds").get(world.getName());
        if (!(value instanceof Integer)) {
            plugin.getConfig().getConfigurationSection("worlds").set(world.getName(), 0);
            plugin.getLogger().severe(plugin.getConfig().getString("messages.config-error")
                    .replace("&", "§")
                    .replace("{value}", value.toString())
                    .replace("{world}", world.getName()));
            plugin.saveConfig();
            return;
        }
        int gravity = (Integer) value;
        if (10 < gravity || gravity < 0) {
            plugin.getConfig().getConfigurationSection("worlds").set(world.getName(), 0);
            plugin.getLogger().severe(plugin.getConfig().getString("messages.config-error")
                    .replace("&", "§")
                    .replace("{value}", value.toString())
                    .replace("{world}", world.getName()));
            plugin.saveConfig();
        }


    }

}
