package de.alex.Geld;

import com.sun.istack.internal.NotNull;

public class Trans extends Transaction{
    String betrag = "";
    String beschreibung = "";
    String davor = "";
    String danach = "";
    String silent = "";
    String id = "";
    String datum = "";
    Long mills = 0L;
    String db = "";
    public Trans(String Betrag2, String Beschreibung, String Davor, String Danach, String Silent,String id23, String Datum, Long Mills,@NotNull String Db){
        datum = Datum;
        mills = Mills;
        betrag = Betrag2;
        beschreibung = Beschreibung;
        davor = Davor;
        danach = Danach;
        silent = Silent;
        id = id23;
        db = Db;
        //System.out.println(id+id23);
    }
    public String toString() {
        String speciale = "";
        if(!silent.equalsIgnoreCase("0")){
            speciale = " silent = "+silent;
        }
        return "Obj: "+"Trans0x"+id+" Db: "+db+" "+"Betrag: "+betrag+" Beschreibung: "+beschreibung+" Date: "+datum+""+speciale;
    }
}
