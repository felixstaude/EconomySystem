package felixstaude.economysystem.events;

import felixstaude.economysystem.sql.economy.SQLEcoMoney;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import felixstaude.economysystem.main.Economy;
import theepicflexo.economysystem.main.EconomySystem;

public class JoinListener implements Listener {
    private static EconomySystem economySystem;
    private static Economy economy;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!SQLEcoMoney.isUserExists(event.getPlayer().getUniqueId())){
            Economy.addEconomy(event.getPlayer().getUniqueId(), 1000);
        }

    }
}
