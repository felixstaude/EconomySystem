package felixstaude.economysystem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import felixstaude.economysystem.events.JoinListener;
import felixstaude.economysystem.sql.economy.SQLEcoSettings;
import felixstaude.economysystem.sql.playtime.SQLPlaySettings;

import java.util.HashMap;
import java.util.UUID;

public final class EconomySystem extends JavaPlugin {

    public HashMap<UUID, Double> economyMap = new HashMap<UUID, Double>();

    @Override
    public void onEnable() {
        SQLEcoSettings.connect();
        SQLPlaySettings.connect();

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        SQLEcoSettings.disconnect();
        SQLPlaySettings.disconnect();
    }

}
