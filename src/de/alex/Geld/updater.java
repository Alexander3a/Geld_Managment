package de.alex.Geld;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class updater {
    private static Boolean updating = null;
    private static Thread updater_thread = new Thread();
    public static void Start(){
        updater_thread.run();
    }
    public static void Stop(){
        updater_thread.interrupt();
    }
    public static void ini(){
        updater_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                updating = false;
//                try {
//                    PreparedStatement ps = Msql.con.prepareStatement("");
//                    ps.setString(1,"asd");
//                    ResultSet rs = ps.executeQuery();
//                    while(rs.next()){
//
//                    }
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
            }
        });
    }
    public static Boolean isupdating(){
        return updating;
    }

}
