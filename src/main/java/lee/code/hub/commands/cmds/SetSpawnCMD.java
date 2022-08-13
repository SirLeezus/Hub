package lee.code.hub.commands.cmds;

import lee.code.hub.Hub;
import lee.code.hub.database.CacheManager;
import lee.code.hub.lists.Lang;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            Hub plugin = Hub.getPlugin();
            CacheManager cacheManager = plugin.getCacheManager();
            String worldName = player.getWorld().getName();
            Location location = player.getLocation();
            cacheManager.setSpawn(location);
            player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.COMMAND_SETSPAWN_MAIN_SUCCESSFUL.getComponent(new String[] { worldName })));
        } else sender.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NOT_CONSOLE_COMMAND.getComponent(null)));
        return true;
    }
}
