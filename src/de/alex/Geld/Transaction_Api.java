package de.alex.Geld;

public class Transaction_Api {
    public static Trans makesmapletrans(){
        return new Trans("Test","Test","test","test","1","12345678","26.06.19",System.currentTimeMillis(),"test");
    }
    public static Trans makesmapletrans(String beschreibung){
        return new Trans("Test",beschreibung,"test","test","1","12345678","26.06.19",System.currentTimeMillis(),"test");
    }
    public static Trans[] getTransbyDb(String Db){
        Trans[] buffer = new Trans[0];


        return buffer;
    }
    private static Trans[] expand(Trans[] input){
        Trans[] buffer = new Trans[input.length+1];
        for(int i = 0;i< input.length  ;i++){
            buffer[i] = input[i];
        }
        return buffer;
    }
}
