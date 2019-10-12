
package onetimeattack;

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

public class GUI_one extends JFrame {

    private JLabel jLabel1 = new JLabel();
    private TextArea input_area = new TextArea();
    private JButton encrypt_text = new JButton();
    private TextArea output_area = new TextArea();
    private JButton choose = new JButton();
    private JButton decrypt_text = new JButton();
    String[] comboTypes = { "Encrypt", "Decrypt" };
    private JButton attack = new JButton();
    private JButton save_file = new JButton();
    private JLabel jLabel2 = new JLabel();
    private JButton decrypt_file = new JButton();
    private JButton encrypt_file = new JButton();
    private JLabel jLabel3 = new JLabel();
    private JButton attack_file = new JButton();
    private JLabel jLabel5 = new JLabel();
    private JTextField plain = new JTextField();
    private JButton get_key_text = new JButton();
    private JButton get_key_file = new JButton();

    public GUI_one() {

        try {
            jbInit();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info :
                 UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_one.class.getName()).log(Level.SEVERE, null,
                                                          ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI_one.class.getName()).log(Level.SEVERE, null,
                                                          ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI_one.class.getName()).log(Level.SEVERE, null,
                                                          ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI_one.class.getName()).log(Level.SEVERE, null,
                                                          ex);
        }

        GUI_one frame = new GUI_one();

        frame.setTitle("Playfair Cipher");
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

