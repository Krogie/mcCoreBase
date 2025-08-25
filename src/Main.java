import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    private MOTDChanger motdChanger;
    private final Set<UUID> mutedPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        motdChanger = new MOTDChanger(this);
        getServer().getPluginManager().registerEvents(motdChanger, this);
        getCommand("motd").setExecutor(motdChanger);

        getCommand("gm").setExecutor(new GmCommand(this));
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getCommand("mute").setExecutor(new MuteCommand(this));
        getCommand("unmute").setExecutor(new UnmuteCommand(this));

        getServer().getPluginManager().registerEvents(this, this);
    }

    public String getPrefix() {
        String prefix = getConfig().getString("prefix", "[Server]");
        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    public void sendPrefixedMessage(CommandSender sender, String message) {
        sender.sendMessage(getPrefix() + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', message));
    }

    public void mute(UUID uuid) {
        mutedPlayers.add(uuid);
    }

    public void unmute(UUID uuid) {
        mutedPlayers.remove(uuid);
    }

    public boolean isMuted(UUID uuid) {
        return mutedPlayers.contains(uuid);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (isMuted(event.getPlayer().getUniqueId())) {
            sendPrefixedMessage(event.getPlayer(), ChatColor.RED + "Du bist stummgeschaltet.");
            event.setCancelled(true);
        }
    }
}

