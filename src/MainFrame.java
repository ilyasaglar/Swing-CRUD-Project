import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class MainFrame extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	private List<JButton> btnList = new ArrayList<>();

	public MainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Human Resources");
		this.setBounds(100, 100, 465, 350);
		getContentPane().setLayout(null);
		
		JButton btnEmployees = new JButton("EMPLOYEES");
		btnList.add(btnEmployees);
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeesFrame empFrame = new EmployeesFrame();
				empFrame.setVisible(true);
			}
		});
		btnEmployees.setBounds(28, 74, 112, 40);
		btnEmployees.setVisible(false);
		getContentPane().add(btnEmployees);
		
		JButton btnDepartments = new JButton("DEPARTMENTS");
		btnList.add(btnDepartments);
		btnDepartments.setBounds(168, 74, 112, 40);
		btnDepartments.setVisible(false);
		getContentPane().add(btnDepartments);
		
		JButton btnJobs = new JButton("JOBS");
		btnList.add(btnJobs);
		btnJobs.setBounds(308, 74, 112, 40);
		btnJobs.setVisible(false);
		getContentPane().add(btnJobs);
		
		JButton btnCountries = new JButton("COUNTRIES");
		btnList.add(btnCountries);
		btnCountries.setBounds(28, 139, 112, 40);
		btnCountries.setVisible(false);
		getContentPane().add(btnCountries);
		
		JButton btnLocations = new JButton("LOCATIONS");
		btnList.add(btnLocations);
		btnLocations.setBounds(168, 139, 112, 40);
		btnLocations.setVisible(false);
		getContentPane().add(btnLocations);
		
		JButton btnRegions = new JButton("REGIONS");
		btnList.add(btnRegions);
		btnRegions.setBounds(308, 139, 112, 40);
		btnRegions.setVisible(false);
		getContentPane().add(btnRegions);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(119, 107, 97, 21);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(119, 139, 97, 21);
		getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(226, 109, 103, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(226, 141, 103, 20);
		getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = textField.getText();
				String pword = passwordField.getText();
				
				if (uname.equals("admin") && pword.equals("admin")) {
					for (JButton jButton : btnList) {
						jButton.setVisible(true);
					}
					
					textField.setVisible(false);
					passwordField.setVisible(false);
					lblUsername.setVisible(false);
					lblPassword.setVisible(false);
					btnLogin.setVisible(false);
					
					JOptionPane.showMessageDialog(new JFrame(), 
							"Login Successful ", "Login",
							JOptionPane.INFORMATION_MESSAGE);

				} else 
				JOptionPane.showMessageDialog(new JFrame(),
						"Invalid Username or Password","Error",
						JOptionPane.ERROR_MESSAGE);
			
				
			}
		});
		btnLogin.setBounds(180, 171, 89, 23);
		getContentPane().add(btnLogin);
		this.setVisible(true);
	//			
	}
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}
}
