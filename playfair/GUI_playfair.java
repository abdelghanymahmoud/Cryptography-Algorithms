package playfair;

import java.awt.Font;
import java.awt.Rectangle;

import java.awt.TextArea;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI_playfair extends JFrame {

    private JLabel jLabel1 = new JLabel();
    private TextArea input_area = new TextArea();
    private JButton encrypt_text = new JButton();
    private TextArea output_area = new TextArea();
    private JButton choose = new JButton();
    private JButton decrypt_text = new JButton();
    String[] comboTypes = {"Encrypt", "Decrypt"};
    private JButton attack = new JButton();
    private JButton save_file = new JButton();
    private JTextField key = new JTextField();
    private JLabel jLabel2 = new JLabel();
    private JButton decrypt_file = new JButton();
    private JButton encrypt_file = new JButton();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JButton attack_file = new JButton();
    private JLabel jLabel5 = new JLabel();
    private JTextField plain = new JTextField();
    private JTextField cipher = new JTextField();
    private JLabel jLabel6 = new JLabel();
    private JButton learn = new JButton();
    private JButton learn_file = new JButton();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();

    private JLabel jLabel7 = new JLabel();

    public GUI_playfair() {

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
            Logger.getLogger(GUI_playfair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI_playfair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI_playfair.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI_playfair.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUI_playfair frame = new GUI_playfair();

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

        jLabel1.setText("Play Fair");
        jLabel1.setBounds(new Rectangle(15, 0, 470, 50));
        jLabel1.setBackground(SystemColor.windowText);
        jLabel1.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.BLACK));
        jLabel1.setFont(new Font("Snap ITC", 0, 16));
//        jLabel1.setForeground(new Color(180, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        input_area.setBounds(new Rectangle(0, 100, 345, 65));
        input_area.setColumns(5);

        encrypt_text.setText("Encrypt");
        encrypt_text.setBounds(new Rectangle(370, 100, 125, 30));
        encrypt_text.setFont(new Font("Snap ITC", 0, 14));
        encrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        encrypt_text.setBackground(new Color(248, 248, 248));
        encrypt_text.addActionListener(new Lis());

        learn.setText("Learn");
        learn.setBounds(new Rectangle(350, 190, 125, 30));
        learn.setFont(new Font("Snap ITC", 0, 14));
        learn.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        learn.setBackground(new Color(248, 248, 248));
        learn.addActionListener(new Lis());

        output_area.setBounds(new Rectangle(0, 420, 495, 155));

        choose.setText("Choose File");
        choose.setBounds(new Rectangle(110, 320, 100, 40));
        choose.setFont(new Font("Snap ITC", 0, 14));
        choose.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        choose.setBackground(new Color(248, 248, 248));
        choose.addActionListener(new Lis());

        decrypt_text.setText("Decrypt");
        decrypt_text.setBounds(new Rectangle(370, 135, 125, 30));
        decrypt_text.setFont(new Font("Snap ITC", 0, 14));
        decrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        decrypt_text.setBackground(new Color(248, 248, 248));
        decrypt_text.addActionListener(new Lis());

        attack.setText("Attack");
        attack.setBounds(new Rectangle(350, 240, 125, 30));
        attack.setFont(new Font("Snap ITC", 0, 14));
        attack.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        attack.setBackground(new Color(248, 248, 248));
        attack.addActionListener(new Lis());

        save_file.setText("Save file");
        save_file.setBounds(new Rectangle(110, 375, 100, 40));
        save_file.setFont(new Font("Snap ITC", 0, 14));
        save_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        save_file.setBackground(new Color(248, 248, 248));
        save_file.addActionListener(new Lis());

        key.setBounds(new Rectangle(100, 55, 130, 35));

        jLabel2.setText("Output");
        jLabel2.setBounds(new Rectangle(15, 375, 80, 35));
        jLabel2.setFont(new Font("Snap ITC", 0, 14));

        jLabel7.setText("---------------------------File---------------------------");
        jLabel7.setBounds(new Rectangle(0, 280, 450, 35));
        jLabel7.setFont(new Font("Snap ITC", 0, 14));
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);

        decrypt_file.setText("Decrypt");
        decrypt_file.setText("Decrypt");
        decrypt_file.setBounds(new Rectangle(220, 375, 95, 40));
        decrypt_file.setFont(new Font("Snap ITC", 0, 14));
        decrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        decrypt_file.setBackground(new Color(248, 248, 248));
        decrypt_file.addActionListener(new Lis());

        encrypt_file.setText("Encrypt");
        encrypt_file.setBounds(new Rectangle(220, 320, 95, 40));
        encrypt_file.setFont(new Font("Snap ITC", 0, 14));
        encrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        encrypt_file.setBackground(new Color(248, 248, 248));
        encrypt_file.addActionListener(new Lis());

        jLabel3.setText("Input");
        jLabel3.setBounds(new Rectangle(15, 315, 80, 35));
        jLabel3.setFont(new Font("Snap ITC", 0, 14));
        jLabel4.setText("Key");
        jLabel4.setBounds(new Rectangle(10, 55, 80, 35));
        jLabel4.setFont(new Font("Snap ITC", 0, 14));

        attack_file.setText("Attack_file");
        attack_file.setBounds(new Rectangle(355, 380, 125, 30));
        attack_file.setFont(new Font("Snap ITC", 0, 14));
        attack_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        attack_file.setBackground(new Color(248, 248, 248));
        attack_file.addActionListener(new Lis());

        learn_file.setText("learn_file");
        learn_file.setBounds(new Rectangle(355, 320, 125, 30));
        learn_file.setFont(new Font("Snap ITC", 0, 14));
        learn_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        learn_file.setBackground(new Color(248, 248, 248));
        learn_file.addActionListener(new Lis());

        jLabel5.setText("plain text");
        jLabel5.setBounds(new Rectangle(5, 190, 90, 35));
        jLabel5.setFont(new Font("Snap ITC", 0, 14));
        plain.setBounds(new Rectangle(150, 185, 130, 35));
        cipher.setBounds(new Rectangle(150, 235, 130, 35));
        jLabel6.setText("cipher text");
        jLabel6.setBounds(new Rectangle(5, 230, 95, 35));
        jLabel6.setFont(new Font("Snap ITC", 0, 14));

        jLabel9.setText("Time : ");
        jLabel9.setBounds(new Rectangle(300, 60, 80, 35));
        jLabel9.setFont(new Font("Snap ITC", 0, 14));

        jLabel8.setText(" ");
        jLabel8.setBounds(new Rectangle(360, 60, 80, 35));
        jLabel8.setFont(new Font("Snap ITC", 0, 14));
        jLabel8.setForeground(Color.red);

        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(cipher, null);
        this.getContentPane().add(plain, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(attack_file, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(encrypt_file, null);
        this.getContentPane().add(decrypt_file, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(key, null);
        this.getContentPane().add(save_file, null);
        this.getContentPane().add(attack, null);
        this.getContentPane().add(decrypt_text, null);
        this.getContentPane().add(choose, null);
        this.getContentPane().add(output_area, null);
        this.getContentPane().add(encrypt_text, null);
        this.getContentPane().add(input_area, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(learn, null);
        this.getContentPane().add(learn_file, null);

        this.getContentPane().add(jLabel7, null);

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
    playfairkey ob;
    playfair ob1;
    playfairattack ob2 = new playfairattack();
    String s, s1, s2, s3;
    File f1, f2;
    long t, t1,t2;

    class Lis implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                ob = new playfairkey(key.getText());
                ob1 = new playfair(ob);

//                
                if (e.getSource() == encrypt_text) {
                    t1 = System.currentTimeMillis();
                    s = input_area.getText().toUpperCase();
                    output_area.setText(ob1.encrypt(s));
                    t2 = System.currentTimeMillis();
                    t = t2 - t1;
                    jLabel8.setText(t + "");
                } else if (e.getSource() == decrypt_text) {
                    t1 = System.currentTimeMillis();

                    s = input_area.getText().toUpperCase();
                    output_area.setText(ob1.decrypt(s));
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t + "");
                } else if (e.getSource() == learn) {
                    ob2.learn(plain.getText(), cipher.getText());
                    //System.out.println(plain.getText()+" "+cipher.getText());
                } else if (e.getSource() == attack) {
                    t1 = System.currentTimeMillis();

                    s3 = input_area.getText();
                    //System.out.println(ob2.attack(s3));
                    output_area.setText(ob2.attack(s3));
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t + "");
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
                    jLabel8.setText(t + "");
                } else if (e.getSource() == decrypt_file) {
                    t1 = System.currentTimeMillis();

                    s1 = ob1.decrypt(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t + "");
                } else if (e.getSource() == learn_file) {
                    ob2.learn(f1);
                } else if (e.getSource() == attack_file) {
                    t1 = System.currentTimeMillis();

                    s1 = ob2.attack(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                    t = System.currentTimeMillis() - t1;
                    jLabel8.setText(t + "");
                }
            } catch (Exception ex) {
            }
        }
    }//end of Lis
}
