package de.alex.Geld;

import javax.swing.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static void run(){
        String[] db_list = new String[0];
        Main.loginUI = new LoginUI(db_list);
    }
    public static void ontrylog(String username,String password){
        if(getMsqldata(username, password,make_uuid()) == 1){
            try {
                Msql.connect();
            }catch (SQLException e){
                //xd
            }

            String uuid = null;
            String StartClip = null;
            Boolean Admin = null;
            Boolean Silent = null;
            Boolean Debug = null;
            Boolean Log = null;
            int Version = -1;
            Boolean Banned = null;
            Boolean Read_only = null;
            if(1 == 1){
                try {
                    PreparedStatement ps = Msql.con.prepareStatement("SELECT * FROM `geld_db`.`Users` WHERE `Username`=? AND `password`=?");
                    ps.setString(1,username);
                    ps.setString(2,password);
                    ResultSet rs = ps.executeQuery();
                    int iii = -1;
                    for (int i = 0; rs.next(); i++) {
                        iii=i;
                        if(i==1){
                            //WTF
                            System.exit(3);
                        }
                        uuid = rs.getString("uuid");
                        StartClip = rs.getString("StartClip");
                        Admin = getBoolean(rs.getString("Admin"));
                        Silent = getBoolean(rs.getString("Silent"));
                        Debug = getBoolean(rs.getString("Debug"));
                        Log = getBoolean(rs.getString("Log"));
                        Version = Integer.valueOf(rs.getString("Version"));
                        Banned = getBoolean(rs.getString("Banned"));
                        Read_only = getBoolean(rs.getString("Read_only"));
                    }
                    if(iii!=0){
                        JOptionPane.showMessageDialog(null,"Wrong login Data");
                        System.out.println("Well that is wrong");
                        System.exit(420);
                    }

                }catch (SQLException e){

                }
            }
            Main.user = new User(username,password,uuid,Admin,Silent,Debug,Log,Version,Banned,Read_only);
            Main.loggedin();

        }else{
            //something is fucked
        }
    }
    public static int getMsqldata(String username,String password,String uuid){
        try {
            String response = test("%"+username+"%%"+password+"%%"+uuid+"%");
            if(response.equals("failed")){
                JOptionPane.showMessageDialog(null,"Your Login Data seems to be wrong");
                System.exit(87);
            }else{
                if(response.equals("banned")){
                    JOptionPane.showMessageDialog(null,"Your are Banned from using this System");
                    System.exit(88);
                }
                if(response.equals("wronguuid")){
                    JOptionPane.showMessageDialog(null,"Your uuid does not match");
                    System.exit(89);
                }
                if(response.startsWith("say ")){
                    JOptionPane.showMessageDialog(null,response.replace("say ",""));
                    System.exit(90);
                }
                if(response.equals("")){
                    JOptionPane.showMessageDialog(null,"The Server may be offline check your Configuration File");
                    System.exit(91);
                }else{
                    String r_username = response.split("%")[1];
                    String r_password = response.split("%")[3];
                    Config.setUsername(r_username);
                    Config.setPassword(r_password);
                }
            }

        }catch (IOException e){

        }
        return 1;
    }
    public static Boolean getBoolean(String rs){
        if(rs.equalsIgnoreCase("true")){
            return true;
        }
        if(rs.equalsIgnoreCase("false")){
            return false;
        }
        return null;
    }
    static String test(String login_data) throws IOException {
        String ip = "ts3byalex.ddns.net"; // localhost
//        String ip = "127.0.0.1";
        int port = 42069;
        java.net.Socket socket = new java.net.Socket(ip,port); // verbindet sich mit Server
        String zuSendendeNachricht = "Auth:"+login_data;
        schreibeNachricht(socket, zuSendendeNachricht);
        String empfangeneNachricht = leseNachricht(socket);
        //System.out.println(empfangeneNachricht);
        socket.close();
        return empfangeneNachricht;
    }
    static void schreibeNachricht(java.net.Socket socket, String nachricht) throws IOException {
        PrintWriter printWriter =
                new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
        printWriter.print(nachricht);
        printWriter.flush();
    }
    static String leseNachricht(java.net.Socket socket) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
        String nachricht = "";
        try {
            nachricht = new String(buffer, 0, anzahlZeichen);
        }catch (StringIndexOutOfBoundsException e){
            if(e.toString().startsWith("java.lang.StringIndexOutOfBoundsException: offset 0, count -1, length 200")){
                JOptionPane.showMessageDialog(null,"The Server fucked up");
                System.exit(8675);
            }else{
                e.printStackTrace();
            }
        }
        return nachricht;
    }
    static String make_uuid(){
        return HWID.bytesToHex(HWID.generateHWID());
    }
}
