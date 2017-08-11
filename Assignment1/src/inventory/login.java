package inventory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//this is comment by Shanini to show conflict

	/**
	 * Created the frame by Shanini.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		contentPane.add(label, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		DBManager dbm = new DBManager();
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtPassword.getText();
				String username = txtUserName.getText();
				if(dbm.authenticateUser( username,  password)){
					txtPassword.setText(null);
					txtUserName.setText(null);
					//InventoryUI inventoryUI = new InventoryUI();
					//inventoryUI.launchUI();
					InventoryUI.main(null);
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText(null);
					txtUserName.setText(null);
				}
			}
		});
		btnLogin.setBounds(20, 204, 107, 23);
		panel.add(btnLogin);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(42, 76, 85, 38);
		panel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(42, 130, 85, 38);
		panel.add(lblPassword);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setBounds(195, 11, 63, 14);
		panel.add(lblUserLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 191, 404, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 46, 404, 2);
		panel.add(separator_1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(178, 204, 89, 23);
		panel.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login System", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(325, 204, 89, 23);
		panel.add(btnExit);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(137, 139, 147, 20);
		panel.add(txtPassword);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(137, 85, 147, 20);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
	}
}
