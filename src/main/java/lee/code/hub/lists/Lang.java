package lee.code.hub.lists;

import lee.code.core.util.bukkit.BukkitUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public enum Lang {

    PREFIX("&2&lHub &e➔ &r"),
    COMMAND_SETSPAWN_MAIN_SUCCESSFUL("&aYou have successfully set the main spawn in world &b{0}&a!"),
    ERROR_NOT_CONSOLE_COMMAND("&cThis command does not work in console."),
    TABLIST_HEADER("&#228B22▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n&#4dc462&lJourney Hub\n&#228B22▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"),
    TABLIST_FOOTER("\n&#228B22&lOnline Network&7: &#4dc462{0}"),
    MENU_SELECT_SERVER_TITLE("&e&lSelect Server"),
    CONNECTING("&6Connecting you to server &e{0}&6..."),
    SERVER_RESTART("&aThe hub is restarting! Journey will be back online soon!"),
    SERVER_SELECTOR_CHAOS_LORE(
            "&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" +
                    "\n&6&lServer Info" +
                    "\n&9▬ &aOnline&7: &2{0}/100" +
                    "\n&9▬ &aVersion&7: &21.19.2" +
                    "\n&9▬ &aType&7: &2Mini-Game" +
                    "\n&9▬ &aReleased&7: &28-12-2022" +
                    "\n&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" +
                    "\n&6&lFeatures" +
                    "\n&9▬ &750v50 PvP" +
                    "\n&9▬ &7Unlockable Kits" +
                    "\n&9▬ &7Unlockable Kill Streaks" +
                    "\n&9▬ &7Stat Tracking" +
                    "\n&9▬ &7Team Chat" +
                    "\n&9▬ &7Level System" +
                    "\n&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"),
    SERVER_SELECTOR_VANILLA_LORE(
            "&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" +
                    "\n&6&lServer Info" +
                    "\n&9▬ &aOnline&7: &2{0}/100" +
                    "\n&9▬ &aVersion&7: &21.19.2" +
                    "\n&9▬ &aType&7: &2Survival" +
                    "\n&9▬ &aReleased&7: &cComing soon!" +
                    "\n&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" +
                    "\n&6&lFeatures" +
                    "\n&9▬ &7Griefing is allowed" +
                    "\n&9▬ &7No claiming system" +
                    "\n&9▬ &7No commands" +
                    "\n&9▬ &7PvP enabled" +
                    "\n&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"),
    SERVER_SELECTOR_ADVANCEMENT_LORE(
            "&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" +
                    "\n&6&lServer Info" +
                    "\n&9▬ &aOnline&7: &2{0}/100" +
                    "\n&9▬ &aVersion&7: &21.19.2" +
                    "\n&9▬ &aType&7: &2Survival" +
                    "\n&9▬ &aReleased&7: &28-2-2021" +
                    "\n&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬" +
                    "\n&6&lFeatures" +
                    "\n&9▬ &7Griefing is not allowed" +
                    "\n&9▬ &7Advancement ranking system" +
                    "\n&9▬ &7Over 50+ vanilla improvements" +
                    "\n&9▬ &7Claiming system" +
                    "\n&9▬ &7Online map" +
                    "\n&9▬ &7Home system" +
                    "\n&9▬ &7Duel system" +
                    "\n&9▬ &7Player shops" +
                    "\n&9▬ &7Resource worlds" +
                    "\n&9▬ &7Legendary enchantments" +
                    "\n&9▬ &7Mob hat drops" +
                    "\n&9▬ &7Mob head drops" +
                    "\n&9▬ &7Mystery boxes" +
                    "\n&9▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"),

    ;

    @Getter private final String string;

    public String getString(String[] variables) {
        String value = string;
        if (variables == null || variables.length == 0) return BukkitUtils.parseColorString(value);
        for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
        return BukkitUtils.parseColorString(value);
    }

    public Component getComponent(String[] variables) {
        String value = string;
        if (variables == null || variables.length == 0) return BukkitUtils.parseColorComponent(value);
        for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
        return BukkitUtils.parseColorComponent(value);
    }
}
