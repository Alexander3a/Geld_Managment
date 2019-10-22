package de.alex.Geld;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class Nodes {
    public static void start(Boolean removed,Boolean silent){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Search for: Db:"+Main.currend_db);
        Database db = null;
        try {
            db = Transaction_Api.getDbbyName(Main.currend_db);
        }catch (Exception e){

        }
        if(db == null){
            System.exit(34);
        }
        for(int i = 0;i< Integer.valueOf(db.getMaxId())  ;i++){
            Transaction transaction = db.getTrans().get(i);
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("Betrag: "+transaction.getBetrag()+" Beschreibung: "+transaction.getBeschreibung()+" Datum: "+transaction.getDatum());
            node.add(new DefaultMutableTreeNode("Id: "+transaction.getId()));
            node.add(new DefaultMutableTreeNode("Beschreibung: "+transaction.getBeschreibung()));
            node.add(new DefaultMutableTreeNode("Betrag: "+transaction.getBetrag()));
            node.add(new DefaultMutableTreeNode("Davor: "+transaction.getDavor()));
            node.add(new DefaultMutableTreeNode("Datum: "+transaction.getDatum()));
            root.add(node);
        }
        if(Integer.valueOf(db.getMaxId()) == 0){
            root.add(new DefaultMutableTreeNode("Nothing found"));
        }


        setTree(root);
    }
    private static void setTree(DefaultMutableTreeNode root){
        DefaultTreeModel tree = (DefaultTreeModel)Main.gui.getTree2().getModel();
        tree.setRoot(root);
        Main.gui.getTree2().setModel(tree);
    }
}
