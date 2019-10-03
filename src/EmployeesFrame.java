import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
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
import javax.swing.JButton;


public class EmployeesFrame extends JDialog{
	JList<Employees> jlist;
	EmployeesDAO empDao = new EmployeesDAO();
	List<JTextField> jtList = new ArrayList<>();
	List<JButton> btnList = new ArrayList<>();
	
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
	JButton btnUpdate;
	JButton btnDelete;
	JButton btnInsert;
	JButton btnCancel;
	JButton btnSave;

	public EmployeesFrame() {
		setModal(true);
		this.setTitle("Employees");
		this.setBounds(200, 200, 634, 507);
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
		empList = empDao.getAllData();
		
		for (Employees employees : empList) {
			model.addElement(employees);
		}
		
		jlist.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e)
		    {
		        if(!e.getValueIsAdjusting()) {
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
		txtHireDate.setBounds(134, 190, 86, 20);
		panelRight.add(txtHireDate);
		txtHireDate.setColumns(10);
		jtList.add(txtHireDate);
		
		txtJobID = new JTextField();
		txtJobID.setEditable(false);
		txtJobID.setBounds(134, 225, 86, 20);
		panelRight.add(txtJobID);
		txtJobID.setColumns(10);
		jtList.add(txtJobID);
		
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
		panelRight.add(txtDepartmentID);
		jtList.add(txtDepartmentID);
		
		lblCommissionPct = new JLabel("Commission PCT");
		lblCommissionPct.setBounds(23, 301, 101, 14);
		panelRight.add(lblCommissionPct);
		
		lblManagerId = new JLabel("Manager ID");
		lblManagerId.setBounds(23, 336, 86, 14);
		panelRight.add(lblManagerId);
		
		lblDepartmentId = new JLabel("Department ID");
		lblDepartmentId.setBounds(23, 371, 86, 14);
		panelRight.add(lblDepartmentId);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setEditable(true);
					jt.setText(null);
				}
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				
			}
		});
		btnInsert.setBounds(129, 434, 89, 23);
		btnList.add(btnInsert);
		getContentPane().add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setEditable(true);
				}
				
				txtID.setEditable(false);

				//empDao.getEmployee(Integer.valueOf(txtID.getText()));
				
			}
		});
		btnUpdate.setBounds(225, 434, 89, 23);
		btnList.add(btnUpdate);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(324, 434, 89, 23);
		btnList.add(btnDelete);
		getContentPane().add(btnDelete);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JButton btn : btnList) {
					if (btn.isEnabled() == false) {
						btn.setEnabled(true);
					}
				}
				
				//empDao.update();
				Employees e = new Employees();
				e.setEmployee_id(Integer.valueOf(txtID.getText()));
				e.setFirst_name(txtName.getText());
				e.setLast_name(txtSurname.getText());
				e.setEmail(txtEmail.getText());
				e.setPhone_number(txtPhoneNumber.getText());
				e.setHire_date(Date.valueOf(txtHireDate.getText()));
				e.setJob_id(txtJobID.getText());
				e.setSalary(Integer.valueOf(txtSalary.getText()));
				e.setCommission_pct(Integer.valueOf(txtCommissionPCT.getText()));
				e.setManager_id(Integer.valueOf(txtManagerID.getText()));
				e.setDepartment_id(Integer.valueOf(txtDepartmentID.getText()));
				
				empDao.alper(e);
				empDao.update();
				
			}
		});
		btnSave.setBounds(430, 434, 89, 23);
		btnList.add(btnSave);
		getContentPane().add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(529, 434, 89, 23);
		btnList.add(btnCancel);
		getContentPane().add(btnCancel);
		this.setVisible(true);
		
	//
	}
	/*
	public List<JTextField> addFieldToList(JTextField jt) {
		List<JTextField> list = new ArrayList<>();
		list.add(jt);
		return list;
	}
	*/
	public static void main(String[] args) {
		EmployeesFrame empFrame = new EmployeesFrame();
		//List<String> list = new List<String>();
		
		
	}
}