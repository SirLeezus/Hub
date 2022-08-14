package lee.code.hub.menusystem.menus;

import lee.code.core.util.bukkit.BukkitUtils;
import lee.code.hub.lists.Lang;
import lee.code.hub.menusystem.Menu;
import lee.code.hub.menusystem.PlayerMU;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerSelector extends Menu {

    public ServerSelector(PlayerMU pmu) {
        super(pmu);
    }

    @Override
    public Component getMenuName() {
        return Lang.MENU_SELECT_SERVER_TITLE.getComponent(null);
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player player = pmu.getOwner();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null) return;
        if (e.getClickedInventory() == player.getInventory()) return;
        if (clickedItem.getType().equals(Material.AIR)) return;
        if (clickedItem.equals(fillerGlass)) return;

        switch (e.getSlot()) {
            case 10 -> BukkitUtils.sendPlayerServer(player, "advancement");
            case 13 -> BukkitUtils.sendPlayerServer(player, "chaos");
            case 16 -> BukkitUtils.sendPlayerServer(player, "vanilla");
        }
        inventory.close();
    }

    @Override
    public void setMenuItems() {
        setFillerGlass();

        ItemMeta advancementServerMeta = advancementServer.getItemMeta();
        BukkitUtils.setItemLore(advancementServerMeta, Lang.SERVER_SELECTOR_ADVANCEMENT_LORE.getString(new String[] { String.valueOf(BukkitUtils.getServerPlayerCount("advancement")) }));
        advancementServer.setItemMeta(advancementServerMeta);

        ItemMeta chaosServerItemMeta = chaosServer.getItemMeta();
        BukkitUtils.setItemLore(chaosServerItemMeta, Lang.SERVER_SELECTOR_CHAOS_LORE.getString(new String[] { String.valueOf(BukkitUtils.getServerPlayerCount("chaos")) }));
        chaosServer.setItemMeta(chaosServerItemMeta);

        ItemMeta vanillaServerItemMeta = vanillaServer.getItemMeta();
        BukkitUtils.setItemLore(vanillaServerItemMeta, Lang.SERVER_SELECTOR_VANILLA_LORE.getString(new String[] { String.valueOf(BukkitUtils.getServerPlayerCount("vanilla"))  }));
        vanillaServer.setItemMeta(vanillaServerItemMeta);

        inventory.setItem(10, advancementServer);
        inventory.setItem(13, chaosServer);
        inventory.setItem(16, vanillaServer);
    }
}
