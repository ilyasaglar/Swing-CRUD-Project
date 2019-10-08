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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class CountriesFrame extends JDialog {

	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Countries> countryList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private CountriesDAO countryDao = new CountriesDAO();
	private JList<Countries> jlist;
	private DefaultListModel<Countries> model;
	private Countries c = new Countries();

	private JTextField txtID;
	private JTextField txtCountryName;
	private JTextField txtRegionID;
	private JLabel lblCountryID;
	private JLabel lblCountryName;
	private JLabel lblRegionID;
	private JScrollPane scrollPane;
	private JButton btnTemizle, btnUpdate, btnInsert, btnDelete, btnSave, btnCancel;
	private int islem;
	private Boolean sonuc;

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
		setResizable(false);
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
		txtID.setBounds(134, 15, 100, 20);
		panelRight.add(txtID);
		txtID.setColumns(10);
		jtList.add(txtID);

		txtCountryName = new JTextField();
		txtCountryName.setEditable(false);
		txtCountryName.setText("");
		txtCountryName.setBounds(134, 50, 100, 20);
		panelRight.add(txtCountryName);
		txtCountryName.setColumns(10);
		jtList.add(txtCountryName);

		txtRegionID = new JTextField();
		txtRegionID.setEditable(false);
		txtRegionID.setText("");
		txtRegionID.setBounds(134, 85, 100, 20);
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
		btnTemizle.setBounds(532, 432, 99, 30);
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
		btnInsert.setHorizontalAlignment(SwingConstants.LEFT);
		btnInsert.setBounds(8, 432, 89, 30);
		getContentPane().add(btnInsert);

		btnUpdate = new JButton("Update");
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setIcon(new ImageIcon(this.getClass().getResource("icon/reload.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (JTextField jt : jtList) {
					jt.setEditable(true);
				}
			
				JOptionPane.showMessageDialog(new JFrame(), "You have not authorized to update.", "Warning!",
						JOptionPane.YES_NO_CANCEL_OPTION);
				
				islem = 1;
				txtID.setEditable(false);
				btnInsert.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				if (txtID.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a Country from the list.", "Error!",
							JOptionPane.YES_NO_CANCEL_OPTION);

				}
				
				
			}
		});
		btnUpdate.setBounds(109, 432, 95, 30);
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
		btnDelete.setBounds(217, 432, 91, 30);
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

					c.setCountry_id(txtID.getText());
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals(""))) {
						if (uzunKontrol == true) {

							Boolean regionIDycontrol = numberControl(txtRegionID.getText().toString());

							if (regionIDycontrol == true) {
								c.setCountry_id(txtID.getText());
								degerGirisi();

								countryDao.setCountry(c);
								sonuc = countryDao.insert();

								if (sonuc == true) {
									JOptionPane.showMessageDialog(new JFrame(), "New Country is inserted", "Successful",
											JOptionPane.YES_NO_CANCEL_OPTION);

								} else {

									JOptionPane.showMessageDialog(new JFrame(), "An error occuredç", "Error!",
											JOptionPane.ERROR_MESSAGE);

								}
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "You entered an invalid value.", "Error!",
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
					c.setCountry_id(txtID.getText());
					degerGirisi();

					Boolean uzunKontrol = uzunlukKontrol();
					if (uzunKontrol == true) {

						countryDao.setCountry(c);
						sonuc = countryDao.update();

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(), "Country is updated.", "Successful",
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
				}

				else if (islem == 2) { // delete islemi
					if (!txtID.getText().equals("")) {

						sonuc = countryDao.delete2(txtID.getText());

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(),
									txtID.getText().toString() + " is deleted.", "Successful",
									JOptionPane.YES_NO_CANCEL_OPTION);

						} else {
							JOptionPane.showMessageDialog(new JFrame(), "An error occured. ", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Please select a Country.", "Error!",
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
		btnSave.setBounds(318, 432, 91, 30);
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
		btnCancel.setBounds(424, 432, 94, 30);
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

		c.setCountry_id(txtID.getText());
		c.setCountry_name(txtCountryName.getText());
		c.setRegion_id(Integer.valueOf(txtRegionID.getText().toString()));

	}

	public Boolean uzunlukKontrol() {
		if (txtID.getText().length() <= 2 && txtCountryName.getText().length() <= 40) {
			return true;
		} else {
			return false;
		}
	}

}
