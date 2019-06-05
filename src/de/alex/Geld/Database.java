package de.alex.Geld;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;

public class Database extends Db{
    private HashMap<Integer, Transaction> trans = new HashMap<>();
    private String MaxId;
    private String Dbname;
    public Database(@NotNull Transaction[] transaction_in_Db, @NotNull String Dbname, String MaxId){
        this.Dbname = Dbname;
        this.MaxId = MaxId;
        if(transaction_in_Db.length == 0){

        }else{
            for(int i = 0; i< transaction_in_Db.length  ; i++){
                trans.put(Integer.valueOf(transaction_in_Db[i].getId()), transaction_in_Db[i]);
            }
        }

    }
    public String toString() {
        return "Database: "+Dbname+" with "+MaxId+"x Transactions";
    }

    public HashMap<Integer, Transaction> getTrans() {
        return trans;
    }

    public String getMaxId() {
        return MaxId;
    }

    public String getDbname() {
        return Dbname;
    }

    public void setTrans(HashMap<Integer, Transaction> trans) {
        this.trans = trans;
    }

    public void setMaxId(String maxId) {
        MaxId = maxId;
    }

    public void setDbname(String dbname) {
        Dbname = dbname;
    }
}
