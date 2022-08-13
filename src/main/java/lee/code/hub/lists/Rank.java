package lee.code.hub.lists;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Rank {

    MOD("&6[&5&lMod&6]", "c"),
    ADMIN("&6[&a&lAdmin&6]", "b"),
    OWNER("&6[&#F40000&lOwner&6]", "a"),

    ;

    @Getter private final String prefix;
    @Getter private final String priority;
}
