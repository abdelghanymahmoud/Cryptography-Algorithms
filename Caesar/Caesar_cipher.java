package caesar;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

public class Caesar_cipher extends JFrame {

    private JLabel jLabel1 = new JLabel();
    private TextArea input_area = new TextArea();
    private JButton encrypt_text = new JButton();
    private TextArea output_area = new TextArea();
    private JButton choose = new JButton();
    private JButton decrypt_text = new JButton();
    String[] comboTypes = {"Encrypt", "Decrypt"};
    private JButton b_force = new JButton();
    private JButton save_file = new JButton();
    private JTextField key = new JTextField();
    private JLabel jLabel2 = new JLabel();
    private JButton decrypt_file = new JButton();
    private JButton encrypt_file = new JButton();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JButton b_force1 = new JButton();

    public Caesar_cipher() {

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
            Logger.getLogger(Caesar_cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Caesar_cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Caesar_cipher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Caesar_cipher.class.getName()).log(Level.SEVERE, null, ex);
        }

        Caesar_cipher frame = new Caesar_cipher();

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

        jLabel1.setText("Caesar Cipher");
        jLabel1.setBounds(new Rectangle(15, 0, 470, 50));
        jLabel1.setBackground(SystemColor.windowText);
        jLabel1.setBorder(BorderFactory.createLineBorder(SystemColor.windowText,
                2));
        jLabel1.setFont(new Font("Snap ITC", 0, 16));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        input_area.setBounds(new Rectangle(0, 100, 260, 115));
        input_area.setColumns(5);

        encrypt_text.setText("Encrypt");
        encrypt_text.setBounds(new Rectangle(370, 100, 125, 30));
        encrypt_text.setFont(new Font("Snap ITC", 0, 14));
        encrypt_text.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        encrypt_text.setBackground(new Color(248, 248, 248));
        encrypt_text.addActionListener(new Lis());

        output_area.setBounds(new Rectangle(0, 420, 500, 160));

        choose.setText("Choose File");
        choose.setBounds(new Rectangle(140, 245, 125, 40));
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

        b_force.setText("B_force");
        b_force.setBounds(new Rectangle(365, 365, 125, 40));
        b_force.setFont(new Font("Snap ITC", 0, 14));
        b_force.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        b_force.setBackground(new Color(248, 248, 248));
        b_force.addActionListener(new Lis());

        save_file.setText("Save file");
        save_file.setBounds(new Rectangle(140, 300, 125, 40));
        save_file.setFont(new Font("Snap ITC", 0, 14));
        save_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        save_file.setBackground(new Color(248, 248, 248));
        save_file.addActionListener(new Lis());

        key.setBounds(new Rectangle(125, 55, 80, 35));
        key.setText("1");

        jLabel2.setText("Output");
        jLabel2.setBounds(new Rectangle(20, 305, 80, 35));
        jLabel2.setFont(new Font("Snap ITC", 0, 14));

        decrypt_file.setText("Decrypt");
        decrypt_file.setText("Decrypt");
        decrypt_file.setBounds(new Rectangle(365, 300, 125, 40));
        decrypt_file.setFont(new Font("Snap ITC", 0, 14));
        decrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        decrypt_file.setBackground(new Color(248, 248, 248));
        decrypt_file.addActionListener(new Lis());

        encrypt_file.setText("Encrypt");
        encrypt_file.setBounds(new Rectangle(365, 245, 125, 40));
        encrypt_file.setFont(new Font("Snap ITC", 0, 14));
        encrypt_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        encrypt_file.setBackground(new Color(248, 248, 248));
        encrypt_file.addActionListener(new Lis());

        jLabel3.setText("Input");
        jLabel3.setBounds(new Rectangle(20, 245, 80, 35));
        jLabel3.setFont(new Font("Snap ITC", 0, 14));
        jLabel4.setText("Key");
        jLabel4.setBounds(new Rectangle(10, 55, 80, 35));
        jLabel4.setFont(new Font("Snap ITC", 0, 14));

        b_force1.setText("B_force");
        b_force1.setBounds(new Rectangle(370, 170, 125, 30));
        b_force1.setFont(new Font("Snap ITC", 0, 14));
        b_force1.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        b_force1.setBackground(new Color(248, 248, 248));
        b_force1.addActionListener(new Lis());

        this.getContentPane().add(b_force1, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(encrypt_file, null);
        this.getContentPane().add(decrypt_file, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(key, null);
        this.getContentPane().add(save_file, null);
        this.getContentPane().add(b_force, null);
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
    Caesar ob;
    String s, s1, s2;
    File f1, f2;
    class Lis implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            try {
                ob = new Caesar(Integer.parseInt(key.getText()));
                if (e.getSource() == encrypt_text) {
                    s = input_area.getText();
                    output_area.setText(ob.encrept(s));
                } else if (e.getSource() == decrypt_text) {
                    s = input_area.getText();
                    output_area.setText(ob.decrept(s));
                } else if (e.getSource() == b_force1) {
                    s = input_area.getText();
                    output_area.setText(ob.decreptWithoutKey(s));
                } else if (e.getSource() == choose) {
                    f1 = new File(promptForFile());
                } else if (e.getSource() == save_file) {
                    s2 = saveForFile();
                } else if (e.getSource() == encrypt_file) {
                    s1 = ob.encrept(f1);
                    f2 = new File(s2);
                    f2.createNewFile();
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                } else if (e.getSource() == decrypt_file) {
                    s1 = ob.decrept(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                } else if (e.getSource() == b_force) {
                    s1 = ob.decreptWithoutKey(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                }
            } catch (Exception ex) {
            }
        }
    }//end of Lis
}
