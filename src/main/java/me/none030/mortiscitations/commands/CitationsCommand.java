package me.none030.mortiscitations.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.none030.mortiscitations.methods.StartingCitations.StartCitations;
import static me.none030.mortiscitations.methods.StartingCitations.isInCooldown;

public class CitationsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player != null) {
                if (!isInCooldown.contains(player.getUniqueId())) {
                    StartCitations(player);
                    return true;
                } else {
                    System.out.println("Player is in cooldown");
                }
            }
        } else {
            if (!isInCooldown.contains(((Player) sender).getUniqueId())) {
                StartCitations((Player) sender);
                return true;
            } else {
                sender.sendMessage("§cThis command is in cooldown.");
            }
        }
        return false;
    }
}
