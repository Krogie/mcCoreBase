import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTDChanger implements Listener, CommandExecutor {

    private final Main plugin;
    private String motd;

    public MOTDChanger(Main plugin) {
        this.plugin = plugin;
        this.motd = plugin.getConfig().getString("motd", "Willkommen auf unserem Server!");
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Bitte gib eine neue MOTD an.");
            return true;
        }

        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg).append(" ");
        }

        motd = builder.toString().trim();
        plugin.getConfig().set("motd", motd);
        plugin.saveConfig();
        plugin.sendPrefixedMessage(sender, ChatColor.GREEN + "Die MOTD wurde erfolgreich geändert.");
        return true;
    }
}

