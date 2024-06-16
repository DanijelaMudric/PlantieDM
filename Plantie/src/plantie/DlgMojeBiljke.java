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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class DlgMojeBiljke extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID_Korisnika;
	private JTextField textFieldNazivBiljke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgMojeBiljke dialog = new DlgMojeBiljke();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgMojeBiljke() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(198, 255, 198));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblID_korisnika = new JLabel("Vaš ID");
			lblID_korisnika.setBounds(52, 72, 74, 14);
			contentPanel.add(lblID_korisnika);
		}
		{
			textFieldID_Korisnika = new JTextField();
			textFieldID_Korisnika.setBounds(210, 69, 134, 20);
			contentPanel.add(textFieldID_Korisnika);
			textFieldID_Korisnika.setColumns(10);
		}
		{
			JLabel lblNazivBiljke = new JLabel("Naziv biljke");
			lblNazivBiljke.setBounds(52, 110, 101, 14);
			contentPanel.add(lblNazivBiljke);
		}
		{
			textFieldNazivBiljke = new JTextField();
			textFieldNazivBiljke.setBounds(210, 107, 134, 20);
			contentPanel.add(textFieldNazivBiljke);
			textFieldNazivBiljke.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Veličina biljke");
			lblNewLabel_2.setBounds(52, 145, 101, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Količina");
			lblNewLabel_3.setBounds(52, 182, 92, 14);
			contentPanel.add(lblNewLabel_3);
		}
		
		JComboBox comboBoxVelicinaBiljke = new JComboBox();
		comboBoxVelicinaBiljke.setModel(new DefaultComboBoxModel(new String[] {"Mala    ", "Srednja", "Velika   "}));
		comboBoxVelicinaBiljke.setBounds(210, 141, 111, 22);
		contentPanel.add(comboBoxVelicinaBiljke);
		
		JComboBox comboBoxKolicina = new JComboBox();
		comboBoxKolicina.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxKolicina.setBounds(210, 178, 74, 22);
		contentPanel.add(comboBoxKolicina);
		

	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 157, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
	
						String ID_korisnika = textFieldID_Korisnika.getText();
						String nazivBiljke = textFieldNazivBiljke.getText();
						String velicinaBiljke = (String) comboBoxVelicinaBiljke.getSelectedItem();
						String kolicina = (String) comboBoxKolicina.getSelectedItem();
						
						try {						
						 	 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						 	 Connection conn = DriverManager.getConnection 
			("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");

								String sql = "INSERT INTO Kosarica (nazivBiljke, velicinaBiljke, kolicina, ID_korisnika) VALUES (?, ?, ?, ?);";
								PreparedStatement stmt = conn.prepareStatement(sql);
								stmt.setString(1, nazivBiljke);
								stmt.setString(2, velicinaBiljke);
								stmt.setString(3, kolicina);
								stmt.setString(4, ID_korisnika);
								stmt.execute();
									
						  textFieldID_Korisnika.setText("");
						  textFieldNazivBiljke.setText("");
						  comboBoxVelicinaBiljke.setSelectedIndex(-1);
						  comboBoxKolicina.setSelectedIndex(-1);
						  
										
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
