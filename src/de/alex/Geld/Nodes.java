package de.alex.Geld;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Nodes {
    private static Boolean cremoved = false;
    private static Boolean csilent = false;
    private static String csearch = "";
    public static void start(Boolean removed,Boolean silent,String search){
        cremoved = removed;
        csilent = silent;
        csearch = search;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Search in db:"+Main.currend_db+" for: "+search);
        Database db = null;
        try {
            db = Transaction_Api.getDbbyName(Main.currend_db);
//            for(int i = 0;i< db.getTrans().size()  ;i++){
//                Transaction trans = db.getTrans().get(i);
//                System.out.println(trans);
//            }
//            System.out.println("Max id: "+db.getMaxId()); // to test if api input is right
            //System.out.println(db);
        }catch (Exception e){

        }
        if(db == null){
            System.exit(34);
        }
        int true_trans = 0;
        System.out.println("Search :"+search);
        for(int i = 0;i< Integer.valueOf(db.getMaxId())+1  ;i++){
            Transaction transaction = db.getTrans().get(i);
            Boolean good = true;
            if(!search.equalsIgnoreCase("")){
                good = false;
                if(transaction.getBeschreibung().startsWith(search)){
                    good = true;
                }
                if(transaction.getBeschreibung().contains(search)){
                    good = true;
                }
            }
            if(!silent){
                if(transaction.getSilent().equals("1")){
                   good = false;
               }
            }
            if(!removed){
                if(transaction.getSilent().equals("2")){
                    good = false;
                }
            }
            if(good && !transaction.getSilent().equals("0")&& !transaction.getSilent().equals("1")&& !transaction.getSilent().equals("2")){
                if(silent && removed){
                    good = true;
                }else{
                    good = false;
                }
            }

            if(!good){
                continue;
            }
            int Shhh = Integer.valueOf(transaction.getSilent());
            String Sh_display = "";
            if(Shhh == 1){
                Sh_display = " This was Silent";
            }
            if(Shhh == 2){
                Sh_display = " This was Deleted";
            }
            true_trans++;
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("Betrag: "+transaction.getBetrag()+"€ Beschreibung: "+transaction.getBeschreibung()+" Datum: "+transaction.getDatum()+Sh_display);
            node.add(new DefaultMutableTreeNode("Id: "+transaction.getId()));
            node.add(new DefaultMutableTreeNode("Beschreibung: "+transaction.getBeschreibung()));
            node.add(new DefaultMutableTreeNode("Betrag: "+transaction.getBetrag()+"€"));
            node.add(new DefaultMutableTreeNode("Davor: "+transaction.getDavor()+"€"));
            node.add(new DefaultMutableTreeNode("Danach: "+transaction.getDanach()+"€"));
            node.add(new DefaultMutableTreeNode("Datum: "+transaction.getDatum()));
            root.add(node);
        }
        if(true_trans == 0){
            root.add(new DefaultMutableTreeNode("Nothing found"));
        }else{
            root.add(new DefaultMutableTreeNode("Alles: "+Transaction_Api.findlasttrans(new Transaction("0,00","If this appers anywhere this is a bug pls report ty","idk","ikd","idk",(Integer.valueOf(db.getMaxId())+1)+"","test",10L,db.getDbname())).getDanach()+"€"));
        }


        setTree(root);
    }
    public static void reload(Boolean removed,Boolean silent,String search){
        start(removed, silent, search);
    }
    public static void reload(String search){
        start(cremoved,csilent,search);
    }
    public static void reload_r(Boolean removed){
        start(removed,csilent,csearch);
    }
    public static void reload_s(Boolean silent){
        start(cremoved,silent,csearch);
    }
    public static void reload(){
        start(cremoved,csilent,csearch);
    }
    private static void setTree(DefaultMutableTreeNode root){
        DefaultTreeModel tree = (DefaultTreeModel)Main.gui.getTree2().getModel();
        tree.setRoot(root);
        Main.gui.getTree2().setModel(tree);
    }
}
