package hub.thespace.gravityfalls.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.util.List;

public class GravityShow implements CommandExecutor, TabCompleter {

    private final Plugin plugin;

    public GravityShow(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("gravity.show")) {
            commandSender.sendMessage(plugin.getConfig().getString("messages.no-perms-to-show")
                    .replace("&", "§"));
            return true;
        }

        if (strings.length != 0) {
            commandSender.sendMessage(plugin.getConfig().getString("messages.use-show")
                    .replace("&", "§"));
            return true;
        }

        commandSender.sendMessage(showWorlds());

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return List.of();
    }

    /**
     * Получение сообщения со всеми мирами.
     *
     * @return Строка для отправки.
     */
    private String showWorlds() {
        ConfigurationSection worlds = plugin.getConfig().getConfigurationSection("worlds");
        StringBuilder message = new StringBuilder("Настройки Миров: \n");
        for (String world : worlds.getKeys(false)) {
            int gravity = worlds.getInt(world);
            message.append(" ").append(world).append(" - ").append(gravity).append('\n');
        }

        return message.toString();
    }
}
