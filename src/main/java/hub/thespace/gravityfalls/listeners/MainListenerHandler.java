package hub.thespace.gravityfalls.listeners;

import hub.thespace.gravityfalls.executors.GravityExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.plugin.Plugin;

public class MainListenerHandler implements Listener {

    private Plugin plugin;
    private final GravityExecutor gravityExecutor;

    public MainListenerHandler(Plugin plugin, GravityExecutor gravityExecutor) {
        this.plugin = plugin;
        this.gravityExecutor = gravityExecutor;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        gravityExecutor.updateEntity(event.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        gravityExecutor.updateEntity(event.getPlayer());
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        gravityExecutor.updateEntity(event.getPlayer());
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        gravityExecutor.updateEntity(event.getPlayer());
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        gravityExecutor.updateEntity(event.getEntity());
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof LivingEntity)
            gravityExecutor.updateEntity((LivingEntity) event.getEntity());
    }

    @EventHandler
    public void onChunkPopulate(ChunkPopulateEvent event) {
        for (Entity entity : event.getChunk().getEntities()) {
            if (entity instanceof LivingEntity)
                gravityExecutor.updateEntity((LivingEntity) entity);
        }
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        if (event.getEntity() instanceof LivingEntity)
            gravityExecutor.updateEntity((LivingEntity) event.getEntity());
    }

}
