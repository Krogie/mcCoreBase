import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmCommand implements CommandExecutor {

    private final Main plugin;

    public GmCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Nur Spieler können diesen Befehl nutzen.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Nutze /gm <survival|creative|adventure|spectator>");
            return true;
        }

        GameMode mode;
        switch (args[0].toLowerCase()) {
            case "0":
            case "s":
            case "survival":
                mode = GameMode.SURVIVAL;
                break;
            case "1":
            case "c":
            case "creative":
                mode = GameMode.CREATIVE;
                break;
            case "2":
            case "a":
            case "adventure":
                mode = GameMode.ADVENTURE;
                break;
            case "3":
            case "sp":
            case "spectator":
                mode = GameMode.SPECTATOR;
                break;
            default:
                plugin.sendPrefixedMessage(sender, ChatColor.RED + "Unbekannter Spielmodus.");
                return true;
        }

        player.setGameMode(mode);
        plugin.sendPrefixedMessage(sender, ChatColor.GREEN + "Spielmodus gesetzt zu " + mode.name().toLowerCase());
        return true;
    }
}

