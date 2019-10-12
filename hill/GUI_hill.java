package hill;


import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.TextArea;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI_hill extends JFrame {

    private JLabel jLabel1 = new JLabel();
    private TextArea input_area = new TextArea();
    private JButton encrypt_text = new JButton();
    private TextArea output_area = new TextArea();
    private JButton choose = new JButton();
    private JButton decrypt_text = new JButton();
    String[] comboTypes = {"Encrypt", "Decrypt"};
    private JButton attack = new JButton();
    private JButton save_file = new JButton();
    private JLabel jLabel2 = new JLabel();
    private JButton decrypt_file = new JButton();
    private JButton encrypt_file = new JButton();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private TextArea key = new TextArea();
    private JLabel jLabel5 = new JLabel();
    private JTextField sz = new JTextField();
    private JButton enter_key = new JButton();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    public GUI_hill() {

        try {
            jbInit();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_hill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI_hill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI_hill.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI_hill.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUI_hill frame = new GUI_hill();

        frame.setTitle("Caesar Cipher");
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void jbInit() throws Exception {
        setLayout(null);
        this.setSize(new Dimension(500, 650));
        //this.setBackground(new Color(0, 0, 0));
        this.setFont(new Font("Informal Roman", 1, 16));

        jLabel1.setText("Hill Cipher");
        jLabel1.setBounds(new Rectangle(15, 0, 470, 50));
        jLabel1.setBackground(SystemColor.windowText);
        jLabel1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText,
                2));
        jLabel1.setFont(new Font("Snap ITC", 0, 16));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        
        
        
         jLabel6.setText("Input Area");
        jLabel6.setBounds(new Rectangle(5, 220, 200, 35));
        jLabel6.setFont(new Font("Snap ITC", 0, 14));
        
        

        input_area.setBounds(new Rectangle(0, 250, 345, 90));
        input_area.setColumns(5);

        encrypt_text.setText("Encrypt");
        encrypt_text.setBounds(new Rectangle(355, 250, 125, 25));
        encrypt_text.setFont(new Font("Snap ITC", 0, 14));
        encrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        encrypt_text.setBackground(new Color(248, 248, 248));
        encrypt_text.addActionListener(new Lis());

        output_area.setBounds(new Rectangle(0, 460, 500, 120));

        choose.setText("Choose File");
        choose.setBounds(new Rectangle(125, 355, 125, 40));
        choose.setFont(new Font("Snap ITC", 0, 14));
        choose.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        choose.setBackground(new Color(248, 248, 248));
        choose.addActionListener(new Lis());

        decrypt_text.setText("Decrypt");
        decrypt_text.setBounds(new Rectangle(355, 280, 125, 25));
        decrypt_text.setFont(new Font("Snap ITC", 0, 14));
        decrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        decrypt_text.setBackground(new Color(248, 248, 248));
        decrypt_text.addActionListener(new Lis());

        attack.setText("Attack");
        attack.setBounds(new Rectangle(295, 430, 125, 25));
        attack.setFont(new Font("Snap ITC", 0, 14));
        attack.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        attack.setBackground(new Color(248, 248, 248));
        attack.addActionListener(new Lis());

        save_file.setText("Save file");
        save_file.setBounds(new Rectangle(125, 410, 125, 40));
        save_file.setFont(new Font("Snap ITC", 0, 14));
        save_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        save_file.setBackground(new Color(248, 248, 248));
        save_file.addActionListener(new Lis());


        jLabel2.setText("Output");
        jLabel2.setBounds(new Rectangle(5, 415, 80, 35));
        jLabel2.setFont(new Font("Snap ITC", 0, 14));

        decrypt_file.setText("Decrypt");
        decrypt_file.setText("Decrypt");
        decrypt_file.setBounds(new Rectangle(295, 390, 125, 25));
        decrypt_file.setFont(new Font("Snap ITC", 0, 14));
        decrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        decrypt_file.setBackground(new Color(248, 248, 248));
        decrypt_file.addActionListener(new Lis());

        encrypt_file.setText("Encrypt");
        encrypt_file.setBounds(new Rectangle(295, 355, 125, 25));
        encrypt_file.setFont(new Font("Snap ITC", 0, 14));
        encrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        encrypt_file.setBackground(new Color(248, 248, 248));
        encrypt_file.addActionListener(new Lis());

        jLabel3.setText("Input");
        jLabel3.setBounds(new Rectangle(5, 355, 80, 35));
        jLabel3.setFont(new Font("Snap ITC", 0, 14));
        
        jLabel4.setText("Key Square Matrix");
        jLabel4.setBounds(new Rectangle(5, 90, 220, 35));
        jLabel4.setFont(new Font("Snap ITC", 0, 14));

        key.setBounds(new Rectangle(0, 125, 345, 100));
        key.setColumns(5);
        jLabel5.setText("Size of Key");
        jLabel5.setBounds(new Rectangle(0, 55, 165, 35));
        jLabel5.setFont(new Font("Snap ITC", 0, 14));
        sz.setBounds(new Rectangle(145, 55, 100, 35));
        sz.setText("1");
        enter_key.setText("Enter key");
        enter_key.setBounds(new Rectangle(355, 160, 125, 25));
        enter_key.setFont(new Font("Snap ITC", 0, 14));
        enter_key.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        enter_key.setBackground(new Color(248, 248, 248));
        enter_key.addActionListener(new Lis());
        
        
        jLabel9.setText("Time : ");
        jLabel9.setBounds(new Rectangle(300, 60, 80, 35));
        jLabel9.setFont(new Font("Snap ITC", 0, 14));
        
        jLabel8.setText(" ");
        jLabel8.setBounds(new Rectangle(360, 60, 80, 35));
        jLabel8.setFont(new Font("Snap ITC", 0, 14)); 
        jLabel8.setForeground(Color.red);
        
        this.getContentPane().add(enter_key, null);
        this.getContentPane().add(sz, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(key, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(encrypt_file, null);
        this.getContentPane().add(decrypt_file, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(save_file, null);
        this.getContentPane().add(attack, null);
        this.getContentPane().add(decrypt_text, null);
        this.getContentPane().add(choose, null);
        this.getContentPane().add(output_area, null);
        this.getContentPane().add(encrypt_text, null);
        this.getContentPane().add(input_area, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(jLabel8, null);
        this.getContentPane().add(jLabel9, null);

    }

    private String promptForFile() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    private String saveForFile() {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        String s = "";
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            s = fileToSave.getAbsolutePath();

        }
        return s;
    }
    hillkey ob;
    hill ob1;
    hillattack ob2;
    String s, s1, s2,s0;
    File f1, f2;
    int size=2;
    int [] [] k = new int[size][size];
    long t,t1;
    class Lis implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            try {
                ob = new hillkey(k);
                ob1 = new hill(ob);
                ob2 = new hillattack();
                
                if (e.getSource() == enter_key) {
                    int cnt = 0 ;
                    size = Integer.parseInt(sz.getText());
                    k = new int[size][size];
                    java.util.Scanner in = new Scanner(key.getText());
                    for(int i = 0 ; i < size ; i++){
                        for(int j = 0 ; j < size ; j++){
                            k[i][j] = in.nextInt();
                        }  
                    }
                }
                else if (e.getSource() == encrypt_text) {
                    t1 = System.currentTimeMillis();
                    s = input_area.getText();
                    output_area.setText(ob1.encrypt(s));
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t+"");
                     
                } else if (e.getSource() == decrypt_text) {
                    t1 = System.currentTimeMillis();
                    s = input_area.getText();
                    output_area.setText(ob1.decrypt(s));
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t+"");
                } else if (e.getSource() == choose) {
                    f1 = new File(promptForFile());
                } else if (e.getSource() == save_file) {
                    s2 = saveForFile();
                } else if (e.getSource() == encrypt_file) {
                    t1 = System.currentTimeMillis();
                    s1 = ob1.encrypt(f1);
                    f2 = new File(s2);
                    f2.createNewFile();
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t+"");
                } else if (e.getSource() == decrypt_file) {
                    t1 = System.currentTimeMillis();
                    s1 = ob1.decrypt(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t+"");
                } else if (e.getSource() == attack) {
                    Hill_attack frame = new Hill_attack();

                    frame.setTitle("Hill Cipher");
                    frame.setSize(500, 450);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }

            } catch (Exception ex) {
            }
        }
    }//end of Lis
}
/*

9 4
5 7

*/