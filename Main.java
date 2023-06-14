import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private MOTDChanger motdChanger;

    @Override
    public void onEnable() {
        // Erstelle eine Instanz des MOTDChangers
        motdChanger = new MOTDChanger();

        // Registriere den MOTDChanger als Bukkit-Plugin
        getServer().getPluginManager().registerEvents(motdChanger, this);
        getCommand("motd").setExecutor(motdChanger);
    }

    @Override
    public void onDisable() {
        // Deaktiviere den MOTDChanger
        motdChanger = null;
    }
}
