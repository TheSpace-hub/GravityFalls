package hub.thespace.gravityfalls.executors;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

        updateWorld(world);
    }

    /**
     * Обновить мир и применить новую гравитацию ко всем существам.
     *
     * @param world Мир.
     */
    public void updateWorld(World world) {
        int gravity = plugin.getConfig().getConfigurationSection("worlds").getInt(world.getName());
        for (LivingEntity entity : world.getLivingEntities()) {
            entity.removePotionEffect(PotionEffectType.LEVITATION);
            entity.addPotionEffect(new PotionEffect(
                    PotionEffectType.LEVITATION,
                    1000000, -gravity, false, false));
        }
    }

}
