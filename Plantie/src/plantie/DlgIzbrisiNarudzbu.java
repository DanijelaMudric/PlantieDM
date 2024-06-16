package plantie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DlgIzbrisiNarudzbu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID_korisnika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgIzbrisiNarudzbu dialog = new DlgIzbrisiNarudzbu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgIzbrisiNarudzbu() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textFieldID_korisnika = new JTextField();
			textFieldID_korisnika.setBounds(134, 107, 159, 20);
			contentPanel.add(textFieldID_korisnika);
			textFieldID_korisnika.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("ID narudzbe(kosarice) koje želite izbrisati:");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			lblNewLabel.setBounds(85, 52, 266, 20);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ID_korisnika = textFieldID_korisnika.getText();
					
						try {						
						 	 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						 	 Connection conn = DriverManager.getConnection 
			("jdbc:mysql://ucka.veleri.hr/dmudric?user=dmudric&password=11");

						  String sql = "DELETE FROM Kosarica WHERE ID_Kosarice = ? ";						 
						  PreparedStatement stmt = conn.prepareStatement(sql);
						  stmt.setString(1,ID_korisnika);
			  			  stmt.execute();
										
						  conn.close();
									
						  textFieldID_korisnika.setText("");
										
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
		}}}