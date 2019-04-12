package de.alex.Geld;

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
        if(!Main.Console){
            Libarys.printf(this.tree2 +"");
        }else{
            System.out.println(this.tree2 +"");
        }
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
        Silent_Box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Silent_Box.isSelected()){
                    NodeStuff.modify(true,Delete_Box.isSelected());
                }else{
                    NodeStuff.modify(false,Delete_Box.isSelected());
                }
            }
        });
        Delete_Box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Delete_Box.isSelected()){
                    NodeStuff.modify(Silent_Box.isSelected(),true);
                }else{
                    NodeStuff.modify(Silent_Box.isSelected(),false);
                }
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
                if(!Main.Console){
                    if(tree2.getSelectionPath().getPathComponent(1).toString().contains("ID:")){
                        if(!Main.Console){
                            Libarys.printf("lol");
                        }else{
                            System.out.println("lol");
                        }
                    }
                    Libarys.printf(tree2.getSelectionPath().getPathComponent(1)+"");
                }else{
                    System.out.println(tree2.getSelectionPath().getPathComponent(1)+"");
                }
            }
        });
        Search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

            }
        });
    }
    private void jbInit() throws Exception {
        JScrollPane jScrollPane1;
        jScrollPane1 = new JScrollPane(tree2,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollpane_ind = new JScrollPane(tree2);

        //jTree1.setBounds(new Rectangle(19, 25, 124, 163));
        //this.getContentPane().add(jTree1);
        add(jScrollPane1);
        //jScrollPane1.setBounds(new Rectangle(133, 170, 2, 2));
        setVisible(true);
    }
}
