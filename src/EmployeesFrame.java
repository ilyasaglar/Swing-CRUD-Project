import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class EmployeesFrame extends JDialog {
	public static Connection connection = DbConnection.getConnection();
	JList<Employees> jlist;
	Employees e = new Employees();
	EmployeesDAO empDao = new EmployeesDAO();
	List<JTextField> jtList = new ArrayList<>();
	List<JButton> btnList = new ArrayList<>();
	List<Jobs> jobList = new ArrayList<>();
	List<Departments> depList = new ArrayList<>();
	Employees tempData = new Employees();
	List<Employees> empList = new ArrayList<>();
	DefaultListModel<Employees> model;

	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JScrollPane scrollPane;
	private JLabel lblEmployeeId;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JTextField txtPhoneNumber;
	private JTextField txtHireDate;
	private JTextField txtJobID;
	private JTextField txtSalary;
	private JLabel lblPhoneNumber;
	private JLabel lblHireDate;
	private JLabel lblJobId;
	private JLabel lblSalary;
	private JTextField txtCommissionPCT;
	private JTextField txtManagerID;
	private JTextField txtDepartmentID;
	private JLabel lblCommissionPct;
	private JLabel lblManagerId;
	private JLabel lblDepartmentId;
	JPanel panelLeft = new JPanel();
	JButton btnUpdate;
	JButton btnDelete;
	JButton btnInsert;
	JButton btnCancel;
	JButton btnSave;
	public int islem;
	private Boolean sonuc;
	private String deger;
	private JComboBox<String> comboBoxJobID;
	private JComboBox<String> comboBoxDepID;

	public String findJobTitlefromJobID(Employees e) {
		String jobtitle = null;
		String jobid = e.getJob_id();

		for (Jobs j : jobList) {
			if (j.getJob_id().equals(jobid)) {
				jobtitle = j.getJob_title();
			}
		}
		return jobtitle;
	}

	public String findDepNamefromDepID(Employees e) {
		String depname = null;
		Integer depid = e.getDepartment_id();

		for (Departments d : depList) {
			if (d.getDepartment_id() == depid) {
				depname = d.getDepartment_name();
			}
		}
		return depname;
	}

	public void createJList() {

		model = new DefaultListModel<Employees>();
		jlist = new JList<Employees>(model);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		scrollPane.setViewportView(jlist);
		panelLeft.add(scrollPane);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList = empDao.getAllData();

		for (Employees employees : empList) {
			model.addElement(employees);
		}

		jlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!e.getValueIsAdjusting()) {

					Employees emp = jlist.getSelectedValue();

					txtID.setText(emp.getEmployee_id().toString());
					txtName.setText(emp.getFirst_name());
					txtSurname.setText(emp.getLast_name());
					txtEmail.setText(emp.getEmail());
					txtPhoneNumber.setText(emp.getPhone_number());
					txtHireDate.setText(emp.getHire_date().toString());
					txtJobID.setText(emp.getJob_id());
					txtSalary.setText(emp.getSalary().toString());
					txtCommissionPCT.setText(emp.getCommission_pct().toString());
					txtManagerID.setText(emp.getManager_id().toString());
					txtDepartmentID.setText(emp.getDepartment_id().toString());
				}

			}
		});

	}

	public EmployeesFrame() {
		setResizable(false);
		setModal(true);
		this.setTitle("Employees");
		this.setBounds(200, 200, 647, 507);
		getContentPane().setLayout(null);

		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(10, 11, 261, 410);
		getContentPane().add(panelLeft);
		panelLeft.setLayout(null);

		// createJList();

		DefaultListModel<Employees> model = new DefaultListModel<Employees>();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		panelLeft.add(scrollPane);
		jlist = new JList<Employees>(model);
		scrollPane.setViewportView(jlist);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// createJList();

		List<Employees> empList = new ArrayList<>();
		empList = empDao.getAllData();

		for (Employees employees : empList) {
			model.addElement(employees);
		}

		jlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Employees emp = jlist.getSelectedValue();

					txtID.setText(emp.getEmployee_id().toString());
					txtName.setText(emp.getFirst_name());
					txtSurname.setText(emp.getLast_name());
					txtEmail.setText(emp.getEmail());
					txtPhoneNumber.setText(emp.getPhone_number());
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
					String date = sdf.format(emp.getHire_date());
					txtHireDate.setText(date);

					txtJobID.setText(emp.getJob_id());
					txtSalary.setText(emp.getSalary().toString());
					txtCommissionPCT.setText(emp.getCommission_pct().toString());
					txtManagerID.setText(emp.getManager_id().toString());
					txtDepartmentID.setText(emp.getDepartment_id().toString());

				}
			}
		});

		JPanel panelRight = new JPanel();
		panelRight.setBounds(281, 11, 337, 410);
		getContentPane().add(panelRight);
		panelRight.setLayout(null);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(134, 15, 86, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		jtList.add(txtID);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setText("");
		txtName.setBounds(134, 50, 86, 20);
		panelRight.add(txtName);
		txtName.setColumns(10);
		jtList.add(txtName);

		txtSurname = new JTextField();
		txtSurname.setEditable(false);
		txtSurname.setText("");
		txtSurname.setBounds(134, 85, 86, 20);
		panelRight.add(txtSurname);
		txtSurname.setColumns(10);
		jtList.add(txtSurname);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setText("");
		txtEmail.setBounds(134, 120, 86, 20);
		panelRight.add(txtEmail);
		txtEmail.setColumns(10);
		jtList.add(txtEmail);

		lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(23, 21, 86, 14);
		panelRight.add(lblEmployeeId);

		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(23, 56, 86, 14);
		panelRight.add(lblFirstName);

		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(23, 91, 86, 14);
		panelRight.add(lblLastName);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(23, 126, 86, 14);
		panelRight.add(lblEmail);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setEditable(false);
		txtPhoneNumber.setBounds(134, 155, 86, 20);
		panelRight.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		jtList.add(txtPhoneNumber);

		txtHireDate = new JTextField();
		txtHireDate.setEditable(false);
		txtHireDate.setBounds(134, 186, 86, 20);
		panelRight.add(txtHireDate);
		txtHireDate.setColumns(10);
		jtList.add(txtHireDate);

		txtJobID = new JTextField();
		txtJobID.setEditable(false);
		txtJobID.setBounds(134, 225, 86, 20);
		panelRight.add(txtJobID);
		txtJobID.setColumns(10);
		jtList.add(txtJobID);
		txtJobID.setVisible(false);

		txtSalary = new JTextField();
		txtSalary.setEditable(false);
		txtSalary.setBounds(134, 260, 86, 20);
		panelRight.add(txtSalary);
		txtSalary.setColumns(10);
		jtList.add(txtSalary);

		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(23, 161, 86, 14);
		panelRight.add(lblPhoneNumber);

		lblHireDate = new JLabel("Hire Date");
		lblHireDate.setBounds(23, 196, 86, 14);
		panelRight.add(lblHireDate);

		lblJobId = new JLabel("Job ID");
		lblJobId.setBounds(23, 231, 86, 14);
		panelRight.add(lblJobId);

		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(23, 266, 86, 14);
		panelRight.add(lblSalary);

		txtCommissionPCT = new JTextField();
		txtCommissionPCT.setEditable(false);
		txtCommissionPCT.setColumns(10);
		txtCommissionPCT.setBounds(134, 295, 86, 20);
		panelRight.add(txtCommissionPCT);
		jtList.add(txtCommissionPCT);

		txtManagerID = new JTextField();
		txtManagerID.setEditable(false);
		txtManagerID.setColumns(10);
		txtManagerID.setBounds(134, 330, 86, 20);
		panelRight.add(txtManagerID);
		jtList.add(txtManagerID);

		txtDepartmentID = new JTextField();
		txtDepartmentID.setEditable(false);
		txtDepartmentID.setColumns(10);
		txtDepartmentID.setBounds(134, 365, 86, 20);
		txtDepartmentID.setVisible(false);
		panelRight.add(txtDepartmentID);
		jtList.add(txtDepartmentID);

		lblCommissionPct = new JLabel("Commission PCT");
		lblCommissionPct.setBounds(23, 301, 101, 14);
		panelRight.add(lblCommissionPct);

		lblManagerId = new JLabel("Manager ID");
		lblManagerId.setBounds(23, 336, 86, 14);
		panelRight.add(lblManagerId);
		lblDepartmentId = new JLabel("Department Name");
		lblDepartmentId.setBounds(8, 368, 101, 14);
		panelRight.add(lblDepartmentId);

		comboBoxJobID = new JComboBox();
		comboBoxJobID.setBounds(134, 225, 95, 20);
		try {
			String str;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from JOBS");
			while (rs.next()) {
				str = rs.getString("job_title");
				comboBoxJobID.addItem(str);
				Jobs j = new Jobs();
				j.setJob_id(rs.getString(1));
				j.setJob_title(rs.getString(2));
				jobList.add(j);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		panelRight.add(comboBoxJobID);

		comboBoxDepID = new JComboBox();
		comboBoxDepID.setBounds(134, 365, 95, 20);
		try {
			String str;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from DEPARTMENTS");
			while (rs.next()) {
				str = rs.getString("department_name");
				comboBoxDepID.addItem(str);
				Departments d = new Departments();
				d.setDepartment_id(rs.getInt(1));
				d.setDepartment_name(rs.getString(2));
				depList.add(d);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		panelRight.add(comboBoxDepID);

		btnInsert = new JButton("Insert");
		btnInsert.setHorizontalAlignment(SwingConstants.LEFT);
		btnInsert.setIcon(new ImageIcon(this.getClass().getResource("icon/add.png")));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setEditable(true);
					jt.setText(null);
				}
				islem = 0;
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				txtJobID.setVisible(false);
				txtDepartmentID.setVisible(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);

			}
		});
		btnInsert.setBounds(10, 430, 89, 30);
		btnList.add(btnInsert);
		getContentPane().add(btnInsert);

		btnUpdate = new JButton("Update");
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setIcon(new ImageIcon(this.getClass().getResource("icon/reload.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setEditable(true);
				}

				islem = 1;
				txtID.setEditable(false);
				btnInsert.setEnabled(false);
				btnDelete.setEnabled(false);
				txtJobID.setVisible(false);
				txtDepartmentID.setVisible(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				if (txtID.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Listeden Kiþi Seçimi Yapýnýz. ", "Dialog",
							JOptionPane.YES_NO_CANCEL_OPTION);

				}
			}
		});
		btnUpdate.setBounds(110, 430, 95, 30);
		btnList.add(btnUpdate);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("icon/garbage.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				islem = 2;

				for (JTextField jt : jtList) {
					jt.setEditable(false);
				}
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				btnInsert.setEnabled(false);
				btnUpdate.setEnabled(false);
				txtJobID.setVisible(true);

			}
		});
		btnDelete.setBounds(216, 430, 91, 30);
		btnList.add(btnDelete);
		getContentPane().add(btnDelete);

		btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(this.getClass().getResource("icon/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connection = DbConnection.getConnection();

				if (islem == 0) { // ýnsert iþlemi
					btnInsert.setEnabled(true);

					e.setEmployee_id(Integer.valueOf(txtID.getText().toString()));
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals("")) && bosDolu == true) {
						if (uzunKontrol == true) {

							Boolean numbercontrol = numberControl(txtID.getText().toString());
						//	Boolean phoneNumbercontrol = numberControl(txtPhoneNumber.getText().toString());
							Boolean salarycontrol = numberControl(txtSalary.getText().toString());
							Boolean managercontrol = numberControl(txtManagerID.getText().toString());

							if (numbercontrol == true  && salarycontrol == true
									&& managercontrol == true) {
								e.setEmployee_id(Integer.valueOf(txtID.getText()));
								degerGirisi();

								empDao.setEmployee(e);
								sonuc = empDao.insert();

								if (sonuc == true) {
									JOptionPane.showMessageDialog(new JFrame(), "Bilgiler Kaydedildi. ", "Sonuç",
											JOptionPane.YES_NO_CANCEL_OPTION);

								} else {

									JOptionPane.showMessageDialog(new JFrame(), "Bir hata oluþtu.", "Hata!",
											JOptionPane.ERROR_MESSAGE);

								}
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Bir hata oluþtu.", "Hata!",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"Maximum alan uzunlugunu geçtiniz, düzeltip yeniden deneyiniz.", "Hata!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Tüm alanlarý doldurunuz", "Hata!",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				else if (islem == 1) { // update islemi
					btnUpdate.setEnabled(true);
					e.setEmployee_id(Integer.valueOf(txtID.getText()));
					degerGirisi();
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();
					if (bosDolu = true) {
						if (uzunKontrol == true) {

							empDao.setEmployee(e);
							sonuc = empDao.update();

							if (sonuc == true) {
								JOptionPane.showMessageDialog(new JFrame(), "Bilgiler Güncellendi. ", "Sonuç",
										JOptionPane.YES_NO_CANCEL_OPTION);

							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Bir hata oluþtu.", "Hata!",
										JOptionPane.ERROR_MESSAGE);

							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"Maximum alan uzunlugunu geçtiniz, düzeltip yeniden deneyiniz.", "Hata!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Tüm alanlarý doldurunuz", "Hata!",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				else if (islem == 2) { // delete islemi
					btnDelete.setEnabled(true);
					if (!txtID.getText().equals("")) {

						sonuc = empDao.delete(Integer.valueOf(txtID.getText()));

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(),
									txtID.getText().toString() + " ID'ye Ait Kiþi Bilgileri Silindi. ", "Sonuç",
									JOptionPane.YES_NO_CANCEL_OPTION);

						} else {
							JOptionPane.showMessageDialog(new JFrame(), " Bir hata oluþtu. ", "Sonuç",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), " Kiþi Seçimi Yapýnýz ", "Hata!",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					btnSave.setEnabled(false);
					btnCancel.setEnabled(false);
				}

				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSave.setBounds(319, 430, 91, 30);
		btnList.add(btnSave);

		getContentPane().add(btnSave);

		btnSave.setEnabled(false);

		btnCancel = new JButton("Cancel");
		btnCancel.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("icon/cancel.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnInsert.setEnabled(true);
				btnDelete.setEnabled(true);
				btnUpdate.setEnabled(true);
				for (JTextField jt : jtList) {
					jt.setEditable(false);
				}
				txtJobID.setVisible(false);
				txtDepartmentID.setVisible(false);
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancel.setBounds(420, 430, 95, 30);
		btnList.add(btnCancel);
		btnCancel.setEnabled(false);
		getContentPane().add(btnCancel);

		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.setHorizontalAlignment(SwingConstants.LEFT);
		btnTemizle.setIcon(new ImageIcon(this.getClass().getResource("icon/temizle.png")));
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setText("");
				}
			}
		});
		btnTemizle.setEnabled(true);
		btnTemizle.setBounds(524, 430, 98, 30);
		getContentPane().add(btnTemizle);
		this.setVisible(true);

	}

	public boolean numberControl(String deger) {
		char a;
		int sonuc = 0;
		char[] ch = deger.toCharArray();

		for (int i = 0; i < deger.length(); i++) {
			if (Character.isLetter(ch[i])) {
				sonuc = +1;
			} else if (Character.isDigit(ch[i])) {

			} else if (Character.isSpaceChar(ch[i])) {
				sonuc = +1;
			} else {
				sonuc = +1;
			}
		}

		if (sonuc >= 1) {
			return false;
		} else {
			return true;
		}

	}

	public void degerGirisi() {

		e.setFirst_name(txtName.getText());
		e.setLast_name(txtSurname.getText());
		e.setEmail(txtEmail.getText());
		e.setPhone_number(txtPhoneNumber.getText());
		for (Jobs j : jobList) {
			if (comboBoxJobID.getSelectedItem().equals(j.getJob_title())) {
				e.setJob_id(j.getJob_id());
			}
		}

		e.setHire_date(txtHireDate.getText());
		e.setJob_id(txtJobID.getText());
		e.setSalary(Integer.valueOf(txtSalary.getText()));
		e.setCommission_pct(Double.valueOf(txtCommissionPCT.getText()));
		e.setManager_id(Integer.valueOf(txtManagerID.getText()));
		for (Departments d : depList) {
			if (comboBoxDepID.getSelectedItem().equals(d.getDepartment_name())) {
				e.setDepartment_id(d.getDepartment_id());
			}
		}
	}

	public Boolean uzunlukKontrol() {
		if (txtID.getText().toString().length() <= 6 && txtName.getText().length() <= 20
				&& txtSurname.getText().length() <= 25 && txtEmail.getText().length() <= 20
				&& txtSalary.getText().length() <= 8 && txtManagerID.getText().length() <= 6) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean bosDoluKontrol() {
		if (!(txtEmail.getText().equals("")) && !(txtName.getText().equals("")) && !(txtSurname.getText().equals(""))
				&& !(txtCommissionPCT.getText().equals("")) && !(txtManagerID.getText().equals(""))
				&& !(txtPhoneNumber.getText().equals("")) && !(txtSalary.getText().equals(""))) {
			return true;
		} else {
			return false;
		}
	}
}