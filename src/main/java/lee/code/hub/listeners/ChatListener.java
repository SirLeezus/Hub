package lee.code.hub.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import lee.code.core.util.bukkit.BukkitUtils;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncChatEvent e) {
        if (!e.isCancelled()) {
            e.setCancelled(true);
            Bukkit.getServer().sendMessage(e.getPlayer().displayName().append(BukkitUtils.parseColorComponent("&7: ").append(e.message().color(TextColor.color(255, 255, 255)))));
        }
    }
}
