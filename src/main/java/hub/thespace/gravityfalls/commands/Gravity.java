package hub.thespace.gravityfalls.commands;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.List;

public class Gravity implements CommandExecutor, TabExecutor {

    private final Plugin plugin;

    public Gravity(Plugin plugin) {
        this.plugin = plugin;
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

        plugin.getLogger().info("I: " + arguments.getLeft() + ", W: " + arguments.getRight());

//        Player player = (Player) commandSender;
//        player.addPotionEffect(new PotionEffect(
//                PotionEffectType.LEVITATION,
//                1000000,
//                Integer.parseInt(strings[0])
//        ));
//        plugin.getLogger().info("Set gravity level" + strings[0]);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        return List.of();
    }

    @Nullable
    private Pair<Integer, World> getArguments(CommandSender sender, String[] strings) {
        if (1 > strings.length || strings.length > 2) return null;
        if (sender instanceof ConsoleCommandSender && strings.length == 1) return null;

        World world;
        if (!strings[0].matches("-?\\d+")) return null;
        int value = Integer.parseInt(strings[0]);

        if (sender instanceof Player && strings.length == 1)
            world = ((Player) sender).getWorld();
        else
            world = Bukkit.getWorld(strings[1]);

        return Pair.of(value, world);
    }

}
