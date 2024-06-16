package plantie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgIDKorisnika extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    JTextArea textAreaKosarica;
    JTextField textFieldIDKorisnika;

    public static void main(String[] args) {
        try {
            DlgIDKorisnika dialog = new DlgIDKorisnika();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgIDKorisnika() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 40, 414, 122);
            contentPanel.add(scrollPane);
            {
                textAreaKosarica = new JTextArea();
                scrollPane.setViewportView(textAreaKosarica);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
        
        textFieldIDKorisnika = new JTextField();
        textFieldIDKorisnika.setBounds(10, 11, 200, 20);
        contentPanel.add(textFieldIDKorisnika);
        textFieldIDKorisnika.setColumns(10);
        

        JButton btnFetchData = new JButton("Vas ID:");
        btnFetchData.setBounds(220, 10, 100, 23);
        btnFetchData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectKosarica();
            }
        });
        contentPanel.add(btnFetchData);
    }

    private void selectKosarica() {
        try {
            String idKorisnika = textFieldIDKorisnika.getText();
            if (idKorisnika.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Molim Vas da unesete ID: ", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");
            String sql = "SELECT * FROM Kosarica WHERE ID_korisnika=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idKorisnika);
            ResultSet rs = pstmt.executeQuery();
            StringBuilder tekst = new StringBuilder();
            while (rs.next()) {
                tekst.append(rs.getString("nazivBiljke")).append(", ")
                .append(rs.getString("velicinaBiljke")).append(", ")
                .append(rs.getString("kolicina")).append(", ")
                .append("ID Kosarice: ")
                     .append(rs.getString("ID_Kosarice")).append("\n");
            }
            textAreaKosarica.setText(tekst.toString());
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
