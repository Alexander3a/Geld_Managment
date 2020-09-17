package de.alex.Geld;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Msql {
        public static String username;
        public static String database;
        public static String host;
        public static String port;
        public static Connection con;
        public static void connect()throws SQLException{
            Msql.port = Config.getPort();
            Msql.host = Config.getHost();
            Msql.username = Config.getUsername();
            Msql.database = Config.getDatabase();
            Boolean lulxds = true;
            if(!isConnected()){
                try {
                    if(Config.getPassword().equalsIgnoreCase("null")){
                        System.out.println("[Msql] MySql Verbindung geschlossen!");
                        //Class.forName("com.mysql.jdbc.Driver");
                        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        con = DriverManager.getConnection("jdbc:mysql://" + host+ ":" +port+ "/" +database,username,null);
                    }else{
                        //System.out.println(DriverManager.getDrivers());
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        Connection conn = null;
                        //System.out.println(DriverManager.getDrivers());
                        //conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/database");
                        //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
                        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                        try {
                            con = DriverManager.getConnection("jdbc:mysql://" + host+ ":" +port+ "/" +database+"?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&max_allowed_packet=1073741824",username,Config.getPassword()); //"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                        }catch (SQLException e){
                            JOptionPane.showMessageDialog(null,"Server was not able to verify your Login Data");
                            System.exit(7);
                        }

                    }
                }catch (SQLException e){
                    e.printStackTrace();
                    lulxds = false;
                }
                if(lulxds){
                    System.out.println("[Msql] MySql Verbindung gestartet!");
                }else{
                    System.out.println("[Msql] MySql Verbindung Error!"+"jdbc:mysql://"+host+ ":" +port+ "/" +"|"+database+"|"+username+"|");
                    System.exit(69);
                }
            }
        }
        public static void close(){
            if(isConnected()){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("[Msql] MySql Verbindung geschlossen!");
                return;
            }
            return;
        }
        public static boolean isConnected(){
            Boolean t = false;
            try {
                if(con == null){
                    t = false;
                }else{
                    t = true;
                }
            }catch (NullPointerException e ){
                e.printStackTrace();
            }
            return t;

        }


}
