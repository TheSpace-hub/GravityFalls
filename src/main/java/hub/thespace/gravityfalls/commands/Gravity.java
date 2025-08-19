package hub.thespace.gravityfalls.commands;

import hub.thespace.gravityfalls.executors.GravityExecutor;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gravity implements CommandExecutor, TabExecutor {

    private final Plugin plugin;
    private final GravityExecutor gravityExecutor;

    public Gravity(Plugin plugin) {
        this.plugin = plugin;
        gravityExecutor = new GravityExecutor(plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("gravity.change")) {
            commandSender.sendMessage(plugin.getConfig().getString("no-perms").replace("&", "§"));
            return true;
        }

        Pair<Integer, World> arguments = getArguments(commandSender, strings);
        if (arguments == null) {
            commandSender.sendMessage(plugin.getConfig().getString("use").replace("&", "§"));
            return true;
        }

        gravityExecutor.changeGravity(arguments.getLeft(), arguments.getRight());

        commandSender.sendMessage(plugin.getConfig().getString("gravity-changed")
                .replace("{world}", arguments.getRight().getName())
                .replace("{new_value}", arguments.getLeft().toString())
                .replace("&", "§"));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("gravity.change"))
            switch (strings.length) {
                case 1: return List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
                case 2: return Bukkit.getWorlds().stream()
                        .map(world -> world.getName())
                        .collect(Collectors.toList());
            }
        return List.of();
    }

    /**
     * Конвертирует аргументы в значения int и World.
     *
     * @param sender  Исполнитель команды.
     * @param strings Переданные аргументы.
     * @return Пара из гравитации и мира.
     */
    @Nullable
    private Pair<Integer, World> getArguments(CommandSender sender, String[] strings) {
        if (1 > strings.length || strings.length > 2) return null;
        if (sender instanceof ConsoleCommandSender && strings.length == 1) return null;

        World world;
        if (!strings[0].matches("\\d+")) return null;
        int value = Integer.parseInt(strings[0]);
        if (value > 10) return null;

        if (sender instanceof Player && strings.length == 1)
            world = ((Player) sender).getWorld();
        else {
            world = Bukkit.getWorld(strings[1]);
            if (world == null) return null;
        }

        return Pair.of(value, world);
    }

}
