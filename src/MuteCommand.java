import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

    private final Main plugin;

    public MuteCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Nutze /mute <Spieler>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Spieler nicht gefunden.");
            return true;
        }

        plugin.mute(target.getUniqueId());
        plugin.sendPrefixedMessage(sender, ChatColor.GREEN + target.getName() + " wurde stummgeschaltet.");
        plugin.sendPrefixedMessage(target, ChatColor.RED + "Du wurdest stummgeschaltet.");
        return true;
    }
}

