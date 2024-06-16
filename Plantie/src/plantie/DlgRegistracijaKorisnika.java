package plantie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DlgRegistracijaKorisnika extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID_Korisnika;
	private JTextField textFieldIme_korisnika;
	private JTextField textFieldPrezime_korisnika;
	private JTextField textFieldEmail_korisnika;
	private JTextField textFieldLozinka_korisnika;
	private JTextField textFieldAdresa_korisnika;
	private JTextField textFieldKontakt_korisnika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRegistracijaKorisnika dialog = new DlgRegistracijaKorisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRegistracijaKorisnika () {
		setBounds(100, 100, 450, 414);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblID_korisnika = new JLabel("ID Korisnika");
			lblID_korisnika.setBounds(106, 37, 62, 14);
			contentPanel.add(lblID_korisnika);
		}
		{
			textFieldID_Korisnika = new JTextField();
			textFieldID_Korisnika.setBounds(207, 34, 134, 20);
			contentPanel.add(textFieldID_Korisnika);
			textFieldID_Korisnika.setColumns(10);
		}
		{
			JLabel lblNazivBiljke = new JLabel("Ime korisnika");
			lblNazivBiljke.setBounds(106, 75, 62, 14);
			contentPanel.add(lblNazivBiljke);
		}
		{
			textFieldIme_korisnika = new JTextField();
			textFieldIme_korisnika.setBounds(207, 72, 134, 20);
			contentPanel.add(textFieldIme_korisnika);
			textFieldIme_korisnika.setColumns(10);
		}
		
		JLabel lblID_korisnika = new JLabel("Prezime korisnika");
		lblID_korisnika.setBounds(87, 118, 81, 14);
		contentPanel.add(lblID_korisnika);
		
		textFieldPrezime_korisnika = new JTextField();
		textFieldPrezime_korisnika.setColumns(10);
		textFieldPrezime_korisnika.setBounds(207, 115, 134, 20);
		contentPanel.add(textFieldPrezime_korisnika);
		
		JLabel lblID_korisnika_1 = new JLabel("Email_korisnika");
		lblID_korisnika_1.setBounds(87, 157, 80, 14);
		contentPanel.add(lblID_korisnika_1);
		
		textFieldEmail_korisnika = new JTextField();
		textFieldEmail_korisnika.setColumns(10);
		textFieldEmail_korisnika.setBounds(207, 154, 134, 20);
		contentPanel.add(textFieldEmail_korisnika);
		
		JLabel lblID_korisnika_2 = new JLabel("Lozinka");
		lblID_korisnika_2.setBounds(106, 197, 62, 14);
		contentPanel.add(lblID_korisnika_2);
		
		textFieldLozinka_korisnika = new JTextField();
		textFieldLozinka_korisnika.setColumns(10);
		textFieldLozinka_korisnika.setBounds(207, 194, 134, 20);
		contentPanel.add(textFieldLozinka_korisnika);
		{
			textFieldAdresa_korisnika = new JTextField();
			textFieldAdresa_korisnika.setColumns(10);
			textFieldAdresa_korisnika.setBounds(207, 236, 134, 20);
			contentPanel.add(textFieldAdresa_korisnika);
		}
		{
			JLabel lblID_korisnika_3 = new JLabel("Adresa korisnika");
			lblID_korisnika_3.setBounds(87, 239, 81, 14);
			contentPanel.add(lblID_korisnika_3);
		}
		{
			textFieldKontakt_korisnika = new JTextField();
			textFieldKontakt_korisnika.setColumns(10);
			textFieldKontakt_korisnika.setBounds(207, 285, 134, 20);
			contentPanel.add(textFieldKontakt_korisnika);
		}
		{
			JLabel lblID_korisnika_3 = new JLabel("Kontakt korisnika");
			lblID_korisnika_3.setBounds(87, 288, 81, 14);
			contentPanel.add(lblID_korisnika_3);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ID_korisnika = textFieldID_Korisnika.getText();
						String Ime_korisnika = textFieldIme_korisnika.getText();
						String Prezime_korisnika = textFieldPrezime_korisnika.getText();
						String Email_korisnika = textFieldEmail_korisnika.getText();
						String Lozinka_korisnika = textFieldLozinka_korisnika.getText();
						String Adresa_korisnika = textFieldAdresa_korisnika.getText();
						String Kontakt_korisnika = textFieldKontakt_korisnika.getText();

						
						try {						
						 	 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						 	 Connection conn = DriverManager.getConnection 
			("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");

						  String sql = "INSERT INTO Korisnik VALUES (?,?,?,?, ?, ?, ?);";						 
						  PreparedStatement stmt = conn.prepareStatement(sql);
						  stmt.setString(1,ID_korisnika);
						  stmt.setString(2, Ime_korisnika);
						  stmt.setString(3, Prezime_korisnika);
						  stmt.setString(4, Email_korisnika);
						  stmt.setString(5, Lozinka_korisnika);
						  stmt.setString(6, Adresa_korisnika);
						  stmt.setString(7, Kontakt_korisnika);
			  			  stmt.execute();
										
						  conn.close();
									
						  textFieldID_Korisnika.setText("");
						  textFieldIme_korisnika.setText("");
						  textFieldPrezime_korisnika.setText("");
						  textFieldEmail_korisnika.setText("");
						  textFieldLozinka_korisnika.setText("");
						  textFieldAdresa_korisnika.setText("");			
						  textFieldKontakt_korisnika.setText("");
						  
										
						} catch(Exception ex) {
						  JOptionPane.showMessageDialog(null, 
			   ex.getMessage(),"Greška", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        dispose(); // Close the dialog
				    }
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				}
		}
	}
}
