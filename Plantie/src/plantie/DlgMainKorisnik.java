package plantie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DlgMainKorisnik extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNapiiPoruku;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgMainKorisnik dialog = new DlgMainKorisnik();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgMainKorisnik() {
		setBounds(100, 100, 620, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(215, 255, 215));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Plantie");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(234, 24, 94, 55);
		contentPanel.add(lblNewLabel);
		
		JLabel lblGetPlentyWith = new JLabel("get plenty with  ...  <3");
		lblGetPlentyWith.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblGetPlentyWith.setBounds(210, 56, 193, 55);
		contentPanel.add(lblGetPlentyWith);
		
		JLabel lblMojeBiljke = new JLabel("Naruči biljke:");
		lblMojeBiljke.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblMojeBiljke.setBounds(27, 84, 180, 55);
		contentPanel.add(lblMojeBiljke);
		
		JButton btnNewButton = new JButton("...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                DlgMojeBiljke dlg = new DlgMojeBiljke();
                dlg.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setBounds(27, 137, 89, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblZahtjevZaBrisanje = new JLabel("Zahtjev za brisanje/izmjene narudžbe, Vaš ID, ID narudzbe:");
		lblZahtjevZaBrisanje.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblZahtjevZaBrisanje.setBounds(42, 193, 411, 55);
		contentPanel.add(lblZahtjevZaBrisanje);
		
		txtNapiiPoruku = new JTextField();
		txtNapiiPoruku.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtNapiiPoruku.setText("Napiši poruku adminu...");
		txtNapiiPoruku.setColumns(10);
		txtNapiiPoruku.setBounds(105, 243, 223, 80);
		contentPanel.add(txtNapiiPoruku);
		
		JButton okButton = new JButton("OK");
		okButton.setVerticalAlignment(SwingConstants.TOP);
		okButton.setHorizontalAlignment(SwingConstants.LEFT);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String NapiiPoruku = txtNapiiPoruku.getText();
				try {						
				 	 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				 	 Connection conn = DriverManager.getConnection 
	("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");

				  String sql = "INSERT INTO ZahtjeviZaAdmina(Zahtjev) VALUES (?);";						 
				  PreparedStatement stmt = conn.prepareStatement(sql);
				  stmt.setString(1,NapiiPoruku);
	  			  stmt.execute();
								
				  conn.close();
							
				  txtNapiiPoruku.setText("");
				  
								
				} catch(Exception ex) {
				  JOptionPane.showMessageDialog(null, 
	   ex.getMessage(),"Greška", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		okButton.setActionCommand("OK");
		okButton.setBounds(338, 272, 47, 23);
		contentPanel.add(okButton);
		
		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgBiljke dlg = new DlgBiljke();
                dlg.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(54, 56, 89, 23);
		contentPanel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Pregled dostupnog bilja:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(23, 38, 154, 14);
		contentPanel.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Narudzbe:");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgIDKorisnika dlg= new DlgIDKorisnika();
				dlg.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(481, 141, 89, 23);
		contentPanel.add(btnNewButton_2);
	}
}
