package lee.code.hub;

import lee.code.core.util.bukkit.BukkitUtils;
import lee.code.hub.commands.cmds.SetSpawnCMD;
import lee.code.hub.commands.tabs.SetSpawnTab;
import lee.code.hub.database.CacheManager;
import lee.code.hub.database.DatabaseManager;
import lee.code.hub.listeners.*;
import lee.code.hub.lists.Lang;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin {

    @Getter private CacheManager cacheManager;
    @Getter private DatabaseManager databaseManager;
    @Getter private Data data;

    @Override
    public void onEnable() {
        this.cacheManager = new CacheManager();
        this.databaseManager = new DatabaseManager();
        this.data = new Data();

        databaseManager.initialize();
        cacheManager.createServerData();
        data.load();

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        BukkitUtils.kickOnlinePlayers(Lang.SERVER_RESTART.getComponent(null));
        databaseManager.closeConnection();
    }

    private void registerCommands() {
        getCommand("setspawn").setExecutor(new SetSpawnCMD());
        getCommand("setspawn").setTabCompleter(new SetSpawnTab());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new HubListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    public static Hub getPlugin() {
        return Hub.getPlugin(Hub.class);
    }

}
