import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BanCommand implements CommandExecutor {

    private final Main plugin;

    public BanCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            plugin.sendPrefixedMessage(sender, ChatColor.RED + "Nutze /ban <Spieler> [Grund]");
            return true;
        }

        String targetName = args[0];
        String reason = args.length > 1 ? String.join(" ", Arrays.copyOfRange(args, 1, args.length)) : "Du wurdest gebannt!";

        Bukkit.getBanList(BanList.Type.NAME).addBan(targetName, reason, null, sender.getName());
        Player target = Bukkit.getPlayerExact(targetName);
        if (target != null) {
            target.kickPlayer(reason);
        }

        plugin.sendPrefixedMessage(sender, ChatColor.GREEN + targetName + " wurde gebannt.");
        return true;
    }
}

