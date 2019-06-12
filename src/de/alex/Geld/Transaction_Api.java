package de.alex.Geld;

import Exeption.MoreThenOneResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction_Api {
//    public static Transaction makesmapletrans(){
//        return new Transaction("Test","Test","test","test","1","12345678","26.06.19",System.currentTimeMillis(),"test");
//    }
//    public static Transaction makesmapletrans(String beschreibung){
//        return new Transaction("Test",beschreibung,"test","test","1","12345678","26.06.19",System.currentTimeMillis(),"test");
//    }
//    public static Transaction makesmapletrans(String beschreibung,Long Time){
//        return new Transaction("Test",beschreibung,"test","test","1","12345678","26.06.19",Time,"test");
//    }
//    public static Transaction makesmapletrans(Long Time){
//        return new Transaction("Test","Test","test","test","1","12345678","26.06.19",Time,"test");
//    }
    public static Transaction[] getTransbyDb(String Db)throws SQLException{
        Transaction[] buffer = new Transaction[0];

        PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Db`=?");
        ps.setString(1,Db);
        ResultSet rs = ps.executeQuery();
        for(int i = 0;rs.next()  ;i++){
            buffer = expand(buffer);
            buffer[i] = new Transaction(rs.getString("Betrag"),rs.getString("Beschreibung"),rs.getString("Davor"),rs.getString("Danach"),rs.getString("Silent"),rs.getString("Id"),rs.getString("Datum"),Long.valueOf(rs.getString("Time")),rs.getString("Db"));
        }
        return buffer;
    }
    public static Database getDbbyName(String Db_name)throws SQLException{
        Transaction[] buffer = new Transaction[0];

        PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Db`=?");
        ps.setString(1,Db_name);
        ResultSet rs = ps.executeQuery();
        for(int i = 0;rs.next()  ;i++){
            buffer = expand(buffer);
            buffer[i] = new Transaction(rs.getString("Betrag"),rs.getString("Beschreibung"),rs.getString("Davor"),rs.getString("Danach"),rs.getString("Silent"),rs.getString("Id"),rs.getString("Datum"),Long.valueOf(rs.getString("Time")),rs.getString("Db"));
        }
        int overridden = 0;
        for(int i = 0;i< buffer.length  ;i++){
            overridden = Integer.valueOf(buffer[i].getId());
        }
        Database database = new Database(buffer,Db_name,null);

        database.setMaxId(overridden+"");
        return database;
    }
    public static Transaction getTransbyID(String Db, String id)throws MoreThenOneResult{
        int results = 0;
        Transaction buffer = null;
        try {

            PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Db`=? AND `Id`=?");
            ps.setString(1,Db);
            ps.setString(2,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                results++;
                buffer = new Transaction(rs.getString("Betrag"),rs.getString("Beschreibung"),rs.getString("Davor"),rs.getString("Danach"),rs.getString("Silent"),rs.getString("Id"),rs.getString("Datum"),Long.valueOf(rs.getString("Time")),rs.getString("Db"));
            }
        }catch (SQLException e){
            throw new MoreThenOneResult(results,"SQL error");
        }
        if(results != 1){
            throw new MoreThenOneResult(results,"Not 1 Result");
        }
        return buffer;
    }
    private static Transaction[] expand(Transaction[] input){
        Transaction[] buffer = new Transaction[input.length+1];
        for(int i = 0;i< input.length  ;i++){
            buffer[i] = input[i];
        }
        return buffer;
    }
    public static void SendTranstoServer(Transaction transaction)throws SQLException {
        PreparedStatement ps2 = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Betrag`=? AND `Beschreibung`=? AND `Davor`=? AND `Danach`=? AND `Silent`=? AND `Id`=? AND `Datum`=? AND `Time`=? AND `Db`=?");
        ps2.setString(1, transaction.getBetrag());
        ps2.setString(2, transaction.getBeschreibung());
        ps2.setString(3, transaction.getDavor());
        ps2.setString(4, transaction.getDanach());
        ps2.setString(5, transaction.getSilent());
        ps2.setString(6, transaction.getId());
        ps2.setString(7, transaction.getDatum());
        ps2.setString(8, transaction.getMills()+"");
        ps2.setString(9, transaction.getDb());
        ResultSet rs2 = ps2.executeQuery();
        Boolean found_shit = false;
        while(rs2.next()){
            found_shit = true;
        }
        if(found_shit){
            System.out.println("Failed to send "+ transaction);
            throw new SQLException("Object allready exists");
        }
        PreparedStatement ps = Msql.con.prepareStatement("INSERT INTO `geld_db`.`TransTable` (`Betrag`, `Beschreibung`, `Davor`, `Danach`, `Silent`, `Id`, `Datum`, `Time`, `Db`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, transaction.getBetrag());
        ps.setString(2, transaction.getBeschreibung());
        ps.setString(3, transaction.getDavor());
        ps.setString(4, transaction.getDanach());
        ps.setString(5, transaction.getSilent());
        ps.setString(6, transaction.getId());
        ps.setString(7, transaction.getDatum());
        ps.setString(8, transaction.getMills()+"");
        ps.setString(9, transaction.getDb());
        ps.execute();
        System.out.println("Succsesfully send "+ transaction);
    }
    public static void DeleteTransformServer(Transaction transaction){
        Boolean failed = false;
        try {
            PreparedStatement ps2 = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Betrag`=? AND `Beschreibung`=? AND `Davor`=? AND `Danach`=? AND `Silent`=? AND `Id`=? AND `Datum`=? AND `Time`=? AND `Db`=?");
            ps2.setString(1, transaction.getBetrag());
            ps2.setString(2, transaction.getBeschreibung());
            ps2.setString(3, transaction.getDavor());
            ps2.setString(4, transaction.getDanach());
            ps2.setString(5, transaction.getSilent());
            ps2.setString(6, transaction.getId());
            ps2.setString(7, transaction.getDatum());
            ps2.setString(8, transaction.getMills()+"");
            ps2.setString(9, transaction.getDb());
            ResultSet rs2 = ps2.executeQuery();
            Boolean found_shit = false;
            while(rs2.next()){
                if(found_shit){
                    failed = true;
                }else{
                    found_shit = true;
                }
            }
            PreparedStatement ps = Msql.con.prepareStatement("DELETE FROM `geld_db`.`TransTable` WHERE `Betrag`=? AND `Beschreibung`=? AND `Davor`=? AND `Danach`=? AND `Silent`=? AND `Id`=? AND `Datum`=? AND `Time`=? AND `Db`=?");
            ps.setString(1, transaction.getBetrag());
            ps.setString(2, transaction.getBeschreibung());
            ps.setString(3, transaction.getDavor());
            ps.setString(4, transaction.getDanach());
            ps.setString(5, transaction.getSilent());
            ps.setString(6, transaction.getId());
            ps.setString(7, transaction.getDatum());
            ps.setString(8, transaction.getMills()+"");
            ps.setString(9, transaction.getDb());
            ps.execute();
        }catch (SQLException e){
            failed = true;
        }
        if(!failed){
            System.out.println("Succsesfully Deleted "+ transaction);
        }else{
            System.out.println("Failed to Delete "+ transaction);
        }
    }
    public static String CalcDanach(String Betrag,String Davor){
        String buf = Davor;
        if(buf.startsWith("+")){
            buf = buf.replace('+',' ');
        }
        if(buf.endsWith("€")){
            buf = buf.replace('€',' ');
        }
//        if(Davor.startsWith("-")){
//            Davor.replace('-',' ');
//        }
        Double Davor_ = Double.valueOf(buf);
        buf = Betrag;
        if(buf.startsWith("+")){
            buf = buf.replace('+',' ');
        }
        if(buf.endsWith("€")){
            buf = buf.replace('€',' ');
        }
        Double Betrag_ = Double.valueOf(buf);
        return (Davor_+Betrag_)+"";
    }
    public static String getCurrentDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd.MM.yy");
        return formatter.format(date)+"";
    }
    public static String getCurrentDb(){
        return Main.currend_db;
    }
    public static int CalcMaxIdperlenght(String Db)throws SQLException{
        return Transaction_Api.getTransbyDb(Db).length;
    }
    public static int CalcNextIdperlenght(String Db)throws SQLException{
        return CalcMaxIdperlenght(Db)+1;
    }
    public static int CalcMaxIdperlastId(String Db)throws SQLException{
       int MaxId = 0;
       int tryid = 1;
       Boolean canceled = false;
       Boolean global_error = false;
       while (!canceled){
           PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Db`=? AND `Id`=?");
           ps.setString(1,Db);
           ps.setString(2,tryid+"");
           ResultSet rs = ps.executeQuery();
           Boolean Error = false;
           while(rs.next()){
               if(Error){
                   global_error = true;
               }
               Error = true;
               MaxId++;
               tryid++;
           }
           if(!Error){
               canceled = true;
           }
       }
       if(global_error){
           throw new SQLException();
       }
       return MaxId;
    }
    public static int CalcNextIdperlastId(String Db)throws SQLException{
        return CalcMaxIdperlastId(Db)+1;
    }
}
