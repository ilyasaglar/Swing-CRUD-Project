import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
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
		this.setBounds(100, 100, 775, 429);
		getContentPane().setLayout(null);
		
		JButton btnEmployees = new JButton("EMPLOYEES");
		btnEmployees.setIcon(new ImageIcon(this.getClass().getResource("icon/employee.png")));
		btnEmployees.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnEmployees);
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeesFrame empFrame = new EmployeesFrame();
				empFrame.setVisible(true);
			}
		});
		btnEmployees.setBounds(51, 101, 180, 80);
		btnEmployees.setVisible(false);
		getContentPane().add(btnEmployees);
		
		JButton btnDepartments = new JButton("DEPARTMENTS");
		btnDepartments.setIcon(new ImageIcon(this.getClass().getResource("icon/departments.png")));
		btnDepartments.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnDepartments);
		btnDepartments.setBounds(283, 101, 180, 80);
		btnDepartments.setVisible(false);
		getContentPane().add(btnDepartments);
		
		JButton btnJobs = new JButton("JOBS");
		btnJobs.setIcon(new ImageIcon(this.getClass().getResource("icon/jobs.png")));
		btnJobs.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnJobs);
		btnJobs.setBounds(507, 101, 180, 80);
		btnJobs.setVisible(false);
		getContentPane().add(btnJobs);
		
		JButton btnCountries = new JButton("COUNTRIES");
		btnCountries.setIcon(new ImageIcon(this.getClass().getResource("icon/countries.png")));
		btnCountries.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnCountries);
		btnCountries.setBounds(51, 215, 180, 80);
		btnCountries.setVisible(false);
		getContentPane().add(btnCountries);
		
		JButton btnLocations = new JButton("LOCATIONS");
		btnLocations.setIcon(new ImageIcon(this.getClass().getResource("icon/locations.png")));
		btnLocations.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnLocations);
		btnLocations.setBounds(283, 215, 180, 80);
		btnLocations.setVisible(false);
		getContentPane().add(btnLocations);
		
		JButton btnRegions = new JButton("REGIONS");
		btnRegions.setIcon(new ImageIcon(this.getClass().getResource("icon/regions.png")));
		btnRegions.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnRegions);
		btnRegions.setBounds(510, 215, 180, 80);
		btnRegions.setVisible(false);
		getContentPane().add(btnRegions);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(263, 117, 97, 21);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(263, 149, 97, 21);
		getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(370, 119, 103, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(370, 151, 103, 20);
		getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = textField.getText();
				String pword = passwordField.getText();
				
				if (uname.equals("admin") && pword.equals("admin")) {
					JOptionPane.showMessageDialog(new JFrame(), 
							"Login Successful ", "Login",
							JOptionPane.INFORMATION_MESSAGE);
					
					for (JButton jButton : btnList) {
						jButton.setVisible(true);
					}
					
					textField.setVisible(false);
					passwordField.setVisible(false);
					lblUsername.setVisible(false);
					lblPassword.setVisible(false);
					btnLogin.setVisible(false);
					
					

				} else 
				JOptionPane.showMessageDialog(new JFrame(),
						"Invalid Username or Password","Error",
						JOptionPane.ERROR_MESSAGE);
			
				
			}
		});
		btnLogin.setBounds(374, 209, 99, 23);
		getContentPane().add(btnLogin);
		this.setVisible(true);
	//			
	}
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}
}