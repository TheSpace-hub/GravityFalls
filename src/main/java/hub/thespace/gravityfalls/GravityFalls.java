package hub.thespace.gravityfalls;

import hub.thespace.gravityfalls.commands.Gravity;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GravityFalls extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("gravity").setExecutor(new Gravity(this));
    }

}
