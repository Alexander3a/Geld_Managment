package de.alex.Geld;

public class LoginUI extends JFrame{
    private JTextField PasswordField;
    private JTextField UsernameField;
    private JButton loginButton;
    private JPanel MainPannel;
    private JComboBox Database_Selectrer;

    public LoginUI(){
        setVisible(true);
        setSize(450, 150);
        setTitle("Pls dont log in");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(MainPannel);
        Database_Selectrer.addItem("Alex_Money1");
        Database_Selectrer.addItem("Testbase");




        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loggin.username = UsernameField.getText();
                Loggin.password = PasswordField.getText();
                setVisible(false);
                //try {
                    //Loggin.run();
                //} catch (SQLException ex) {
                //    ex.printStackTrace();
                //}
                //Main.resumeStart();
            }
        });
    }
}