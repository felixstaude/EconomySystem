package felixstaude.economysystem.money;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PlaytimeReward {

    /*
    TODO    - activate in config file

    TODO    - amount in config file

    TODO    - playtime saved in mysql database
                - UUID, Playtime in seconds

    TODO    - playtime command

     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // save the playtime

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "n" +
                        "Thread's name: " + Thread.currentThread().getName());
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        timer.schedule(task, delay);

    }
}
