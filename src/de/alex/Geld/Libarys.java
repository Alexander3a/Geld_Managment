package de.alex.Geld;

import Exeption.NotImpemented;
import com.sun.istack.internal.NotNull;

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
}
