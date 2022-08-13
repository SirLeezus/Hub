package lee.code.hub.listeners;

import lee.code.core.util.bukkit.BukkitUtils;
import lee.code.hub.menusystem.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if (BukkitUtils.hasClickDelay(player)) return;
            menu.handleMenu(e);
        }
    }
}