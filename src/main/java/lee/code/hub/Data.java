package lee.code.hub;

import lee.code.core.util.bukkit.BukkitUtils;
import lee.code.core.util.bukkit.ping.Pinger;
import lee.code.core.util.bukkit.scoreboard.BoardBuilder;
import lee.code.core.util.bukkit.scoreboard.CollisionRule;
import lee.code.hub.lists.Lang;
import lee.code.hub.lists.Rank;
import lee.code.hub.menusystem.PlayerMU;
import lee.code.permissions.PermissionsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Data {

    private final ConcurrentHashMap<UUID, PlayerMU> playerMUList = new ConcurrentHashMap<>();

    public PlayerMU getPlayerMU(UUID uuid) {
        if (playerMUList.containsKey(uuid)) {
            return playerMUList.get(uuid);
        } else {
            PlayerMU pmu = new PlayerMU(uuid);
            playerMUList.put(uuid, pmu);
            return pmu;
        }
    }


    public void load() {
        scheduleTabListUpdater();
    }

    private void scheduleTabListUpdater() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Hub.getPlugin(), () -> {
            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                Bukkit.getServer().sendPlayerListHeaderAndFooter(Lang.TABLIST_HEADER.getComponent(null), Lang.TABLIST_FOOTER.getComponent(new String[] { String.valueOf(BukkitUtils.getGlobalPlayerCount()) }));
            }
        }, 10, 40);
    }

    public void updateDisplayName(Player player) {
        UUID uuid = player.getUniqueId();
        BoardBuilder boardBuilder = new BoardBuilder(player);
        if (PermissionsAPI.hasRank(uuid)) {
            Rank rank = Rank.valueOf(PermissionsAPI.getRank(uuid));
            boardBuilder.prefix(rank.getPrefix() + " ");
            boardBuilder.priority(rank.getPriority());
        } else {
            boardBuilder.priority("d");
        }
        boardBuilder.nameColor(ChatColor.YELLOW);
        boardBuilder.collisionRule(CollisionRule.NEVER);
        boardBuilder.create();
    }
}
