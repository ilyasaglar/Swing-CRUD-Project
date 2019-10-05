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

public class JobsFrame extends JDialog {
	
	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();	
	private List<Jobs> jobList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private JobsDAO jobDao = new JobsDAO();
	private JList<Jobs> jlist;
	private DefaultListModel<Jobs> model;
	
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtID;
	private JTextField txtJobTitle;
	private JTextField txtMinSalary;
	private JTextField txtMaxSalary;
	private JLabel lblJobID;
	private JLabel lblJobTitle;
	private JLabel lblMinSalary;
	private JLabel lblMaxSalary;
	private JScrollPane scrollPane;
	
	public void createJlist() {
		model = new DefaultListModel<Jobs>();
		jlist = new JList<Jobs>(model);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		scrollPane.setViewportView(jlist);
		panelLeft.add(scrollPane);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		jobList = jobDao.getAllData();

		for (Jobs jobs : jobList) {
			model.addElement(jobs);
		}
		
		jlist.getSelectionModel().addListSelectionListener(e -> {
			Jobs job = jlist.getSelectedValue();

			txtID.setText(job.getJob_id());
			txtJobTitle.setText(job.getJob_title());
			txtMinSalary.setText(job.getMin_salary().toString());
			txtMaxSalary.setText(job.getMax_salary().toString());
		});
	}
	
	public JobsFrame() {
		setModal(true);
		this.setTitle("Jobs");
		this.setBounds(200, 200, 655, 507);
		getContentPane().setLayout(null);

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
		txtID.setBounds(134, 15, 170, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		jtList.add(txtID);

		txtJobTitle = new JTextField();
		txtJobTitle.setEditable(false);
		txtJobTitle.setText("");
		txtJobTitle.setBounds(134, 50, 170, 20);
		panelRight.add(txtJobTitle);
		txtJobTitle.setColumns(10);
		jtList.add(txtJobTitle);

		txtMinSalary = new JTextField();
		txtMinSalary.setEditable(false);
		txtMinSalary.setText("");
		txtMinSalary.setBounds(134, 85, 170, 20);
		panelRight.add(txtMinSalary);
		txtMinSalary.setColumns(10);
		jtList.add(txtMinSalary);

		txtMaxSalary = new JTextField();
		txtMaxSalary.setEditable(false);
		txtMaxSalary.setText("");
		txtMaxSalary.setBounds(134, 120, 170, 20);
		panelRight.add(txtMaxSalary);
		txtMaxSalary.setColumns(10);
		jtList.add(txtMaxSalary);
		
		lblJobID = new JLabel("Job ID");
		lblJobID.setBounds(23, 21, 86, 14);
		panelRight.add(lblJobID);

		lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setBounds(23, 56, 86, 14);
		panelRight.add(lblJobTitle);

		lblMinSalary = new JLabel("Minimum Salary");
		lblMinSalary.setBounds(23, 91, 80, 14);
		panelRight.add(lblMinSalary);

		lblMaxSalary = new JLabel("Maximum Salary");
		lblMaxSalary.setBounds(23, 126, 80, 14);
		panelRight.add(lblMaxSalary);
		
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
		// TODO Auto-generated method stub
		JobsFrame jobFrame = new JobsFrame();
	}

}
