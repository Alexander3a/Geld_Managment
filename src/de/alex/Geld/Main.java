package de.alex.Geld;

public class Main {
    public static String currend_db = "";
    public static LoginUI loginUI;

    public static void main(String[] args) {
	// write your code here
        String[] db_list = new String[2];
        db_list[0]= "gay";
        db_list[1]= "test";
        loginUI = new LoginUI(db_list);
    }
}
