package de.alex.Geld;

import Exception.MoreThenOneResult;
import Exception.ResultException;

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

        database.setMaxId(overridden+""); //old idk why not working
        database.setMaxId(String.valueOf(buffer.length));
        database.setMaxId((Integer.valueOf(database.getMaxId())-1)+"");
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
    public static Database[] getalldb(){
        try {
            Database[] buff = new Database[0];
            PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`DbPropertys`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Database[] buffer = new Database[buff.length+1];
                for(int i = 0;i< buff.length  ;i++){
                    buffer[i] = buff[i];
                }
                buffer[buff.length] = getDbbyName(rs.getString("Db_Name"));
                buff = buffer;
            }
            return buff;
        }catch (Exception e){
            return null;
        }
    }
    public static void updatedbmaxid(Database db,int new_id){
        try {
            PreparedStatement ps = Msql.con.prepareStatement("UPDATE `DbPropertys` SET `MaxId`=? WHERE `Db_Name`=?");
            ps.setString(1,new_id+"");
            ps.setString(2,db.getDbname());
            ps.execute();
        }catch (Exception e){

        }
    }
    public static void updatemaxprop(Database db){
        updatedbmaxid(db,db.getTrans().size()-1);
    }
    public static void recalcTrans(Transaction trans,String Davor){
        System.out.println("Old: "+trans);
        //DeleteTransformServer(trans);
        trans.Davor = Davor;
        trans.Danach = Transaction_Api.CalcDanach(trans.getBetrag(),Davor);
        try {
            PreparedStatement ps = Msql.con.prepareStatement("UPDATE `TransTable` SET `Davor`=?,`Danach`=? WHERE `Db`=? AND `Id`=?");
            ps.setString(1,Davor);
            ps.setString(2,Transaction_Api.CalcDanach(trans.getBetrag(),Davor));
            ps.setString(3,trans.getDb());
            ps.setString(4,trans.getId());
            ps.execute();
            System.out.println("New: "+getTransbyID(trans.getDb(),trans.getId()));
        } catch (SQLException | MoreThenOneResult e) {
            e.printStackTrace();
        }

    }
    public static Boolean add(String db,String Betrag,String Beschreibeung){
        Long Mills = System.currentTimeMillis();
        Database cdb;
        if(Betrag.contains("+-")){
            Betrag = Betrag.replace("+-","-");
        }
        try {
            cdb = Transaction_Api.getDbbyName(db);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Transaction ltrans = null;
        try {
            if(cdb.getMaxId().equals("-1")){
                ltrans = new Transaction("+0.00","Start Chain Block","0.00","0.00","0","0","12.06.19",1560346309101L,cdb.getDbname());
            }else{
                ltrans = findlasttrans(new Transaction("0,00","If this appers anywhere this is a bug pls report ty","idk","ikd","idk",(Integer.valueOf(cdb.getMaxId())+1)+"","test",10L,cdb.getDbname()));
            }
            if(ltrans == null){
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        String silent = "0";
        if(Beschreibeung.contains("-s") && Main.user.getSilent()){
            Beschreibeung = Beschreibeung.replace("-s","");
            silent = "1";
        }
        try {
            Transaction_Api.updatemaxprop(cdb);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd.MM.yy");
        String today = formatter.format(date);
        String Datum = today+"";
        Transaction trans = new Transaction(Betrag,Beschreibeung,ltrans.getDanach(),Transaction_Api.CalcDanach(Betrag,ltrans.getDanach()),silent,(Integer.valueOf(cdb.getMaxId())+1)+"",Datum,Mills,db);
        try{
            Transaction_Api.SendTranstoServer(trans);
            Transaction newtrans = Transaction_Api.getTransbyID(db,(Integer.valueOf(cdb.getMaxId())+1)+"");
            Transaction_Api.updatemaxprop(Transaction_Api.getDbbyName(db));
            System.out.println("Old: "+trans);
            System.out.println("New: "+newtrans);
            if(!newtrans.toString().equals(trans.toString())){
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void update_silent(Transaction trans,String new_Silent){
        Transaction Strans = null;
        try{
            Strans = getTransbyID(trans.getDb(),trans.getId());

        }catch (Exception e){

        }
        if(Strans == null){
            throw new NullPointerException();
        }
        if(Strans.toString().equals(trans.toString())){
            System.out.println("Old: "+trans);
            //DeleteTransformServer(trans);

            try {
                PreparedStatement ps = Msql.con.prepareStatement("UPDATE `TransTable` SET `Silent`=? WHERE `Db`=? AND `Id`=?");
                ps.setString(1,new_Silent);
                ps.setString(2,trans.getDb());
                ps.setString(3,trans.getId());
                ps.execute();
                System.out.println("New: "+getTransbyID(trans.getDb(),trans.getId()));
            } catch (SQLException | MoreThenOneResult e) {
                e.printStackTrace();
            }
        }else{
            throw new ResultException(0,"update_silent");
        }
    }
    public static Boolean proper_delete(Transaction trans){
        try{
            Database db = Transaction_Api.getDbbyName(trans.getDb());
            System.out.println("Updating: "+trans.getId());
            update_silent(trans,"2");
            for(int i = Integer.valueOf(trans.getId())+1;i<= Integer.valueOf(db.getMaxId())  ;i++){
                recalcTrans(getTransbyID(trans.getDb(),i+""),findlasttrans(trans).getDanach());
                System.out.println("Fixing: "+i);
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }
    public static Transaction findlasttrans(Transaction trans){
        try{
            Transaction pltrans = Transaction_Api.getTransbyID(trans.getDb(),(Integer.valueOf(trans.getId())-1)+"");
            if(pltrans.getSilent().equals("2")){
                Transaction transaction = findlasttrans(pltrans);
                return transaction;
            }else{
                return pltrans;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
