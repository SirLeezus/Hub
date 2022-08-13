package lee.code.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onHubPlayerQuit(PlayerQuitEvent e) {
        //disable quit message
        e.quitMessage(null);
    }
}
