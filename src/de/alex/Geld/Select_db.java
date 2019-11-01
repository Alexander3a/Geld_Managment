package de.alex.Geld;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.glass.ui.Cursor.setVisible;

public class Select_db extends JFrame{
    private JPanel mainPannel;
    private JComboBox Database_Selecter;
    private JButton selectButton;
    final String no_db_error= "no database loaded";
    public Select_db(String[] datebase_list){
        setVisible(true);
        setSize(250, 150);
        setTitle("Select a Database");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPannel);
        for(int i = 0;i<datebase_list.length;i++){
            Database_Selecter.addItem(datebase_list[i]);
        }
        if(datebase_list.length == 0){
            Database_Selecter.addItem(no_db_error);
        }
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.currend_db = Database_Selecter.getModel().getSelectedItem()+"";
                setVisible(false);
                Main.ondbselect();
            }
        });
    }
}
