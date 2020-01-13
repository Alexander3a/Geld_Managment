package de.alex.Geld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame{
    public static final int WIDTH = Integer.valueOf(Math.round(Toolkit.getDefaultToolkit().getScreenSize().width*0.5)+"");
    public static final int HEIGHT = Integer.valueOf(Math.round(Toolkit.getDefaultToolkit().getScreenSize().height*0.8)+"");
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
    private JButton Switch_db;
    private Long last;

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
        setSize(WIDTH,HEIGHT);


        setTitle("Life sucks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ScrollPane.hide();
        //scrollPane.setLayout(new GridLayout());
        //MainPannel.add(scrollPane);
        setContentPane(MainPannel);


        Search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.paramString().toString().contains("keyChar=Eingabe") || e.paramString().toString().contains("keyChar=Enter")){
                    Search.setText(Search.getText().replaceAll("\n",""));
                    if(last == null){
                        last = System.currentTimeMillis();
                    }
                    if(System.currentTimeMillis()-last > 250){
                        Nodes.reload(Search.getText());
                    }
                    last = System.currentTimeMillis();
                    return;
                }
                super.keyTyped(e);
            }
        });
        Switch_db.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Main.switchdb();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.addWindow = new AddWindow();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String current = tree2.getSelectionModel().getLeadSelectionPath().toString();
                String[] cut = current.split(",");
                try {
                    current = cut[2];
                    current = current.replace("]","");
                    current = current.replace(" Id: ","");
                    int id = Integer.valueOf(current);
                    System.out.println("Selection: "+id);
                    Transaction trans = Transaction_Api.getTransbyID(Main.currend_db,id+"");
                    int confirm = JOptionPane.showConfirmDialog(null,"Are u shure u want to delete "+trans);
                    if(confirm==0){
                        Boolean delete = Transaction_Api.proper_delete(trans);
                        if(!delete){
                            JOptionPane.showMessageDialog(null,"Failed");
                            Nodes.reload();
                        }else{
                            JOptionPane.showMessageDialog(null,"Succsesfully done");
                            Nodes.reload();
                        }
                    }
                }catch (Exception ee){
                    JOptionPane.showMessageDialog(null,"Pls select the Id Tree and then click remove");
                }


            }
        });
        Silent_Box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nodes.reload_s(Silent_Box.isSelected());
            }
        });
        Delete_Box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nodes.reload_r(Delete_Box.isSelected());
            }
        });
    }
}
