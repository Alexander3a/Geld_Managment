package de.alex.Geld;

import Exception.NotImpemented;
import com.sun.istack.internal.NotNull;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Libarys {
    private static String cText = "";
    public static void printf(@NotNull String Text,@NotNull Boolean Con,@NotNull Boolean Gui,Boolean log){

        if(Con){
            System.out.println(Text);

        }
        if(Gui){
            if(Main.user.getLog()){
                Main.gui.getText().setText(Libarys.ntoHtlm(cText+Text+"\n"));
                cText = cText+Text+"\n";
            }else{
                Main.gui.getText().setText(Libarys.ntoHtlm(cText+Text+"\n"));
                cText = cText+Text+"\n";
            }
        }
    }

    public static void printf(@NotNull String Text) throws NotImpemented{
        System.out.println(Text);
    }
    public static void switchdb(String Db){
        Main.currend_db = Db;
        Main.CurrentMaxId = -1;
        Main.CurrentNextid = -1;
    }
    public static void download(String url, String fileName) throws Exception {
        try (InputStream in = URI.create(url).toURL().openStream()) {

            if(!Files.exists(Paths.get(fileName))){
                Files.copy(in, Paths.get(fileName));
                return;
            }else{
                return;
            }
        }
    }
    public static String ntoHtlm(String input){
        String[] split = input.split("\n");
        String out = "<html>";
        for(int i = 0;i< split.length  ;i++){
            if(out.equals("<html>")){
                out = out+split[i];
            }else{
                out = out+"<br>"+split[i];
            }
        }
        out = out+"</html>";
        return out;

    }
}
