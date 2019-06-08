package de.alex.Geld;

import Exeption.NotImpemented;
import java.sql.SQLException;

public class Main {
    public static String currend_db = "";
    public static LoginUI loginUI;
    public static int CurrentMaxId = -1;
    public static int CurrentNextid = -1;

    public static void main(String[] args)throws NotImpemented {
        try{
            Msql.connect();
        }catch (SQLException e){
            e.printStackTrace();
            Libarys.printf("Msql Connection Failed");
        }
        if(Msql.isConnected()) {
            updater.ini();
            updater.Start();

            while (updater.isupdating() != false){
                int trash = 1;
                trash = 2;
                System.out.println("still updating");
            }
            if (!updater.isupdating()) {
                String[] db_list = new String[2];
                db_list[0] = "gay";
                db_list[1] = "test";
                loginUI = new LoginUI(db_list);
                //Api testing part start







                System.out.println(Transaction_Api.CalcDanach("-00.10€","01.00€"));












                //Api testing part end
            }else {
                System.out.println("updating now");
            }
        }
    }
}
