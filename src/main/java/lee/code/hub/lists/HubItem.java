package lee.code.hub.lists;

import lee.code.core.util.bukkit.BukkitUtils;
import lee.code.hub.Hub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum HubItem {
    SERVER_SELECTOR(Material.COMPASS, "&e&lSelect Server", "&e&l> &aTo pick a server right-click this while holding it."),
    SERVER_ADVANCEMENT(Material.EMERALD_BLOCK, " &a&lAdvancement Server", Lang.SERVER_SELECTOR_ADVANCEMENT_LORE.getString(new String[] { String.valueOf(Hub.getPlugin().getData().getServerOnlinePlayers("advancement")) })),
    SERVER_CHAOS(Material.MAGMA_BLOCK, "      &c&lChaos Server", Lang.SERVER_SELECTOR_CHAOS_LORE.getString(new String[] { String.valueOf(Hub.getPlugin().getData().getServerOnlinePlayers("chaos")) })),
    SERVER_VANILLA(Material.GRASS_BLOCK, "     &e&lVanilla Server", Lang.SERVER_SELECTOR_VANILLA_LORE.getString(new String[] { String.valueOf(Hub.getPlugin().getData().getServerOnlinePlayers("vanilla")) })),
    FILLER_GLASS(Material.BLACK_STAINED_GLASS_PANE, "&r", null),

    ;

    @Getter private final Material type;
    @Getter private final String name;
    @Getter private final String lore;

    public ItemStack getItem() {
        return BukkitUtils.getItem(type, name, lore, null, true);
    }
}
