package de.alex.Geld;

import Exception.NotImpemented;

import java.io.File;
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
    public static final String ver = "1.3Release";
    public static String todb = "";

    public static void main(String[] args)throws NotImpemented, SQLException {
//        try{
//            Msql.connect();
//        }catch (SQLException e){
//            e.printStackTrace();
//            Libarys.printf("Msql Connection Failed");
//        }
        String username = "";
        String password = "";
        for(int i = 0;i< args.length  ;i++){
            if(args[i].equals("-old")){
                String filename = args[i+1].replaceAll("%20","\\ ")+".jar";
                File file = new File(System.getProperty("user.dir")+"\\"+filename);
                file.delete();
                System.out.println("Deleted: "+file.getAbsolutePath());
                //JOptionPane.showMessageDialog(null,"Deleted: "+ file.getAbsolutePath());
            }
            if(args[i].equals("-u")){
                username = args[i+1];
            }
            if(args[i].equals("-p")){
                password = args[i+1];
            }
            if(args[i].equals("-d")){
                todb = args[i+1];
            }
        }
        if(!(!username.equals("") && !password.equals(""))){
            Login.run();
        }else{
            Login.ontrylog(username,password);
        }

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
            gui.getDelete_Box().setVisible(user.getAdmin());
            gui.getSilent_Box().setVisible(user.getSilent());
            gui.getAddButton().setVisible(!user.getRead_only());
            gui.getRemoveButton().setVisible(user.getAdmin());
            Nodes.start(false,false,"");
            Libarys.printf("Welcome "+user.getUsername(),false,true,false);
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
