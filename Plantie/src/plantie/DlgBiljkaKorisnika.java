package plantie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class DlgBiljkaKorisnika extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldKolicina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgBiljkaKorisnika dialog = new DlgBiljkaKorisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgBiljkaKorisnika() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblOdabrana_biljka = new JLabel("Odabrana biljka");
			lblOdabrana_biljka.setBounds(31, 19, 95, 31);
			contentPanel.add(lblOdabrana_biljka);
		}
		
		JComboBox comboBoxOdabrana_biljka = new JComboBox();
		comboBoxOdabrana_biljka.setModel(new DefaultComboBoxModel(new String[] {"Orhideja", "Ljiljan", "Begonija", "Ruza", "Lavanda", "Kaktus", "Azaleja", "Fikus", "Paprat", "Gardenija", "Kalanhoa", "Aloe vera", "Jasmin"}));
		comboBoxOdabrana_biljka.setBounds(169, 23, 95, 22);
		contentPanel.add(comboBoxOdabrana_biljka);
		
		JLabel lblKolicina = new JLabel("Količina");
		lblKolicina.setBounds(31, 78, 73, 22);
		contentPanel.add(lblKolicina);
		
		textFieldKolicina = new JTextField();
		textFieldKolicina.setBounds(169, 79, 99, 20);
		contentPanel.add(textFieldKolicina);
		textFieldKolicina.setColumns(10);
		
		JLabel lblKomentar = new JLabel("Komentar");
		lblKomentar.setBounds(31, 138, 73, 14);
		contentPanel.add(lblKomentar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 175, 2, 2);
		contentPanel.add(scrollPane);
		
		JTextArea textAreaKomentar = new JTextArea();
		textAreaKomentar.setBounds(141, 124, 162, 93);
		contentPanel.add(textAreaKomentar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String odabranabiljka = (String) comboBoxOdabrana_biljka.getSelectedItem();
						String kolicina = textFieldKolicina.getText();
						String komentar = textAreaKomentar.getText();
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
							Connection conn = DriverManager.getConnection
									("jdbc:mysql://ucka.veleri.hr/mkopjar?" + 
										"user=mkopjar&password=11");
							//id, odabrana biljka, kolicina komentar
							String sql = "INSERT INTO Biljka_korisnika VALUES(NULL,?,?,?);";
							PreparedStatement stmt = conn.prepareStatement(sql);
							stmt.setString(1, odabranabiljka);
							stmt.setString(2, kolicina);
							stmt.setString(3, komentar);
							stmt.execute();
							
							conn.close();
							
							comboBoxOdabrana_biljka.setSelectedIndex(-1);
							textFieldKolicina.setText("");
							textAreaKomentar.setText("");
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, 
								ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
