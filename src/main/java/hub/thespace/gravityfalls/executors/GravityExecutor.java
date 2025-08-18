package hub.thespace.gravityfalls.executors;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class GravityExecutor {

    private final Plugin plugin;

    public GravityExecutor(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Изменяет гравитацию в выбранном мире.
     *
     * @param world   Мир.
     * @param gravity Целое положительное число от 0 до 10.
     */
    public void changeGravity(int gravity, World world) {
        plugin.getLogger().info("Изменение гравитации на " + gravity + " // " + world);
    }

}
