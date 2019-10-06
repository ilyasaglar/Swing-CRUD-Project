import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class LocationsFrame extends JDialog {

	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Locations> locationList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private LocationsDAO locationDao = new LocationsDAO();
	private Locations l = new Locations();
	private JList<Locations> jlist;
	private DefaultListModel<Locations> model;
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
	private JButton btnUpdate;
	private JButton btnTemizle;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave;
	private int islem;
	private Boolean sonuc;

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
		setResizable(false);
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

		btnTemizle = new JButton("Temizle");
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
		btnTemizle.setBounds(536, 430, 99, 30);
		getContentPane().add(btnTemizle);

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
			public void actionPerformed(ActionEvent e) {

				for (JTextField jt : jtList) {
					jt.setEditable(true);
				}

				islem = 1;
				txtID.setEditable(false);
				btnInsert.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				if (txtID.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a Department from the list.", "Warning",
							JOptionPane.YES_NO_CANCEL_OPTION);

				}

			}
		});
		btnUpdate.setBounds(109, 430, 95, 30);
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

			}
		});
		btnDelete.setBounds(219, 430, 91, 30);
		btnList.add(btnDelete);
		getContentPane().add(btnDelete);

		btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(this.getClass().getResource("icon/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				connection = DbConnection.getConnection();
				for (JButton btn : btnList) {
					if (btn.isEnabled() == false) {
						btn.setEnabled(true);
					}
				}

				if (islem == 0) { // ýnsert iþlemi

					l.setLocation_id(Integer.valueOf(txtID.getText().toString()));
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals("")) && bosDolu == true) {
						if (uzunKontrol == true) {

							Boolean IDnumbercontrol = numberControl(txtID.getText().toString());

							if (IDnumbercontrol == true) {

								l.setLocation_id(Integer.valueOf(txtID.getText().toString()));
								degerGirisi();

								locationDao.setLocation(l);
								sonuc = locationDao.insert();

								if (sonuc == true) {
									JOptionPane.showMessageDialog(new JFrame(), "New Location is inserted.", "Successful",
											JOptionPane.YES_NO_CANCEL_OPTION);

								} else {

									JOptionPane.showMessageDialog(new JFrame(), "An error occured", "Error!",
											JOptionPane.ERROR_MESSAGE);

								}
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "You have entered an invalid value.", "Error!",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"You have exceeded the maximum field length, please try again.", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields.", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				else if (islem == 1) { // update islemi

					
					l.setLocation_id(Integer.valueOf(txtID.getText().toString()));
					degerGirisi();
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();
					if (bosDolu = true) {
						if (uzunKontrol == true) {

							locationDao.setLocation(l);
							sonuc = locationDao.insert();

							if (sonuc == true) {
								JOptionPane.showMessageDialog(new JFrame(), "Location is updated.", "Successful",
										JOptionPane.YES_NO_CANCEL_OPTION);

							} else {
								JOptionPane.showMessageDialog(new JFrame(), "An error occured.", "Error!",
										JOptionPane.ERROR_MESSAGE);

							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(),
									"You have exceeded the maximum field length, please try again.", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields.", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				else if (islem == 2) { // delete islemi
					if (!txtID.getText().equals("")) {

						sonuc = locationDao.delete(Integer.valueOf(txtID.getText().toString()));

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(),
									txtID.getText().toString() + " is deleted.", "Successful",
									JOptionPane.YES_NO_CANCEL_OPTION);

						} else {
							JOptionPane.showMessageDialog(new JFrame(), "An error occured.", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Please select a Location.", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					btnSave.setEnabled(false);
					btnCancel.setEnabled(false);
				}

				try {
					connection.close();
				} catch (SQLException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}

			}
		});
		btnSave.setBounds(322, 430, 91, 30);
		btnList.add(btnSave);
		getContentPane().add(btnSave);
		btnSave.setEnabled(false);

		btnCancel = new JButton("Cancel");
		btnCancel.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("icon/cancel.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInsert.setEnabled(true);
				btnDelete.setEnabled(true);
				btnUpdate.setEnabled(true);
				for (JTextField jt : jtList) {
					jt.setEditable(false);
				}

				try {
					connection.close();
				} catch (SQLException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
			}
		});
		btnCancel.setBounds(426, 430, 97, 30);
		btnList.add(btnCancel);
		btnCancel.setEnabled(false);
		getContentPane().add(btnCancel);


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

		l.setLocation_id(Integer.valueOf(txtID.getText().toString()));
		l.setStreet_address(txtStreetAddress.getText());
		l.setPostal_code(txtPostalCode.getText());
		l.setCity(txtCity.getText());
		l.setCountry_id(txtCountryID.getText());
		l.setState_province(txtStateProvince.getText());

	}

	public Boolean uzunlukKontrol() {
		if (txtID.getText().toString().length() <= 4 && txtStreetAddress.getText().length() <= 40
				&& txtPostalCode.getText().length() <= 12 && txtCity.getText().length() <= 30
				&& txtStateProvince.getText().length() <= 25 && txtCountryID.getText().length() <= 2) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean bosDoluKontrol() {
		if (!(txtID.getText().equals("")) && !(txtCity.getText().equals(""))) {
			return true;
		} else {
			return false;
		}
	}

}
