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

public class LocationsFrame extends JDialog {
	
	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();	
	private List<Locations> locationList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private LocationsDAO locationDao = new LocationsDAO();
	private JList<Locations> jlist;
	private DefaultListModel<Locations> model;
	
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtID;
	private JTextField txtStreetAddress;
	private JTextField txtPostalCode;
	private JTextField txtCity;
	private JTextField txtStateProvince;
	private JTextField txtCountryID;
	private JLabel lblLocationID;
	private JLabel lblStreetAddress;
	private JLabel lblPostalCode;
	private JLabel lblCity;
	private JLabel lblStateProvince;
	private JLabel lblCountryID;
	private JScrollPane scrollPane;
	
	public void createJlist() {
		model = new DefaultListModel<Locations>();
		jlist = new JList<Locations>(model);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 241, 388);
		scrollPane.setViewportView(jlist);
		panelLeft.add(scrollPane);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		locationList = locationDao.getAllData();

		for (Locations locations : locationList) {
			model.addElement(locations);
		}
		
		jlist.getSelectionModel().addListSelectionListener(e -> {
			Locations location = jlist.getSelectedValue();

			txtID.setText(location.getLocation_id().toString());
			txtStreetAddress.setText(location.getStreet_address());
			txtPostalCode.setText(location.getPostal_code());
			txtCity.setText(location.getCity());
			txtStateProvince.setText(location.getState_province());
			txtCountryID.setText(location.getCountry_id());
		});
	}
	
	public LocationsFrame() {
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

		txtStreetAddress = new JTextField();
		txtStreetAddress.setEditable(false);
		txtStreetAddress.setText("");
		txtStreetAddress.setBounds(134, 50, 150, 20);
		panelRight.add(txtStreetAddress);
		txtStreetAddress.setColumns(10);
		jtList.add(txtStreetAddress);

		txtPostalCode = new JTextField();
		txtPostalCode.setEditable(false);
		txtPostalCode.setText("");
		txtPostalCode.setBounds(134, 85, 150, 20);
		panelRight.add(txtPostalCode);
		txtPostalCode.setColumns(10);
		jtList.add(txtPostalCode);

		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setText("");
		txtCity.setBounds(134, 120, 150, 20);
		panelRight.add(txtCity);
		txtCity.setColumns(10);
		jtList.add(txtCity);
		
		txtStateProvince = new JTextField();
		txtStateProvince.setEditable(false);
		txtStateProvince.setBounds(134, 155, 150, 20);
		panelRight.add(txtStateProvince);
		txtStateProvince.setColumns(10);
		jtList.add(txtStateProvince);

		txtCountryID = new JTextField();
		txtCountryID.setEditable(false);
		txtCountryID.setBounds(134, 190, 150, 20);
		panelRight.add(txtCountryID);
		txtCountryID.setColumns(10);
		jtList.add(txtCountryID);
		
		lblLocationID = new JLabel("Location ID");
		lblLocationID.setBounds(23, 21, 86, 14);
		panelRight.add(lblLocationID);

		lblStreetAddress = new JLabel("Street Address");
		lblStreetAddress.setBounds(23, 56, 86, 14);
		panelRight.add(lblStreetAddress);

		lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setBounds(23, 91, 86, 14);
		panelRight.add(lblPostalCode);

		lblCity = new JLabel("City");
		lblCity.setBounds(23, 126, 86, 14);
		panelRight.add(lblCity);
		
		lblStateProvince = new JLabel("State Province");
		lblStateProvince.setBounds(23, 161, 86, 14);
		panelRight.add(lblStateProvince);

		lblCountryID = new JLabel("Country ID");
		lblCountryID.setBounds(23, 196, 86, 14);
		panelRight.add(lblCountryID);
		
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
		LocationsFrame locationFrame = new LocationsFrame();

	}

}
