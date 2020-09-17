package de.alex.Geld;

import java.util.HashMap;

public class Users {
    String Username;
    String password;
    String uuid;
    String StartClip;
    String toexec;
    Boolean Admin;
    Boolean Silent;
    Boolean Debug;
    Boolean Log;
    int Version;
    Boolean Banned;
    Boolean Read_only;
    HashMap Perms_per_db = new HashMap<String,Perms>();
}
