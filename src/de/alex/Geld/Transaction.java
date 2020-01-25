package de.alex.Geld;

import org.jetbrains.annotations.NotNull;

public class Transaction extends Trans_Obj {
    private String betrag = "";
    private String beschreibung = "";
    private String davor = "";
    private String danach = "";
    private String silent = "";
    private String id = "";
    private String datum = "";
    private Long mills = 0L;
    private String db = "";
    public Transaction(String Betrag2, String Beschreibung, String Davor, String Danach, String Silent, String Id, String Datum, Long Mills, @NotNull String Db){
        datum = Datum;
        mills = Mills;
        betrag = Betrag2;
        beschreibung = Beschreibung;
        davor = Davor;
        danach = Danach;
        silent = Silent;
        id = Id;
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

    public String getBetrag() {
        return betrag;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getDavor() {
        return davor;
    }

    public String getDanach() {
        return danach;
    }

    public String getSilent() {
        return silent;
    }

    public String getId() {
        return id;
    }

    public String getDatum() {
        return datum;
    }

    public Long getMills() {
        return mills;
    }

    public String getDb() {
        return db;
    }
}
