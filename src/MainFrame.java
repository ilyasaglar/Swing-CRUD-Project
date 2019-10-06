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
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel, labelInput;
	private List<JButton> btnList = new ArrayList<>();

	public MainFrame() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Human Resources");
		this.setBounds(100, 100, 820, 477);
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
		btnEmployees.setBounds(53, 150, 195, 80);
		btnEmployees.setVisible(false);
		getContentPane().add(btnEmployees);

		JButton btnDepartments = new JButton("DEPARTMENTS");
		btnDepartments.setIcon(new ImageIcon(this.getClass().getResource("icon/departments.png")));
		btnDepartments.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnDepartments);
		btnDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentsFrame depFrame = new DepartmentsFrame();
				depFrame.setVisible(true);
			}
		});
		btnDepartments.setBounds(283, 150, 210, 80);
		btnDepartments.setVisible(false);
		getContentPane().add(btnDepartments);

		JButton btnJobs = new JButton("JOBS");
		btnJobs.setIcon(new ImageIcon(this.getClass().getResource("icon/jobs.png")));
		btnJobs.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnJobs);
		btnJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JobsFrame jobFrame = new JobsFrame();
				jobFrame.setVisible(true);
			}
		});
		btnJobs.setBounds(532, 150, 195, 80);
		btnJobs.setVisible(false);
		getContentPane().add(btnJobs);

		JButton btnCountries = new JButton("COUNTRIES");
		btnCountries.setIcon(new ImageIcon(this.getClass().getResource("icon/countries.png")));
		btnCountries.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnCountries);
		btnCountries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CountriesFrame countryFrame = new CountriesFrame();
				countryFrame.setVisible(true);
			}
		});
		btnCountries.setBounds(53, 264, 195, 80);
		btnCountries.setVisible(false);
		getContentPane().add(btnCountries);

		JButton btnLocations = new JButton("LOCATIONS");
		btnLocations.setIcon(new ImageIcon(this.getClass().getResource("icon/locations.png")));
		btnLocations.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnLocations);
		btnLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationsFrame locationFrame = new LocationsFrame();
				locationFrame.setVisible(true);
			}
		});
		btnLocations.setBounds(283, 264, 210, 80);
		btnLocations.setVisible(false);
		getContentPane().add(btnLocations);

		JButton btnRegions = new JButton("REGIONS");
		btnRegions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				RegionsFrame regionsFrame = new RegionsFrame();
				regionsFrame.setVisible(true);
			}
		});
		btnRegions.setIcon(new ImageIcon(this.getClass().getResource("icon/regions.png")));
		btnRegions.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnList.add(btnRegions);
		btnRegions.setBounds(532, 264, 195, 80);
		btnRegions.setVisible(false);
		getContentPane().add(btnRegions);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(280, 204, 97, 21);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(280, 236, 97, 21);
		getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(387, 206, 103, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(387, 238, 103, 20);
		getContentPane().add(passwordField);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = textField.getText();
				String pword = passwordField.getText();

				if (uname.equals("admin") && pword.equals("admin")) {
					JOptionPane.showMessageDialog(new JFrame(), "Login Successful ", "Login",
							JOptionPane.INFORMATION_MESSAGE);

					for (JButton jButton : btnList) {
						jButton.setVisible(true);
					}
					lblNewLabel.setVisible(true);
					textField.setVisible(false);
					passwordField.setVisible(false);
					lblUsername.setVisible(false);
					lblPassword.setVisible(false);
					labelInput.setVisible(false);
					btnLogin.setVisible(false);
					
					

				} else
					JOptionPane.showMessageDialog(new JFrame(), "Invalid Username or Password", "Error",
							JOptionPane.ERROR_MESSAGE);

			}

		});
		btnLogin.setBounds(387, 296, 103, 23);
		getContentPane().add(btnLogin);

		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("icon/logo2.png")));
		lblNewLabel.setBounds(213, 0, 368, 139);
		lblNewLabel.setVisible(false);
		getContentPane().add(lblNewLabel);

		JLabel lblLogo2 = new JLabel("");
		lblLogo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo2.setIcon(new ImageIcon(this.getClass().getResource("icon/logo.png")));
		lblLogo2.setBounds(645, 350, 188, 88);
		getContentPane().add(lblLogo2);

		labelInput = new JLabel("");
		labelInput.setHorizontalAlignment(SwingConstants.CENTER);
		labelInput.setIcon(new ImageIcon(this.getClass().getResource("icon/key.png")));
		labelInput.setBounds(234, 0, 347, 180);
		getContentPane().add(labelInput);
		labelInput.setVisible(true);

		this.setVisible(true);
		//
	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}
}