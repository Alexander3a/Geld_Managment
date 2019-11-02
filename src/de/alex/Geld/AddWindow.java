package de.alex.Geld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow  extends JFrame {
    private JPanel MainPannell;
    private JTextPane Beschreibung_Box;
    private JTextPane Cent_Box;
    private JTextPane Euro_Box;
    private JComboBox Positiv_Box;
    private JButton addButton;

    public AddWindow(){
        setVisible(true);
        setSize(450, 150);
        setTitle("Life sucks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(MainPannell);
        Positiv_Box.addItem("+");
        Positiv_Box.addItem("-");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Cent_Box.getText().equalsIgnoreCase("")){
                    Cent_Box.setText("00");
                }
                if(Cent_Box.getText().length()==1){
                    Cent_Box.setText(Cent_Box.getText()+"0");
                }
                String Betrag = Positiv_Box.getModel().getSelectedItem()+Euro_Box.getText()+"."+Cent_Box.getText();
                //System.out.println(Betrag);
                int yeet = JOptionPane.showConfirmDialog(null,"Ist "+Betrag+"€ richtig?");
                if(yeet == 0){
                    try{
                        int Euro = Integer.MAX_VALUE;
                        int Cent = Integer.MAX_VALUE;
                        Euro = Integer.valueOf(Euro_Box.getText());
                        Cent = Integer.valueOf(Euro_Box.getText());
                        if(Euro == Integer.MAX_VALUE)throw new Exception();
                        if(Cent == Integer.MAX_VALUE)throw new Exception();
                    }catch (Exception ee){
                        JOptionPane.showMessageDialog(null,"I think thats just a bit wrong");
                        return;
                    }
                    Boolean out = Transaction_Api.add(Main.currend_db,Betrag,Beschreibung_Box.getText());
                    if(out){
                        Nodes.reload();
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Failed");
                    }
                }
                if(yeet == 2){
                    setVisible(false);
                }
            }
        });
    }
}
