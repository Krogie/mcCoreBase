import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    private final Main plugin;

    public UnbanCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Nutze /unban <Spieler>");
            return true;
        }

        String targetName = args[0];
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
        if (banList.isBanned(targetName)) {
            banList.pardon(targetName);
            plugin.sendPrefixedMessage(sender, ChatColor.GREEN + targetName + " wurde entbannt.");
        } else {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + targetName + " ist nicht gebannt.");
        }
        return true;
    }
}

