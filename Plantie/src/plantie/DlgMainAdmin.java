package plantie;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

public class DlgMainAdmin {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DlgMainAdmin window = new DlgMainAdmin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DlgMainAdmin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 128, 0));
        frame.setBackground(new Color(0, 102, 51));
        frame.setBounds(100, 100, 615, 372);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnKosarica = new JButton("Košarica");
        btnKosarica.setForeground(new Color(255, 255, 255));
        btnKosarica.setBackground(new Color(0, 210, 0));
        btnKosarica.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        btnKosarica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgKosarica dlg = new DlgKosarica();
                dlg.setVisible(true);
                
            }
        });
        btnKosarica.setBounds(315, 107, 149, 58);
        frame.getContentPane().add(btnKosarica);
        
        
        JLabel lblNewLabel = new JLabel("Što želite napraviti?");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
        lblNewLabel.setBounds(202, 39, 208, 27);
        frame.getContentPane().add(lblNewLabel);
        
        JButton btnIzbrisiNarudzbu = new JButton("Izbriši narudžbu");
        btnIzbrisiNarudzbu.setForeground(new Color(255, 255, 255));
        btnIzbrisiNarudzbu.setBackground(new Color(0, 210, 0));
        btnIzbrisiNarudzbu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgIzbrisiNarudzbu dlg = new DlgIzbrisiNarudzbu();
                dlg.setVisible(true);
            }
        });
        btnIzbrisiNarudzbu.setBounds(71, 150, 149, 58);
        frame.getContentPane().add(btnIzbrisiNarudzbu);
        
        JButton btnZahtjeviKorisnika = new JButton("Zahtjevi korisnika");
        btnZahtjeviKorisnika.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                DlgZahtjevi dlg = new DlgZahtjevi();
                dlg.setVisible(true);
        	}
        });
        btnZahtjeviKorisnika.setForeground(Color.WHITE);
        btnZahtjeviKorisnika.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        btnZahtjeviKorisnika.setBackground(new Color(0, 210, 0));
        btnZahtjeviKorisnika.setBounds(315, 228, 149, 58);
        frame.getContentPane().add(btnZahtjeviKorisnika);

    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
