package de.alex.Geld;

import Exception.NotImpemented;
import java.sql.SQLException;

public class Main {
    public static String currend_db = "";
    public static LoginUI loginUI;
    public static int CurrentMaxId = -1;
    public static int CurrentNextid = -1;
    public static User user;
    public static GUI gui;
    public final static Boolean Api_test = false;
    public static Select_db select_db;
    public static AddWindow addWindow;

    public static void main(String[] args)throws NotImpemented, SQLException {
//        try{
//            Msql.connect();
//        }catch (SQLException e){
//            e.printStackTrace();
//            Libarys.printf("Msql Connection Failed");
//        }
        Login.run();

    }
    public static void loggedin(){
        try {
            if(Msql.isConnected()) {
                updater.ini();
                updater.Start();

                while (updater.isupdating() != false){
                    int trash = 1;
                    trash = 2;
                    System.out.println("still updating");
                }
                if (!updater.isupdating()) {
                    Database[] databases = Transaction_Api.getalldb();
                    String[] dbs = new String[databases.length];
                    for(int i = 0;i< databases.length  ;i++){
                        dbs[i] = databases[i].getDbname();
                    }
                    select_db = new Select_db(dbs);
                }else {
                    System.out.println("updating now");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void ondbselect(){
        //Api testing part start
        if(!Api_test){

            System.out.println("Well u made it");
            System.out.println("The user: ");
            System.out.println("Name: "+user.getUsername());
            System.out.println("Admin: "+user.getAdmin());
            System.out.println("Uuid: "+user.getUuid());
            System.out.println("Current database: "+Main.currend_db);
            gui = new GUI();
            gui.getDelete_Box().setVisible(user.getDebug());
            gui.getSilent_Box().setVisible(user.getSilent());
            gui.getDelete_Box().setVisible(user.getDebug());
            gui.getAddButton().setVisible(!user.getRead_only());
            gui.getRemoveButton().setVisible(user.getDebug());
            Nodes.start(false,false,"");
        }else{
            gui = new GUI();
            Nodes.start(true,true,"");
                try {
                    Transaction_Api.proper_delete(Transaction_Api.getTransbyID("test","2"));
                }catch (Exception e){
                    System.out.println("aids");
                }
        }
        //Api testing part end
    }
    public static void switchdb(){
        Main.gui = null;
        Main.select_db = null;
        Database[] databases = Transaction_Api.getalldb();
        String[] dbs = new String[databases.length];
        for(int i = 0;i< databases.length  ;i++){
            dbs[i] = databases[i].getDbname();
        }
        select_db = new Select_db(dbs);
    }
}
