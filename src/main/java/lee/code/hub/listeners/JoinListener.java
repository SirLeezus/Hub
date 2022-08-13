package lee.code.hub.listeners;

import lee.code.hub.Data;
import lee.code.hub.Hub;
import lee.code.hub.database.CacheManager;
import lee.code.hub.lists.HubItem;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class JoinListener implements Listener {

    @EventHandler
    public void onHubPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Hub plugin = Hub.getPlugin();
        CacheManager cacheManager = plugin.getCacheManager();
        Data data = plugin.getData();

        player.getInventory().clear();
        for (PotionEffect potionEffect : player.getActivePotionEffects()) player.removePotionEffect(potionEffect.getType());
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);

        //disable join message
        e.joinMessage(null);

        //server selector
        player.getInventory().setItem(4, HubItem.SERVER_SELECTOR.getItem());

        //display name
        data.updateDisplayName(player);

        if (cacheManager.isSpawn()) player.teleportAsync(cacheManager.getSpawn());
    }
}
