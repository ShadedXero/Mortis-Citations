package me.none030.mortiscitations;

import me.none030.mortiscitations.commands.CitationsCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MortisCitations extends JavaPlugin {

    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginCommand("citations").setExecutor(new CitationsCommand());
        plugin.saveResource("config.yml", false);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
