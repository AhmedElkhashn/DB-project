package HRSystemApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	private JTextField passTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(109, 91, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(109, 127, 61, 14);
		contentPane.add(lblNewLabel_1);
		
		userTextField = new JTextField();
		userTextField.setBounds(188, 88, 106, 20);
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Utility utility=new Utility();
					String username = userTextField.getText();
					String pass = passTextField.getText();
					
					if(utility.login(username, pass)) {
						JOptionPane.showMessageDialog(null,"LOGIN IS SUCESSFUL");
						Login.this.dispose();	
						MainWindow.main(null);
					}else {
						JOptionPane.showMessageDialog(null,"NOT AUTHORIZED USER, TRY AGAIN");
						userTextField.setText("");
						passTextField.setText("");
						
					}
					utility.terminate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
		});
		loginButton.setBounds(166, 177, 89, 23);
		contentPane.add(loginButton);
		
		passTextField = new JTextField();
		passTextField.setBounds(188, 124, 106, 20);
		contentPane.add(passTextField);
		passTextField.setColumns(10);
	}
}
