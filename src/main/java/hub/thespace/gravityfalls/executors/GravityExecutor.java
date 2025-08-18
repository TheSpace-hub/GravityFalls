package hub.thespace.gravityfalls.executors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GravityExecutor implements Runnable {

    private final Plugin plugin;
    private final Map<Entity, Double> velocities = new HashMap<>();

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

    @Override
    public void run() {
        World world = Bukkit.getWorld("world");
        for (Entity entity : world.getEntities()) {
            if (!velocities.containsKey(entity)) {
                velocities.put(entity, entity.getVelocity().getY());
                return;
            }

            plugin.getLogger().info(entity.getType() + " // " + entity.getVelocity().toString());
            if (Objects.equals(entity.getVelocity(), new Vector()))
                return;
            double diff = entity.getVelocity().getY() - velocities.get(entity);
            entity.setVelocity(new Vector(
                    entity.getVelocity().getX(),
                    entity.getVelocity().getY(),
                    entity.getVelocity().getZ()
            ));
            velocities.put(entity, entity.getVelocity().getY());
        }

    }
}
