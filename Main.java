import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private MOTDChanger motdChanger;

    @Override
    public void onEnable() {
        // Erstelle eine Instanz des MOTDChanger-Plugins
        motdChanger = new MOTDChanger();

        // Registriere das MOTDChanger-Plugin als Bukkit-Plugin
        getServer().getPluginManager().registerEvents(motdChanger, this);
        getCommand("motd").setExecutor(motdChanger);
    }

    @Override
    public void onDisable() {
        // Deaktiviere das MOTDChanger-Plugin
        motdChanger = null;
    }
}