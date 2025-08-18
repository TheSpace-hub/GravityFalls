package hub.thespace.gravityfalls.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Gravity implements CommandExecutor {

    private final Plugin plugin;

    public Gravity(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.LEVITATION,
                1000000,
                Integer.parseInt(strings[0])
        ));
        plugin.getLogger().info("Set gravity level" + strings[0]);
        return false;
    }


}
