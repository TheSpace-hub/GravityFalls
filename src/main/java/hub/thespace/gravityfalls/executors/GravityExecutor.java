package hub.thespace.gravityfalls.executors;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class GravityExecutor implements Runnable {

    private final Plugin plugin;

    /**
     * Фикс бага майнкрафт версии 1.12.2.
     * При спавне сущности, она появляется в world.getLivingEntities позже события EntitySpawnEvent, поэтому
     * на неё не накладываться эффект парения.
     * Это обрабатывает сущность через 1 тик после её появления.
     */
    private final List<LivingEntity> entitiesAwaitingProcessing = new ArrayList<>();
    private final List<LivingEntity> entitiesReadyToBeProcessed = new ArrayList<>();

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
     * При спавне сущности, она появляется в world.getLivingEntities позже события EntitySpawnEvent, поэтому
     * на неё не накладываться эффект парения.
     * Это обрабатывает сущность через 1 тик после её появления.
     */
    @Override
    public void run() {
        for (LivingEntity entity : entitiesReadyToBeProcessed) {
            applyGravity(entity);
        }
        entitiesReadyToBeProcessed.clear();
        entitiesReadyToBeProcessed.addAll(entitiesAwaitingProcessing);
        entitiesAwaitingProcessing.clear();
    }

    /**
     * Обновить мир и применить новую гравитацию ко всем существам.
     *
     * @param world Мир.
     */
    public void updateWorld(World world) {
        entitiesAwaitingProcessing.addAll(world.getLivingEntities());
    }

    /**
     * Обновить отдельное существо.
     *
     * @param entity Существо.
     */
    public void updateEntity(LivingEntity entity) {
        entitiesAwaitingProcessing.add(entity);
        plugin.getLogger().info("EAP: " + entitiesAwaitingProcessing);
    }

    /**
     * Применяет гравитацию для сущности.
     *
     * @param entity Сущность.
     */
    private void applyGravity(LivingEntity entity) {
        World world = entity.getWorld();
        ConfigurationSection worlds = plugin.getConfig().getConfigurationSection("worlds");
        if (!worlds.contains(world.getName())) {
            removeGravity(entity);
            return;
        }

        int gravity = worlds.getInt(world.getName()) + 1;
        entity.removePotionEffect(PotionEffectType.LEVITATION);
        entity.removePotionEffect(PotionEffectType.JUMP);
        entity.addPotionEffect(new PotionEffect(
                PotionEffectType.JUMP,
                1000000, Math.max(0, 4 - gravity), false, false));
        entity.addPotionEffect(new PotionEffect(
                PotionEffectType.LEVITATION,
                1000000, -gravity, false, false));
    }

    /**
     * Убрать гравитацию у существ.
     *
     * @param entity Существо.
     */
    private void removeGravity(LivingEntity entity) {
        entity.removePotionEffect(PotionEffectType.LEVITATION);
        entity.removePotionEffect(PotionEffectType.JUMP);
    }

}
