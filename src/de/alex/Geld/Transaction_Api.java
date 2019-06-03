package de.alex.Geld;

import Exeption.MoreThenOneResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction_Api {
    public static Trans makesmapletrans(){
        return new Trans("Test","Test","test","test","1","12345678","26.06.19",System.currentTimeMillis(),"test");
    }
    public static Trans makesmapletrans(String beschreibung){
        return new Trans("Test",beschreibung,"test","test","1","12345678","26.06.19",System.currentTimeMillis(),"test");
    }
    public static Trans makesmapletrans(String beschreibung,Long Time){
        return new Trans("Test",beschreibung,"test","test","1","12345678","26.06.19",Time,"test");
    }
    public static Trans makesmapletrans(Long Time){
        return new Trans("Test","Test","test","test","1","12345678","26.06.19",Time,"test");
    }
    public static Trans[] getTransbyDb(String Db)throws SQLException{
        Trans[] buffer = new Trans[0];

        PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Db`=?");
        ps.setString(1,Db);
        ResultSet rs = ps.executeQuery();
        for(int i = 0;rs.next()  ;i++){
            buffer = expand(buffer);
            buffer[i] = new Trans(rs.getString("Betrag"),rs.getString("Beschreibung"),rs.getString("Davor"),rs.getString("Danach"),rs.getString("Silent"),rs.getString("Id"),rs.getString("Datum"),Long.valueOf(rs.getString("Time")),rs.getString("Db"));
        }
        return buffer;
    }
    public static Trans getTransbyID(String Db,String id)throws MoreThenOneResult{
        int results = 0;
        Trans buffer = null;
        try {

            PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`TransTable` WHERE `Db`=? AND `Id`=?");
            ps.setString(1,Db);
            ps.setString(2,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                results++;
                buffer = new Trans(rs.getString("Betrag"),rs.getString("Beschreibung"),rs.getString("Davor"),rs.getString("Danach"),rs.getString("Silent"),rs.getString("Id"),rs.getString("Datum"),Long.valueOf(rs.getString("Time")),rs.getString("Db"));
            }
        }catch (SQLException e){
            throw new MoreThenOneResult(results,"SQL error");
        }
        if(results != 1){
            throw new MoreThenOneResult(results,"Not 1 Result");
        }
        return buffer;
    }
    private static Trans[] expand(Trans[] input){
        Trans[] buffer = new Trans[input.length+1];
        for(int i = 0;i< input.length  ;i++){
            buffer[i] = input[i];
        }
        return buffer;
    }
    public static void SendTranstoServer(Trans trans)throws SQLException {
        PreparedStatement ps = Msql.con.prepareStatement("INSERT INTO `geld_db`.`TransTable` (`Betrag`, `Beschreibung`, `Davor`, `Danach`, `Silent`, `Id`, `Datum`, `Time`, `Db`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1,trans.getBetrag());
        ps.setString(2,trans.getBeschreibung());
        ps.setString(3,trans.getDavor());
        ps.setString(4,trans.getDanach());
        ps.setString(5,trans.getSilent());
        ps.setString(6,trans.getId());
        ps.setString(7,trans.getDatum());
        ps.setString(8,trans.getMills()+"");
        ps.setString(9,trans.getDb());
        ps.execute();
        System.out.println("Succsesfully send "+trans);
    }
    public static void DeleteTransformServer(Trans trans){
        Boolean failed = false;
        try {
            PreparedStatement ps = Msql.con.prepareStatement("DELETE FROM `geld_db`.`TransTable` WHERE `Betrag`=? AND `Beschreibung`=? AND `Davor`=? AND `Danach`=? AND `Silent`=? AND `Id`=? AND `Datum`=? AND `Time`=? AND `Db`=?");
            ps.setString(1,trans.getBetrag());
            ps.setString(2,trans.getBeschreibung());
            ps.setString(3,trans.getDavor());
            ps.setString(4,trans.getDanach());
            ps.setString(5,trans.getSilent());
            ps.setString(6,trans.getId());
            ps.setString(7,trans.getDatum());
            ps.setString(8,trans.getMills()+"");
            ps.setString(9,trans.getDb());
            ps.execute();
        }catch (SQLException e){
            failed = true;
        }
        if(!failed){
            System.out.println("Succsesfully Deleted "+trans);
        }else{
            System.out.println("Deliting failed "+trans);
        }
    }
}
