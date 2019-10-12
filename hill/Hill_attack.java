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

public class Hill_attack extends JFrame {

    private JLabel jLabel1 = new JLabel();
    private TextArea dec = new TextArea();
    private TextArea output_area = new TextArea();
    String[] comboTypes = {"Encrypt", "Decrypt"};
    private JButton attack = new JButton();
    private JLabel jLabel4 = new JLabel();
    private TextArea enc = new TextArea();
    private JLabel jLabel5 = new JLabel();
    private JTextField sz1 = new JTextField();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JButton De_file = new JButton();
    private JButton En_file = new JButton();

    public Hill_attack() {

        try {
            jbInit();
        } catch (Exception e) {
        }
    }

    private void jbInit() throws Exception {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hill_attack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Hill_attack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Hill_attack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Hill_attack.class.getName()).log(Level.SEVERE, null, ex);
        }

        setLayout(null);
        this.setSize(new Dimension(500, 479));
        //this.setBackground(new Color(0, 0, 0));
        this.setFont(new Font("Informal Roman", 1, 16));

        jLabel1.setText("Hill Attack");
        jLabel1.setBounds(new Rectangle(15, 0, 470, 50));
        jLabel1.setBackground(SystemColor.windowText);
        jLabel1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText,
                2));
        jLabel1.setFont(new Font("Snap ITC", 0, 16));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        dec.setBounds(new Rectangle(265, 125, 220, 100));
        dec.setColumns(5);

        output_area.setBounds(new Rectangle(0, 295, 500, 120));

        attack.setText("Attack");
        attack.setBounds(new Rectangle(370, 245, 125, 25));
        attack.setFont(new Font("Snap ITC", 0, 14));
        attack.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        attack.setBackground(new Color(248, 248, 248));
        attack.addActionListener(new Lis());

        De_file.setText("De_file");
        De_file.setBounds(new Rectangle(220, 245, 125, 25));
        De_file.setFont(new Font("Snap ITC", 0, 14));
        De_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        De_file.setBackground(new Color(248, 248, 248));
        De_file.addActionListener(new Lis());

        En_file.setText("En_file");
        En_file.setBounds(new Rectangle(70, 245, 125, 25));
        En_file.setFont(new Font("Snap ITC", 0, 14));
        En_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        En_file.setBackground(new Color(248, 248, 248));
        En_file.addActionListener(new Lis());

        jLabel4.setText("Decrypted");
        jLabel4.setBounds(new Rectangle(265, 90, 125, 35));
        jLabel4.setFont(new Font("Snap ITC", 0, 14));

        enc.setBounds(new Rectangle(0, 125, 220, 100));
        enc.setColumns(5);
        jLabel5.setText("Encrypted");
        jLabel5.setBounds(new Rectangle(0, 90, 130, 35));
        jLabel5.setFont(new Font("Snap ITC", 0, 14));
        sz1.setBounds(new Rectangle(125, 50, 90, 40));
        sz1.setText("1");
        sz1.setFont(new Font("Snap ITC", 0, 11));
        jLabel6.setText("Size");
        jLabel6.setBounds(new Rectangle(15, 50, 130, 35));
        jLabel6.setFont(new Font("Snap ITC", 0, 14));

        jLabel7.setText("Time : ");
        jLabel7.setBounds(new Rectangle(265, 45, 125, 35));
        jLabel7.setFont(new Font("Snap ITC", 0, 14));

        jLabel8.setText("");
        jLabel8.setBounds(new Rectangle(330, 45, 125, 35));
        jLabel8.setFont(new Font("Snap ITC", 0, 14));
        jLabel8.setForeground(Color.red);

        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(sz1, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(enc, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(attack, null);
        this.getContentPane().add(output_area, null);
        this.getContentPane().add(dec, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jLabel7, null);
        this.getContentPane().add(jLabel8, null);
        this.getContentPane().add(De_file, null);
        this.getContentPane().add(En_file, null);

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

    hillattack ob;
    int size1 = 2;
    int[][] k1 = new int[size1][size1];
    int[][] k2 = new int[size1][size1];
    String[] st1, st2;
    File f1, f2;
    long t, t1;

    class Lis implements ActionListener {

        public void actionPerformed(ActionEvent E) {
            try {

                ob = new hillattack();

                if (E.getSource() == attack) {
                    t1 = System.currentTimeMillis();
                    
                    String de  = "", en ="";
                    if(!enc.getText().equals("")){
                        de = enc.getText().toUpperCase();
                        en = dec.getText().toUpperCase();
                    }else {
                        java.util.Scanner in = new Scanner(f2);
                        while(in.hasNext()) en += in.next();
                        in = new Scanner(f1);
                        while(in.hasNext()) de += in.next();
                    }
                    de = de.trim();
                    en = en.trim();
                    int n = Integer.parseInt(sz1.getText());
                    int e[][] = new int[n][n];
                    int d[][] = new int[n][n];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            e[j][i] = en.charAt(i * n + j) - 'A';
                            d[j][i] = de.charAt(i * n + j) - 'A';
                        }
                    }
                    hillattack att = new hillattack();
                    output_area.setText(att.attack(e, d));
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t + "");
                }
                else if (E.getSource() == En_file ) {
                    f1 = new File(promptForFile());
                } else if (E.getSource() == De_file) {
                  f2 = new File(promptForFile());
                }

            } catch (Exception ex) {
            }
        }
    }//end of Lis
}
