package de.alex.Geld;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class updater {
    private static Boolean updating = null;
    private static Thread updater_thread = new Thread();
    public static void Start(){
        updater_thread.run();
    }
    public static void Stop(){
        updater_thread.interrupt();
    }
    public static void ini(){
        updater_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                updating = true;
                try {
                    File temp = File.createTempFile("temp",".txt");
                    if(Files.exists(Paths.get(temp.getAbsolutePath()))){
                        Files.delete(Paths.get(temp.getAbsolutePath()));
                    }else{
                        System.exit(2);
                    }
                    Libarys.download("http://ts3byalex.ddns.net/pw/geld_ver.txt",temp.getAbsolutePath());
                    BufferedReader br = new BufferedReader(new FileReader(temp.getAbsolutePath()));
//                    String line = br.readLine();
//                    while (line != null){
//                        System.out.println(line);
//                        line = br.readLine();
//                    }
                    if(!br.readLine().equals(Main.ver)){
                        br.close();
                        //System.out.println(Main.ver+"|"+br.readLine());
                        if(Files.exists(Paths.get(temp.getAbsolutePath()))){
                            Files.delete(Paths.get(temp.getAbsolutePath()));
                        }else{
                            System.exit(2);
                        }
                        System.out.println("Newer Version Availible");
                        String Filename = JOptionPane.showInputDialog(null,"type name of the new updated jar in","New Update",JOptionPane.QUESTION_MESSAGE);
                        try {
                            if(Filename.equals("null") || Filename.equals("")){
                                System.exit(404);
                            }
                        }catch (NullPointerException e){
                            System.exit(403);
                        }
                        Libarys.download("http://ts3byalex.ddns.net/pw/Geld_Managment.jar",System.getProperty("user.dir")+"\\"+Filename+".jar");
                        String jar = (new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getFile())).getName();
                        if(jar.contains(".jar")){
                            jar = jar.replace(".jar","");
                            jar = jar.replaceAll(" ",".SPACE.");
                        }else{
                            Process proc = Runtime.getRuntime().exec("java -jar "+Filename+".jar");
                        }
                        Process proc = Runtime.getRuntime().exec("java -jar "+Filename+".jar -old "+jar);
                        System.exit(403);
                    }else{
                        br.close();
                        if(Files.exists(Paths.get(temp.getAbsolutePath()))){
                            Files.delete(Paths.get(temp.getAbsolutePath()));
                        }else{
                            System.exit(2);
                        }
                        updating = false;
                        return;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Update Error");
                    System.exit( 99);
                }
            }
        });
    }
    public static Boolean isupdating(){
        return updating;
    }

}
