package hub.thespace.gravityfalls.commands;

import hub.thespace.gravityfalls.executors.GravityExecutor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Gravity implements CommandExecutor, TabExecutor {

    private final Plugin plugin;
    private final GravityExecutor gravityExecutor;

    public Gravity(Plugin plugin, GravityExecutor gravityExecutor) {
        this.plugin = plugin;
        this.gravityExecutor = gravityExecutor;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("gravity.change")) {
            commandSender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.no-perms-to-change"))
                    .replace("&", "§"));
            return true;
        }


        if (getArguments(commandSender, strings) == null) {
            commandSender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.use-change"))
                    .replace("&", "§"));
            return true;
        }
        int gravity = (int) Objects.requireNonNull(getArguments(commandSender, strings)).get(0);
        World world = (World) Objects.requireNonNull(getArguments(commandSender, strings)).get(1);

        gravityExecutor.changeGravity(gravity, world);

        commandSender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.gravity-changed"))
                .replace("{world}", world.getName())
                .replace("{new_value}", gravity + "")
                .replace("&", "§"));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("gravity.change"))
            switch (strings.length) {
                case 1:
                    return List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
                case 2:
                    return Bukkit.getWorlds().stream()
                            .map(World::getName)
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
    private List<Object> getArguments(CommandSender sender, String[] strings) {
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

        return List.of(value, world);
    }

}
