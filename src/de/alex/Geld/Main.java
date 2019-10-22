package de.alex.Geld;

import Exeption.NotImpemented;
import java.sql.SQLException;

public class Main {
    public static String currend_db = "";
    public static LoginUI loginUI;
    public static int CurrentMaxId = -1;
    public static int CurrentNextid = -1;
    public static User user;
    public static GUI gui;
    public final static Boolean Api_test = false;

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

                    //loginUI = new LoginUI(db_list);
                    //Api testing part start
                    if(!Api_test){
                        System.out.println("Well u made it");
                        System.out.println("The user: ");
                        System.out.println("Name: "+user.getUsername());
                        System.out.println("Admin: "+user.getAdmin());
                        System.out.println("Uuid: "+user.getUuid());
                        System.out.println("Current database: "+Main.currend_db);
                        gui = new GUI();
                        Nodes.start(user.getSilent(),user.getSilent());
                    }else{
                        Transaction transaction = new Transaction("+1.00","first test","0.00",Transaction_Api.CalcDanach("+1.00","0.00"),"false","1","22.10.19",8712409L,Main.currend_db);
                        Transaction_Api.SendTranstoServer(transaction);
                        System.out.println("send");
                    }
                    //Api testing part end
                }else {
                    System.out.println("updating now");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
