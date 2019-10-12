package affine;


import java.awt.Rectangle;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.JTextField;

public class GUI_affine extends JFrame {

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
    private JTextField key1 = new JTextField();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();

    public GUI_affine() {

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
            Logger.getLogger(GUI_affine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI_affine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI_affine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI_affine.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUI_affine frame = new GUI_affine();

        frame.setTitle("Affine Cipher");
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

        jLabel1.setText("Affine");
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

        attack.setText("Attack");
        attack.setBounds(new Rectangle(365, 365, 125, 40));
        attack.setFont(new Font("Snap ITC", 0, 14));
        attack.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        attack.setBackground(new Color(248, 248, 248));
        attack.addActionListener(new Lis());

        save_file.setText("Save file");
        save_file.setBounds(new Rectangle(140, 300, 125, 40));
        save_file.setFont(new Font("Snap ITC", 0, 14));
        save_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        save_file.setBackground(new Color(248, 248, 248));
        save_file.addActionListener(new Lis());

        key.setBounds(new Rectangle(100, 55, 35, 35));
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

        attack_file.setText("Attack");
        attack_file.setBounds(new Rectangle(370, 170, 125, 30));
        attack_file.setFont(new Font("Snap ITC", 0, 14));
        attack_file.setBorder(BorderFactory.createLineBorder(new Color(250, 250, 250), 3));
        attack_file.setBackground(new Color(248, 248, 248));
        attack_file.addActionListener(new Lis());

        key1.setBounds(new Rectangle(145, 55, 35, 35));
        key1.setText("1");
        
        jLabel5.setText("Time : ");
        jLabel5.setBounds(new Rectangle(20, 380, 80, 35));
        jLabel5.setFont(new Font("Snap ITC", 0, 14));
        
        jLabel6.setText(" ");
        jLabel6.setBounds(new Rectangle(80, 380, 80, 35));
        jLabel6.setFont(new Font("Snap ITC", 0, 14)); 
        jLabel6.setForeground(Color.red);

        this.getContentPane().add(key1, null);
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
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel6, null);


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
    affinekey ob;
    affine ob1;
    affineattack ob2;
    String s, s1, s2;
    File f1, f2;
    long t,t1;
    class Lis implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            try {
                ob = new affinekey(Integer.parseInt(key.getText()),Integer.parseInt(key1.getText()));
                ob1 = new affine(ob);
                ob2 = new affineattack();
                if (e.getSource() == encrypt_text) {
                    t1 = System.currentTimeMillis();
                    s = input_area.getText();
                    output_area.setText(ob1.encrypt(s));
                    t = System.currentTimeMillis() - t1;
                    jLabel6.setText(t+"");
                } else if (e.getSource() == decrypt_text) {
                    t1 = System.currentTimeMillis();
                    s = input_area.getText();
                    output_area.setText(ob1.decrypt(s));
                     t = System.currentTimeMillis() - t1;
                    jLabel6.setText(t+"");
                } else if (e.getSource() == attack_file) {
                    t1 = System.currentTimeMillis();
                    s = input_area.getText();
                    output_area.setText(ob2.attack(s));
                     t = System.currentTimeMillis() - t1;
                    jLabel6.setText(t+"");
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
                    jLabel6.setText(t+"");
                } else if (e.getSource() == decrypt_file) {
                    t1 = System.currentTimeMillis();
                    s1 = ob1.decrypt(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                    t = System.currentTimeMillis() - t1;
                    jLabel6.setText(t+"");
                } else if (e.getSource() == attack) {
                    t1 = System.currentTimeMillis();
                    s1 = ob2.attack(f1);
                    f2 = new File(s2);
                    PrintWriter writer = new PrintWriter(f2);
                    writer.println(s1);
                    writer.close();
                    t = System.currentTimeMillis() - t1;
                    jLabel6.setText(t+"");
                }
            } catch (Exception ex) {
            }
        }
    }//end of Lis
}
