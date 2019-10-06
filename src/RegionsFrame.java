import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class RegionsFrame extends JDialog {
	
	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Regions> regionList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private RegionsDAO regionDao = new RegionsDAO();
	private JList<Regions> jlist;
	private DefaultListModel<Regions> model;
	
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtID;
	private JTextField txtRegionName;
	private JLabel lblRegionID;
	private JLabel lblRegionName;
	private JScrollPane scrollPane;
	public int islem = -1;
	
	public void createJlist() {
		model = new DefaultListModel<Regions>();
		jlist = new JList<Regions>(model);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		scrollPane.setViewportView(jlist);
		panelLeft.add(scrollPane);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		regionList = regionDao.getAllData();

		for (Regions regions : regionList) {
			model.addElement(regions);
		}
		
		jlist.getSelectionModel().addListSelectionListener(e -> {
			Regions region = jlist.getSelectedValue();

			txtID.setText(region.getRegion_id().toString());
			txtRegionName.setText(region.getRegion_name());
			
		});
	}
	
	public RegionsFrame() {
		setModal(true);
		this.setTitle("Locations");
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
		txtID.setBounds(134, 15, 150, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		jtList.add(txtID);

		txtRegionName = new JTextField();
		txtRegionName.setEditable(false);
		txtRegionName.setText("");
		txtRegionName.setBounds(134, 50, 150, 20);
		panelRight.add(txtRegionName);
		txtRegionName.setColumns(10);
		jtList.add(txtRegionName);
		
		lblRegionID = new JLabel("Location ID");
		lblRegionID.setBounds(23, 21, 86, 14);
		panelRight.add(lblRegionID);

		lblRegionName = new JLabel("Street Address");
		lblRegionName.setBounds(23, 56, 86, 14);
		panelRight.add(lblRegionName);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(129, 434, 89, 23);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setEditable(true);
					jt.setText(null);
				}
				islem = 0;
				btnUpdate.setEnabled(false);
				btnDelete.setEnabled(false);
				//btnSearch.setVisible(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);

			}
		});
		btnList.add(btnInsert);
		getContentPane().add(btnInsert);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JTextField jt : jtList) {
					jt.setEditable(true);
				}
				islem = 1;
				txtID.setEditable(false);
				btnInsert.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);

				//createJlist();
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
		RegionsFrame regionFrame = new RegionsFrame();

	}

}
