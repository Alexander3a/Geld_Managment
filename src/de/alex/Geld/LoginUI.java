package de.alex.Geld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame{
    private JTextField PasswordField;
    private JTextField UsernameField;
    private JButton loginButton;
    private JPanel MainPannel;
    private JComboBox Database_Selecter;
    final String no_db_error= "no database loaded";

    public JComboBox getDatabase_Selecter() {
        return Database_Selecter;
    }

    public LoginUI(String[] datebase_list){
        setVisible(true);
        setSize(450, 150);
        setTitle("Login Window");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MainPannel);
        //Database_Selecter.addItem("Alex_Money1");
        //Database_Selecter.addItem("Testbase");

        for(int i = 0;i<datebase_list.length;i++){
            Database_Selecter.addItem(datebase_list[i]);
        }
        if(datebase_list.length == 0){
            Database_Selecter.addItem(no_db_error);
        }
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.currend_db = Main.loginUI.getDatabase_Selecter().getModel().getSelectedItem()+"";
                Login.ontrylog(UsernameField.getText(),PasswordField.getText());
                setVisible(false);
            }
        });
    }
}