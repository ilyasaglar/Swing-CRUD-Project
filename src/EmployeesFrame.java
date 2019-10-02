import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

class User {
	Integer id;
	String name;
	String surname;
	Integer age;
	
	public User(Integer id, String name, String surname, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return id + " " + name + " , " + surname;
	}
}

public class EmployeesFrame extends JDialog{
	JList<Employees> jlist;
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

	public EmployeesFrame() {
		setModal(true);
		this.setTitle("Employees");
		this.setBounds(200, 200, 634, 471);
		getContentPane().setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(10, 11, 261, 410);
		getContentPane().add(panelLeft);
		panelLeft.setLayout(null);
		
		DefaultListModel<Employees> model = new DefaultListModel<Employees>();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		panelLeft.add(scrollPane);
		jlist = new JList<Employees>(model);
		scrollPane.setViewportView(jlist);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
	
		List<Employees> empList = new ArrayList<>();
		empList = getAllUsers();
		
		for (Employees employees : empList) {
			model.addElement(employees);
		}
		
		jlist.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e)
		    {
		        if(!e.getValueIsAdjusting()) {
		            //final List<User> selectedValuesList = jlist.getSelectedValuesList();
		            Employees emp = jlist.getSelectedValue();
		            
		            txtID.setText(emp.getEmployee_id().toString());
		            txtName.setText(emp.getFirst_name());
		            txtSurname.setText(emp.getLast_name());
		            txtEmail.setText(emp.getEmail());
		            txtPhoneNumber.setText(emp.getPhone_number());
		            //txtHireDate.setText(t);
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
		txtID.setBounds(134, 15, 86, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("");
		txtName.setBounds(134, 50, 86, 20);
		panelRight.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setText("");
		txtSurname.setBounds(134, 85, 86, 20);
		panelRight.add(txtSurname);
		txtSurname.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setBounds(134, 120, 86, 20);
		panelRight.add(txtEmail);
		txtEmail.setColumns(10);
		
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
		txtPhoneNumber.setBounds(134, 155, 86, 20);
		panelRight.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		txtHireDate = new JTextField();
		txtHireDate.setBounds(134, 190, 86, 20);
		panelRight.add(txtHireDate);
		txtHireDate.setColumns(10);
		
		txtJobID = new JTextField();
		txtJobID.setBounds(134, 225, 86, 20);
		panelRight.add(txtJobID);
		txtJobID.setColumns(10);
		
		txtSalary = new JTextField();
		txtSalary.setBounds(134, 260, 86, 20);
		panelRight.add(txtSalary);
		txtSalary.setColumns(10);
		
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
		txtCommissionPCT.setColumns(10);
		txtCommissionPCT.setBounds(134, 295, 86, 20);
		panelRight.add(txtCommissionPCT);
		
		txtManagerID = new JTextField();
		txtManagerID.setColumns(10);
		txtManagerID.setBounds(134, 330, 86, 20);
		panelRight.add(txtManagerID);
		
		txtDepartmentID = new JTextField();
		txtDepartmentID.setColumns(10);
		txtDepartmentID.setBounds(134, 365, 86, 20);
		panelRight.add(txtDepartmentID);
		
		lblCommissionPct = new JLabel("Commission PCT");
		lblCommissionPct.setBounds(23, 301, 101, 14);
		panelRight.add(lblCommissionPct);
		
		lblManagerId = new JLabel("Manager ID");
		lblManagerId.setBounds(23, 336, 86, 14);
		panelRight.add(lblManagerId);
		
		lblDepartmentId = new JLabel("Department ID");
		lblDepartmentId.setBounds(23, 371, 86, 14);
		panelRight.add(lblDepartmentId);
		this.setVisible(true);
		
	//
	}
	
	public static void main(String[] args) {
		EmployeesFrame empFrame = new EmployeesFrame();
		//List<String> list = new List<String>();
		
		
		
	}
	
	


}