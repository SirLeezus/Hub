package lee.code.hub.database;

import lee.code.core.ormlite.dao.Dao;
import lee.code.core.ormlite.dao.DaoManager;
import lee.code.core.ormlite.jdbc.JdbcConnectionSource;
import lee.code.core.ormlite.jdbc.db.DatabaseTypeUtils;
import lee.code.core.ormlite.logger.LogBackendType;
import lee.code.core.ormlite.logger.LoggerFactory;
import lee.code.core.ormlite.support.ConnectionSource;
import lee.code.core.ormlite.table.TableUtils;
import lee.code.hub.Hub;
import lee.code.hub.database.tables.ServerTable;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.SQLException;

public class DatabaseManager {

    private Dao<ServerTable, String> serverDao;

    @Getter(AccessLevel.NONE)
    private ConnectionSource connectionSource;

    private String getDatabaseURL() {
        Hub plugin = Hub.getPlugin();
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
        return "jdbc:sqlite:" + new File(plugin.getDataFolder(), "database.db");
    }

    public void initialize() {
        LoggerFactory.setLogBackendFactory(LogBackendType.NULL);

        try {
            String databaseURL = getDatabaseURL();
            connectionSource = new JdbcConnectionSource(
                    databaseURL,
                    "test",
                    "test",
                    DatabaseTypeUtils.createDatabaseType(databaseURL));
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {
        CacheManager cacheManager = Hub.getPlugin().getCacheManager();

        //server data
        TableUtils.createTableIfNotExists(connectionSource, ServerTable.class);
        serverDao = DaoManager.createDao(connectionSource, ServerTable.class);
        //load server data into cache
        for (ServerTable serverTable : serverDao.queryForAll()) cacheManager.setServerData(serverTable);

    }

    public void closeConnection() {
        try {
            connectionSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void createServerTable(ServerTable serverTable) {
        Bukkit.getScheduler().runTaskAsynchronously(Hub.getPlugin(), () -> {
            try {
                serverDao.create(serverTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public synchronized void updateServerTable(ServerTable serverTable) {
        Bukkit.getScheduler().runTaskAsynchronously(Hub.getPlugin(), () -> {
            try {
                serverDao.update(serverTable);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
