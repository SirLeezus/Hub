package lee.code.hub.database.tables;

import lee.code.core.ormlite.field.DatabaseField;
import lee.code.core.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@DatabaseTable(tableName = "server")
public class ServerTable {

    @DatabaseField(id = true, canBeNull = false)
    private String server;

    @DatabaseField(columnName = "spawn", canBeNull = false)
    private String spawn;

    public ServerTable(String server) {
        this.server = server;
        this.spawn = "0";
    }
}