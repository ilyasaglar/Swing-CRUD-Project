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

public class CountriesFrame extends JDialog {
	
	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();	
	private List<Countries> countryList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private CountriesDAO countryDao = new CountriesDAO();
	private JList<Countries> jlist;
	private DefaultListModel<Countries> model;
	
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtID;
	private JTextField txtCountryName;
	private JTextField txtRegionID;
	private JLabel lblCountryID;
	private JLabel lblCountryName;
	private JLabel lblRegionID;
	private JScrollPane scrollPane;
	
	public void createJlist() {
		model = new DefaultListModel<Countries>();
		jlist = new JList<Countries>(model);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		scrollPane.setViewportView(jlist);
		panelLeft.add(scrollPane);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryList = countryDao.getAllData();

		for (Countries countries : countryList) {
			model.addElement(countries);
		}
		
		jlist.getSelectionModel().addListSelectionListener(e -> {
			Countries country = jlist.getSelectedValue();
			
			txtID.setText(country.getCountry_id());
			txtCountryName.setText(country.getCountry_name());
			txtRegionID.setText(country.getRegion_id().toString());
		});
	}
	
	public CountriesFrame() {
		setModal(true);
		this.setTitle("Countries");
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
		txtID.setBounds(134, 15, 86, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		jtList.add(txtID);

		txtCountryName = new JTextField();
		txtCountryName.setEditable(false);
		txtCountryName.setText("");
		txtCountryName.setBounds(134, 50, 86, 20);
		panelRight.add(txtCountryName);
		txtCountryName.setColumns(10);
		jtList.add(txtCountryName);

		txtRegionID = new JTextField();
		txtRegionID.setEditable(false);
		txtRegionID.setText("");
		txtRegionID.setBounds(134, 85, 86, 20);
		panelRight.add(txtRegionID);
		txtRegionID.setColumns(10);
		jtList.add(txtRegionID);
		
		lblCountryID = new JLabel("Country ID");
		lblCountryID.setBounds(23, 21, 86, 14);
		panelRight.add(lblCountryID);

		lblCountryName = new JLabel("Country Name");
		lblCountryName.setBounds(23, 56, 86, 14);
		panelRight.add(lblCountryName);

		lblRegionID = new JLabel("Region ID");
		lblRegionID.setBounds(23, 91, 86, 14);
		panelRight.add(lblRegionID);
		
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
		CountriesFrame countryFrame = new CountriesFrame();
	}

}
