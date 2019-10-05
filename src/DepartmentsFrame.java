
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class DepartmentsFrame extends JDialog {
	
	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Departments> depList = new ArrayList<>();
	private JList<Departments> jlist;
	private DefaultListModel<Departments> model;
	private JPanel panelLeft = new JPanel();
	private DepartmentsDAO depDao = new DepartmentsDAO();
		
	private JScrollPane scrollPane;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtID;
	private JTextField txtDepName;
	private JTextField txtManagerID;
	private JTextField txtLocationID;
	private JLabel lblDepID;
	private JLabel lblDepName;
	private JLabel lblManagerID;
	private JLabel lblLocationID;
	
	public void createJlist() {
		model = new DefaultListModel<Departments>();
		jlist = new JList<Departments>(model);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		scrollPane.setViewportView(jlist);
		panelLeft.add(scrollPane);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		depList = depDao.getAllData();

		for (Departments departments : depList) {
			model.addElement(departments);
		}
		
		jlist.getSelectionModel().addListSelectionListener(e -> {
			Departments dep = jlist.getSelectedValue();

			txtID.setText(dep.getDepartment_id().toString());
			txtDepName.setText(dep.getDepartment_name());
			txtManagerID.setText(dep.getManager_id().toString());
			txtLocationID.setText(dep.getLocation_id().toString());
		});
	}
	
	public DepartmentsFrame() {
		setModal(true);
		this.setTitle("Departments");
		this.setBounds(200, 200, 655, 507);
		getContentPane().setLayout(null);

		//JPanel panelLeft = new JPanel();
		panelLeft.setBounds(10, 11, 261, 410);
		getContentPane().add(panelLeft);
		panelLeft.setLayout(null);
		
		createJlist();
		
		JPanel panelRight = new JPanel();
		panelRight.setBounds(281, 11, 348, 410);
		getContentPane().add(panelRight);
		panelRight.setLayout(null);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(134, 15, 120, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		jtList.add(txtID);

		txtDepName = new JTextField();
		txtDepName.setEditable(false);
		txtDepName.setText("");
		txtDepName.setBounds(134, 50, 120, 20);
		panelRight.add(txtDepName);
		txtDepName.setColumns(10);
		jtList.add(txtDepName);

		txtManagerID = new JTextField();
		txtManagerID.setEditable(false);
		txtManagerID.setText("");
		txtManagerID.setBounds(134, 85, 120, 20);
		panelRight.add(txtManagerID);
		txtManagerID.setColumns(10);
		jtList.add(txtManagerID);

		txtLocationID = new JTextField();
		txtLocationID.setEditable(false);
		txtLocationID.setText("");
		txtLocationID.setBounds(134, 120, 120, 20);
		panelRight.add(txtLocationID);
		txtLocationID.setColumns(10);
		jtList.add(txtLocationID);
		
		lblDepID = new JLabel("Department ID");
		lblDepID.setBounds(23, 21, 86, 14);
		panelRight.add(lblDepID);

		lblDepName = new JLabel("Department Name");
		lblDepName.setBounds(23, 56, 105, 14);
		panelRight.add(lblDepName);

		lblManagerID = new JLabel("Manager ID");
		lblManagerID.setBounds(23, 91, 86, 14);
		panelRight.add(lblManagerID);

		lblLocationID = new JLabel("Location ID");
		lblLocationID.setBounds(23, 126, 86, 14);
		panelRight.add(lblLocationID);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(129, 434, 89, 23);
		btnList.add(btnInsert);
		getContentPane().add(btnInsert);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(225, 434, 89, 23);
		btnList.add(btnUpdate);
		getContentPane().add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(324, 434, 89, 23);
		btnList.add(btnDelete);
		getContentPane().add(btnDelete);

		btnSave = new JButton("Save");
		btnSave.setBounds(430, 434, 89, 23);
		btnList.add(btnSave);
		getContentPane().add(btnSave);
		btnSave.setEnabled(false);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(529, 434, 89, 23);
		btnList.add(btnCancel);
		btnCancel.setEnabled(false);
		getContentPane().add(btnCancel);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		DepartmentsFrame depFrame = new DepartmentsFrame();

	}

}
