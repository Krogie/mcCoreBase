import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MOTDChanger extends JavaPlugin implements Listener {

    private String motd = "Willkommen auf unserem Server!";

    @Override
    public void onEnable() {
        // Registriere den Event-Listener
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        // Setze die MOTD des Servers
        event.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
    }

    // Befehl zum Ändern der MOTD
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Bitte gib eine neue MOTD an.");
                return true;
            }

            StringBuilder builder = new StringBuilder();
            for (String arg : args) {
                builder.append(arg).append(" ");
            }

            // Setze die neue MOTD
            motd = builder.toString().trim();
            sender.sendMessage(ChatColor.GREEN + "Die MOTD wurde erfolgreich geändert.");
            return true;
        }
        return false;
    }
}
