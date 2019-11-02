package de.alex.Geld;

import Exception.NotImpemented;
import com.sun.istack.internal.NotNull;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Libarys {
    public static void printf(@NotNull String Text,@NotNull Boolean Con,@NotNull Boolean Gui) throws NotImpemented{

        if(Con){
            System.out.println(Text);

        }
        if(Gui){
            throw new NotImpemented(1,"Gui not Impemented");
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
}
