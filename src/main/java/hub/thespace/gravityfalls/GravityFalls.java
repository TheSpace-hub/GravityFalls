package hub.thespace.gravityfalls;

import hub.thespace.gravityfalls.commands.Gravity;
import hub.thespace.gravityfalls.listeners.MainListenerHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GravityFalls extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginCommand("gravity").setExecutor(new Gravity(this));
        Bukkit.getPluginManager().registerEvents(new MainListenerHandler(this), this);
    }

}
