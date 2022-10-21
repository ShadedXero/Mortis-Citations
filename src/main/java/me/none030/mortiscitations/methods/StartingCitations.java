package me.none030.mortiscitations.methods;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

import static me.none030.mortiscitations.MortisCitations.plugin;

public class StartingCitations {

    public static List<UUID> isInCooldown = new ArrayList<>();

    public static void StartCitations(Player player) {

        File file = new File("plugins/MortisCitations", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection citation = config.getConfigurationSection("citation");

        assert citation != null;
        if (Objects.requireNonNull(citation.getString("execution")).equalsIgnoreCase("CHAT")) {
            ConfigurationSection section = citation.getConfigurationSection("citations");
            assert section != null;
            long delay = citation.getLong("delay");
            long delay2 = 0;
            int size = section.getKeys(false).size();
            int size2 = 0;
            isInCooldown.add(player.getUniqueId());
            for (String key : section.getKeys(false)) {
                size2 = size2 + 1;
                int finalSize = size2;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        List<String> citations = section.getStringList(key);
                        for (String credit : citations) {
                            player.sendMessage(credit);
                            if (size == finalSize) {
                                isInCooldown.remove(player.getUniqueId());
                            }
                        }
                    }
                }.runTaskLater(plugin, delay2);
                delay2 = delay2 + delay;
            }
        }
        if (Objects.requireNonNull(citation.getString("execution")).equalsIgnoreCase("TITLE")) {
            ConfigurationSection section = citation.getConfigurationSection("citations");
            assert section != null;
            long delay = citation.getLong("delay");
            long delay2 = 0;
            int size = section.getKeys(false).size();
            int size2 = 0;
            isInCooldown.add(player.getUniqueId());
            for (String key : section.getKeys(false)) {
                size2 = size2 + 1;
                int finalSize = size2;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        List<String> citations = section.getStringList(key);
                        player.sendTitle(citations.get(0), citations.get(1));
                        if (size == finalSize) {
                            isInCooldown.remove(player.getUniqueId());
                        }
                    }
                }.runTaskLater(plugin, delay2);
                delay2 = delay2 + delay;
            }
        }
    }
}
