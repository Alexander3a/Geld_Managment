package de.alex.Geld;

public class Trans extends Transaction{
    String betrag = "";
    String beschreibung = "";
    String davor = "";
    String danach = "";
    String silent = "";
    String id = "";
    String datum = "";
    Long mills = 0L;
    public Trans(String Betrag2, String Beschreibung, String Davor, String Danach, String Silent,String id23, String Datum, Long Mills){
        datum = Datum;
        mills = Mills;
        betrag = Betrag2;
        beschreibung = Beschreibung;
        davor = Davor;
        danach = Danach;
        silent = Silent;
        id = id23;
        //System.out.println(id+id23);
    }
    public String toString() {
        return "Trans0x"+id+" "+"Betrag: "+betrag+" Beschreibung: "+beschreibung;
    }
}
