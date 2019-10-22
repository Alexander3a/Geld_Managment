package de.alex.Geld;

import javax.swing.*;

public class GUI extends JFrame{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private JPanel MainPannel;
    private JLabel Text;
    private javax.swing.JTree tree2;
    private JProgressBar progressBar1;
    private JCheckBox Silent_Box;
    private JCheckBox Delete_Box;
    private JButton addButton;
    private JButton removeButton;
    private JTextArea Search;
    private JScrollPane ScrollPane;

    public JTextArea getSearch() {
        return Search;
    }

    public JCheckBox getDelete_Box() {
        return Delete_Box;
    }

    public JCheckBox getSilent_Box() {
        return Silent_Box;
    }

    public JProgressBar getProgressBar1() {
        return progressBar1;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public void setTree2(javax.swing.JTree tree2) {
//        if(!Main.Console){
//            Libarys.printf(this.tree2 +"");
//        }else{
            System.out.println(this.tree2 +"");
//        }
        this.tree2 = tree2;
    }

    public JTree getTree2() {
        return tree2;
    }

    public JLabel getText() {
        return Text;
    }

    public GUI(){
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setTitle("Life sucks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ScrollPane.hide();
        //scrollPane.setLayout(new GridLayout());
        //MainPannel.add(scrollPane);
        setContentPane(MainPannel);

    }
}
