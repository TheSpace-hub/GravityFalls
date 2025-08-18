package hub.thespace.gravityfalls.executors;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class GravityExecutor {

    private final Plugin plugin;

    public GravityExecutor(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Изменяет гравитацию в выбранном мире.
     *
     * @param world Мир.
     * @param value Целое положительное число от 0 до 10.
     */
    public void changeGravity(int value, World world) {
        ConfigurationSection worlds = plugin.getConfig().getConfigurationSection("worlds");
        worlds.set(world.getName(), value);
        plugin.saveConfig();
    }

}
