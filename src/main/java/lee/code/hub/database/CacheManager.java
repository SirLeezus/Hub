package lee.code.hub.database;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lee.code.core.util.bukkit.BukkitUtils;
import lee.code.hub.Hub;
import lee.code.hub.database.tables.ServerTable;
import lombok.Getter;
import org.bukkit.Location;

public class CacheManager {

    private final String serverKey = "server";
    @Getter
    private final Cache<String, ServerTable> serverCache = CacheBuilder
            .newBuilder()
            .initialCapacity(5000)
            .recordStats()
            .build();

    //server data

    private ServerTable getServerTable() {
        return getServerCache().getIfPresent(serverKey);
    }

    public void updateServerTable(ServerTable serverTable) {
        getServerCache().put(serverTable.getServer(), serverTable);
        Hub.getPlugin().getDatabaseManager().updateServerTable(serverTable);
    }

    public void createServerData() {
        if (getServerCache().asMap().isEmpty()) {
            ServerTable serverTable = new ServerTable(serverKey);
            getServerCache().put(serverTable.getServer(), serverTable);
            Hub.getPlugin().getDatabaseManager().createServerTable(serverTable);
        }
    }

    public void setServerData(ServerTable serverTable) {
        getServerCache().put(serverTable.getServer(), serverTable);
    }

    public Location getSpawn() {
        String spawn = getServerTable().getSpawn();
        if (!spawn.equals("0")) return BukkitUtils.parseLocation(spawn);
        else return null;
    }

    public boolean isSpawn() {
        return !getServerTable().getSpawn().equals("0");
    }

    public void setSpawn(Location location) {
        ServerTable serverTable = getServerTable();
        String sLocation = BukkitUtils.serializeLocation(location);
        serverTable.setSpawn(sLocation);
        updateServerTable(serverTable);
    }
}
