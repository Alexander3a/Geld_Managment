package de.alex.Geld;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static void run(){
        String[] db_list = new String[2];
        db_list[0] = "gay";
        db_list[1] = "test";
        Main.loginUI = new LoginUI(db_list);
    }
    public static void ontrylog(String username,String password){
        if(getMsqldata(username, password) == 1){
            try {
                Msql.connect();
            }catch (SQLException e){
                //xd
            }

            String uuid = null;
            String StartClip = null;
            Boolean Admin = null;
            Boolean Silent = null;
            Boolean Debug = null;
            Boolean Log = null;
            int Version = -1;
            Boolean Banned = null;
            Boolean Read_only = null;
            if(1 == 1){
                try {
                    PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`Users` WHERE `Username`=? AND `password`=?");
                    ps.setString(1,username);
                    ps.setString(2,password);
                    ResultSet rs = ps.executeQuery();
                    int iii = -1;
                    for (int i = 0; rs.next(); i++) {
                        iii=i;
                        if(i==1){
                            //WTF
                            System.exit(3);
                        }
                        uuid = rs.getString("uuid");
                        StartClip = rs.getString("StartClip");
                        Admin = getBoolean(rs.getString("Admin"));
                        Silent = getBoolean(rs.getString("Silent"));
                        Debug = getBoolean(rs.getString("Debug"));
                        Log = getBoolean(rs.getString("Log"));
                        Version = Integer.valueOf(rs.getString("Version"));
                        Banned = getBoolean(rs.getString("Banned"));
                        Read_only = getBoolean(rs.getString("Read_only"));
                    }
                    if(iii!=0){
                        System.out.println("Well that is is wrong");
                        System.exit(420);
                    }

                }catch (SQLException e){

                }
            }
            Main.user = new User(username,password,uuid,Admin,Silent,Debug,Log,Version,Banned,Read_only);
            Main.loggedin();

        }else{
            //something is fucked
        }
    }
    public static int getMsqldata(String username,String password){

        return 1;
    }
    public static Boolean getBoolean(String rs){
        if(rs.equalsIgnoreCase("true")){
            return true;
        }
        if(rs.equalsIgnoreCase("false")){
            return false;
        }
        return null;
    }
}
