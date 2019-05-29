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
    public Trans(String Betrag2, String Beschreibung, String Davor, String Danach, String Silent,String Id23, String Datum, Long Mills){
        Betrag2 = betrag;
        Beschreibung = beschreibung;
        Davor = davor;
        Danach = danach;
        Silent = silent;
        Id23 = id;
        Datum = datum;
        Mills = mills;
        System.out.println(id+Id);
    }
    public String toString() {
        System.out.println(id);
        return "Trans0x000"+"ID:"+id;
    }
}
