import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnmuteCommand implements CommandExecutor {

    private final Main plugin;

    public UnmuteCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Nutze /unmute <Spieler>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Spieler nicht gefunden.");
            return true;
        }

        plugin.unmute(target.getUniqueId());
        plugin.sendPrefixedMessage(sender, ChatColor.GREEN + target.getName() + " darf wieder sprechen.");
        plugin.sendPrefixedMessage(target, ChatColor.GREEN + "Du darfst wieder sprechen.");
        return true;
    }
}