        jLabel1.setText("One Time Pad");
        jLabel1.setBounds(new Rectangle(15, 0, 470, 50));
        jLabel1.setBackground(SystemColor.windowText);
        jLabel1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText,
                                                         2));
        jLabel1.setFont(new Font("Snap ITC", 0, 16));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        input_area.setBounds(new Rectangle(0, 65, 345, 100));
        input_area.setColumns(5);

        encrypt_text.setText("Encrypt");
        encrypt_text.setBounds(new Rectangle(370, 100, 125, 30));
        encrypt_text.setFont(new Font("Snap ITC", 0, 14));
        encrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                        250,
                                                                        250),
                                                              3));
        encrypt_text.setBackground(new Color(248, 248, 248));
        encrypt_text.addActionListener(new Lis());

        output_area.setBounds(new Rectangle(0, 350, 495, 225));

        choose.setText("Choose File");
        choose.setBounds(new Rectangle(110, 245, 100, 40));
        choose.setFont(new Font("Snap ITC", 0, 14));
        choose.setBorder(BorderFactory.createLineBorder(new Color(250, 250,
                                                                  250), 3));
        choose.setBackground(new Color(248, 248, 248));
        choose.addActionListener(new Lis());

        decrypt_text.setText("Decrypt");
        decrypt_text.setBounds(new Rectangle(370, 135, 125, 30));
        decrypt_text.setFont(new Font("Snap ITC", 0, 14));
        decrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                        250,
                                                                        250),
                                                              3));
        decrypt_text.setBackground(new Color(248, 248, 248));
        decrypt_text.addActionListener(new Lis());

        attack.setText("Attack");
        attack.setBounds(new Rectangle(355, 190, 125, 30));
        attack.setFont(new Font("Snap ITC", 0, 14));
        attack.setBorder(BorderFactory.createLineBorder(new Color(250, 250,
                                                                  250), 3));
        attack.setBackground(new Color(248, 248, 248));
        attack.addActionListener(new Lis());

        save_file.setText("Save file");
        save_file.setBounds(new Rectangle(110, 300, 100, 40));
        save_file.setFont(new Font("Snap ITC", 0, 14));
        save_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250,
                                                                     250), 3));
        save_file.setBackground(new Color(248, 248, 248));
        save_file.addActionListener(new Lis());


        jLabel2.setText("Output");
        jLabel2.setBounds(new Rectangle(15, 300, 80, 35));
        jLabel2.setFont(new Font("Snap ITC", 0, 14));

        decrypt_file.setText("Decrypt");
        decrypt_file.setText("Decrypt");
        decrypt_file.setBounds(new Rectangle(220, 300, 95, 40));
        decrypt_file.setFont(new Font("Snap ITC", 0, 14));
        decrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                        250,
                                                                        250),
                                                              3));
        decrypt_file.setBackground(new Color(248, 248, 248));
        decrypt_file.addActionListener(new Lis());

        encrypt_file.setText("Encrypt");
        encrypt_file.setBounds(new Rectangle(220, 245, 95, 40));
        encrypt_file.setFont(new Font("Snap ITC", 0, 14));
        encrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                        250,
                                                                        250),
                                                              3));
        encrypt_file.setBackground(new Color(248, 248, 248));
        encrypt_file.addActionListener(new Lis());

        jLabel3.setText("Input");
        jLabel3.setBounds(new Rectangle(15, 240, 80, 35));
        jLabel3.setFont(new Font("Snap ITC", 0, 14));

        attack_file.setText("Attack");
        attack_file.setBounds(new Rectangle(355, 305, 125, 30));
        attack_file.setFont(new Font("Snap ITC", 0, 14));
        attack_file.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                       250,
                                                                       250),
                                                             3));
        attack_file.setBackground(new Color(248, 248, 248));
        attack_file.addActionListener(new Lis());

        jLabel5.setText("Attack Word :");
        jLabel5.setBounds(new Rectangle(5, 190, 120, 35));
        jLabel5.setFont(new Font("Snap ITC", 0, 14));
        plain.setBounds(new Rectangle(125, 185, 220, 35));
        get_key_text.setText("Get Key");
        get_key_text.setBounds(new Rectangle(370, 65, 125, 30));
        get_key_text.setFont(new Font("Snap ITC", 0, 14));
        get_key_text.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                         250,
                                                                         250),
                                                               3));
        get_key_text.setBackground(new Color(248, 248, 248));

        get_key_text.addActionListener(new Lis());
        get_key_file.setText("Get Key");
        get_key_file.setBounds(new Rectangle(355, 250, 125, 30));
        get_key_file.setFont(new Font("Snap ITC", 0, 14));
        get_key_file.setBorder(BorderFactory.createLineBorder(new Color(250,
                                                                         250,
                                                                         250),
                                                               3));
        get_key_file.setBackground(new Color(248, 248, 248));
        get_key_file.addActionListener(new Lis());

        this.getContentPane().add(get_key_file, null);
        this.getContentPane().add(get_key_text, null);
        this.getContentPane().add(plain, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(attack_file, null);
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
    onetimekey ob;
    onetimeEncrypt ob1;
    onetimeDecrypt ob2;
    onetimeattack ob3;


    String s, s1, s2, s3;
    File f1, f2;
    boolean in = true;

    class Lis implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {

                ob1 = new onetimeEncrypt(ob);
                ob2 = new onetimeDecrypt(ob);
                ob3 = new onetimeattack();
                //

                if (e.getSource() == encrypt_text) {
                    s = input_area.getText().toUpperCase();
                    output_area.setText(ob1.encrypt(s));
                } else if (e.getSource() == decrypt_text) {
                    s = input_area.getText().toUpperCase();
                    output_area.setText(ob2.decrypt(s));
                } else if (e.getSource() == get_key_text) {
                    ob = new onetimekey(input_area.getText().length());
                } else if (e.getSource() == attack) {
                    output_area.setText(ob3.attack(plain.getText()));
                } else if (e.getSource() == choose) {
                    f1 = new File(promptForFile());
                } else if (e.getSource() == save_file) {
                    s2 = saveForFile();
                } else if (e.getSource() == encrypt_file) {
                    s1 = ob1.encrypt(f1);
                    f2 = new File(s2);
                    f2.createNewFile();
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                } else if (e.getSource() == get_key_file) {
                        String res = "";
                        Scanner in = new Scanner(f1);
                        while(in.hasNext()){
                            res += in.nextLine();  
                        }
                    ob = new onetimekey(res.trim().length());
                        System.out.print(res.trim().length());
                } else if (e.getSource() == decrypt_file) {
                    s1 = ob2.decrypt(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                } else if (e.getSource() == attack_file) {
                    s1 = ob3.attack(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                }
            } catch (Exception ex) {
            }
        }
    } //end of Lis
}
