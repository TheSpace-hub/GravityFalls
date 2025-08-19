package hub.thespace.gravityfalls;

import hub.thespace.gravityfalls.commands.Gravity;
import hub.thespace.gravityfalls.executors.GravityExecutor;
import hub.thespace.gravityfalls.listeners.MainListenerHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GravityFalls extends JavaPlugin {

    @Override
    public void onEnable() {
        GravityExecutor gravityExecutor = new GravityExecutor(this);
        saveDefaultConfig();

        Bukkit.getPluginCommand("gravity").setExecutor(new Gravity(this, gravityExecutor));
        Bukkit.getPluginManager().registerEvents(new MainListenerHandler(this, gravityExecutor), this);

        Bukkit.getScheduler().runTaskTimer(this, gravityExecutor, 1, 1);
    }

}
