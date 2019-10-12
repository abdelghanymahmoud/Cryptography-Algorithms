package AES;

import LAB4.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

public class GUI_AES extends JFrame {

    private JPanel text_panel = new JPanel();
    private JTextField key = new JTextField();
    private JTextArea in = new JTextArea();
    private JTextArea out = new JTextArea();
    private JButton en_text = new JButton();
    private JButton de_text = new JButton();
    private JPanel file_panel = new JPanel();
    private JButton choose = new JButton();
    private JButton de_file = new JButton();
    private JButton en_file = new JButton();
    private JButton save = new JButton();

    public GUI_AES() {
        try {
            jbInit();
        } catch (Exception e) {
        }
    }

    private void jbInit() throws Exception {
        setLayout(null);
        this.setSize(new Dimension(347, 455));
        this.setBackground(new Color(246, 246, 246));
        text_panel.setBounds(new Rectangle(0, 0, 350, 325));
        text_panel.setBorder(BorderFactory.createTitledBorder("Text"));
        text_panel.setBackground(SystemColor.window);
        text_panel.setLayout(null);

        key.setBounds(new Rectangle(5, 30, 200, 60));
        key.setBorder(BorderFactory.createTitledBorder("Key"));

        in.setBounds(new Rectangle(5, 95, 200, 105));
        in.setBorder(BorderFactory.createTitledBorder("Input Text"));
        in.setLineWrap(true);
        in.setAutoscrolls(true);

        out.setBounds(new Rectangle(5, 205, 200, 105));
        out.setBorder(BorderFactory.createTitledBorder("Output Text"));
        out.setLineWrap(true);
        out.setAutoscrolls(true);

        en_text.setText("Encrypt");
        en_text.setBounds(new Rectangle(215, 120, 115, 25));
        en_text.setToolTipText("Encrypt Button");
        de_text.setText("Decrypt");
        de_text.setBounds(new Rectangle(215, 165, 115, 25));
        de_text.setToolTipText("Decrypt Button");

        file_panel.setBounds(new Rectangle(0, 325, 350, 105));
        file_panel.setBackground(SystemColor.window);
        file_panel.setLayout(null);
        file_panel.setBorder(BorderFactory.createTitledBorder("File"));

        choose.setText("Choose");
        choose.setBounds(new Rectangle(40, 30, 115, 25));

        de_file.setText("Decrypt");
        de_file.setBounds(new Rectangle(205, 60, 115, 25));
        de_file.setToolTipText("Decrypt Button");

        en_file.setText("Encrypt");
        en_file.setBounds(new Rectangle(205, 30, 115, 25));

        save.setText("Save");
        save.setBounds(new Rectangle(40, 60, 115, 25));

        en_file.addActionListener(new Lis());
        en_text.addActionListener(new Lis());
        de_file.addActionListener(new Lis());
        de_text.addActionListener(new Lis());
        save.addActionListener(new Lis());
        choose.addActionListener(new Lis());

        text_panel.add(de_text, null);
        text_panel.add(en_text, null);
        text_panel.add(out, null);
        text_panel.add(in, null);
        text_panel.add(key, null);
        file_panel.add(save, null);
        file_panel.add(en_file, null);
        file_panel.add(choose, null);
        file_panel.add(de_file, null);

        this.getContentPane().add(file_panel, null);
        this.getContentPane().add(text_panel, null);

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI_AES.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUI_AES frame = new GUI_AES();

        frame.setTitle("Advanced Encryption Standard");
        frame.setSize(355, 460);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

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
        String res = "";
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            res = fileToSave.getAbsolutePath();
        }
        return res;
    }

    AESkey ob;
    AESencrypt ob1;
    AESdecrypt ob2;

    String s, s1, s2, s3;
    File f1, f2;

    class Lis implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (key.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter a Key", "Error", JOptionPane.ERROR_MESSAGE);
                }
                ob = new AESkey(key.getText());
                ob1 = new AESencrypt(ob);
                ob2 = new AESdecrypt(ob);
                if (e.getSource() == en_text) {
                    s = in.getText();
                    out.setText(ob1.encrypt(s));
                } else if (e.getSource() == de_text) {
                    s = in.getText();
                    out.setText(ob2.decrypt(s));
                } else if (e.getSource() == en_file) {
                    s1 = ob1.encrypt(f1);
                    f2 = new File(s2);
                    f2.createNewFile();
                    try (PrintWriter writer = new PrintWriter(f2)) {
                        writer.println(s1);
                    }
                } else if (e.getSource() == de_file) {
                    s1 = ob2.decrypt(f1);
                    f2 = new File(s2);
                    try (PrintWriter writer = new PrintWriter(f2)) {
                        writer.println(s1);
                    }
                } else if (e.getSource() == choose) {
                    f1 = new File(promptForFile());
                } else if (e.getSource() == save) {
                    s2 = saveForFile();
                }
            } catch (Exception ex) {System.out.println("lsdkafkldsaf");
            }
        }
    }
}
