package plantie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main {

    private JFrame frame;

    private JTextField textFieldID;
    private JPasswordField passwordField;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    private void provjeriAdmina(String ID_Admina) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");
            String sql = "SELECT * FROM Admin WHERE ID_Admina=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ID_Admina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	DlgMainAdmin dlg = new DlgMainAdmin();
                dlg.setVisible(true);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ne postoji admin u bazi", "Greška", JOptionPane.ERROR_MESSAGE);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void provjeriKorisnika(String korime, String lozinka) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");
            String sql = "SELECT * FROM Korisnik WHERE ID_korisnika=? AND Lozinka_korisnika=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, korime);
            stmt.setString(2, lozinka);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DlgMainKorisnik dlg = new DlgMainKorisnik();
                dlg.setVisible(true);

                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ne postoji korisnik u bazi", "Greška", JOptionPane.ERROR_MESSAGE);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setEnabled(false);
        frame.getContentPane().setBackground(new Color(155, 213, 164));
        frame.setBounds(100, 100, 958, 443);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Plantie");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 33));
        lblNewLabel.setBounds(418, 11, 111, 79);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Prijavite se kao korisnik:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(121, 68, 171, 14);
        frame.getContentPane().add(lblNewLabel_2);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 98, 49));
        panel.setBounds(40, 93, 302, 155);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblIDKorisnika = new JLabel("ID Korisnika:");
        lblIDKorisnika.setForeground(new Color(255, 255, 255));
        lblIDKorisnika.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblIDKorisnika.setBounds(23, 31, 86, 14);
        panel.add(lblIDKorisnika);
        
        textFieldID = new JTextField();
        textFieldID.setBounds(119, 28, 153, 20);
        panel.add(textFieldID);
        textFieldID.setColumns(10);
        
        JLabel lblLozinka = new JLabel("Lozinka:");
        lblLozinka.setForeground(new Color(255, 255, 255));
        lblLozinka.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblLozinka.setBounds(47, 72, 86, 14);
        panel.add(lblLozinka);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(119, 69, 153, 20);
        panel.add(passwordField);
        
        JButton btnPrijavaKorisnika = new JButton("Prijava");
        btnPrijavaKorisnika.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String korime = textFieldID.getText();
                String lozinka = String.valueOf(passwordField.getPassword());
                provjeriKorisnika(korime, lozinka);
  
            }
        });
        btnPrijavaKorisnika.setBounds(119, 121, 89, 23);
        panel.add(btnPrijavaKorisnika);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(40, 98, 49));
        panel_1.setBounds(619, 93, 302, 111);
        frame.getContentPane().add(panel_1);
        
        JLabel lblIdAdmina = new JLabel("ID Admina:");
        lblIdAdmina.setForeground(Color.WHITE);
        lblIdAdmina.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblIdAdmina.setBounds(21, 28, 86, 14);
        panel_1.add(lblIdAdmina);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(117, 25, 153, 20);
        panel_1.add(textField);
        
        JButton btnPrijavaAdmina = new JButton("Prijava");
        btnPrijavaAdmina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID_Admina = textField.getText();
                provjeriAdmina(ID_Admina);
 
            }
        });
        btnPrijavaAdmina.setBounds(97, 69, 89, 23);
        panel_1.add(btnPrijavaAdmina);
        
        JLabel lblNewLabel_2_1 = new JLabel("Nemas racun? Registriraj se :)...");
        lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_2_1.setBounds(376, 310, 213, 20);
        frame.getContentPane().add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Prijavite se kao admin:");
        lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_2_2.setBounds(698, 68, 171, 14);
        frame.getContentPane().add(lblNewLabel_2_2);
        
        JButton btnNewButton = new JButton("Registriraj se:");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgRegistracijaKorisnika dlg = new DlgRegistracijaKorisnika();
                dlg.setVisible(true);
            }
        });
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnNewButton.setBackground(new Color(30, 115, 57));
        btnNewButton.setBounds(408, 341, 133, 40);
        frame.getContentPane().add(btnNewButton);
    }   
}
