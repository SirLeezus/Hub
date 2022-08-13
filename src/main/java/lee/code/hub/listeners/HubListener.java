package lee.code.hub.listeners;

import lee.code.hub.Data;
import lee.code.hub.Hub;
import lee.code.hub.lists.HubItem;
import lee.code.hub.menusystem.menus.ServerSelector;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

import java.util.UUID;

public class HubListener implements Listener {

    @EventHandler
    public void onVoidDeath(PlayerMoveEvent e) {
        if (e.getTo().getBlockY() <= 0) {
            e.getPlayer().teleportAsync(Hub.getPlugin().getCacheManager().getSpawn());
        }
    }

    @EventHandler
    public void onHubInteractInventory(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        Data data = Hub.getPlugin().getData();
        if (e.getAction().isRightClick()) {
            if (player.getInventory().getItemInMainHand().equals(HubItem.SERVER_SELECTOR.getItem())) {
                new ServerSelector(data.getPlayerMU(uuid)).open();
                player.playSound(player.getLocation(), Sound.ENTITY_LLAMA_SWAG, 1, 1);
            }
        }
    }

    @EventHandler
    public void onHubExplodeEvent(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onHubBlockBurn(BlockBurnEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onBlockChange(EntityChangeBlockEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onHubItemDrop(PlayerDropItemEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onHubLeafDecay(LeavesDecayEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onHubDamage(EntityDamageEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onHubBlockPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHubFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onHubPlayerSwapHandItems(PlayerSwapHandItemsEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
    }

    @EventHandler
    public void onHubBlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
    }

    @EventHandler
    public void onHubHangingEntityBreak(HangingBreakByEntityEvent e) {
        if (e.getRemover() instanceof Player player) {
            if (player.getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHubVehicleBreak(VehicleDestroyEvent e) {
        if (e.getAttacker() instanceof Player player) {
            if (player.getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHubBucketEmpty(PlayerBucketEmptyEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
    }

    @EventHandler
    public void onHubArmorStandInteract(PlayerInteractAtEntityEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
    }

    @EventHandler
    public void onHubEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player) {
            if (player.getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHubMoveInventoryItem(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player player) {
            if (player.getGameMode().equals(GameMode.ADVENTURE)) e.setCancelled(true);
        }
    }
}
