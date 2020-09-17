package de.alex.Geld;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class User extends Users{
    private String Username;
    private String password;
    private String uuid;
    private String StartClip;
    private String toexec;
    private Boolean Admin;
    private Boolean Silent;
    private Boolean Debug;
    private Boolean Log;
    private int Version;
    private Boolean Banned;
    private Boolean Read_only;
    HashMap Perms_per_db = new HashMap<String,Perms>();
    public User(@NotNull String Username, @NotNull String password, @NotNull String uuid, Boolean Admin, Boolean Silent, Boolean Debug, Boolean Log, int Version, @NotNull Boolean Banned, @NotNull Boolean read_only){
        this.Username = Username;
        this.password = password;
        this.uuid = uuid;
        this.StartClip = null;
        this.toexec = null;
        this.Admin = Admin;
        this.Silent = Silent;
        this.Debug = Debug;
        this.Log = Log;
        this.Version = Version;
        this.Banned = Banned;
        this.Read_only = read_only;
    }

    public HashMap getPerms_per_db() {
        return Perms_per_db;
    }

    public void setPerms_per_db(HashMap perms_per_db) {
        Perms_per_db = perms_per_db;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

    public String getUuid() {
        return uuid;
    }

    public Boolean getAdmin() {
        return Admin;
    }

    public Boolean getSilent() {
        return Silent;
    }

    public Boolean getDebug() {
        return Debug;
    }

    public Boolean getLog() {
        return Log;
    }

    public int getVersion() {
        return Version;
    }

    public Boolean getBanned() {
        return Banned;
    }

    public Boolean getRead_only() {
        return Read_only;
    }
}
