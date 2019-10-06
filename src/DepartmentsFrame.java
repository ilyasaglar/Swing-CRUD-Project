
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DepartmentsFrame extends JDialog {

	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Departments> depList = new ArrayList<>();
	private JList<Departments> jlist;
	private DefaultListModel<Departments> model;
	private JPanel panelLeft = new JPanel();
	private DepartmentsDAO depDao = new DepartmentsDAO();
	Departments d = new Departments();

	private JScrollPane scrollPane;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnInsert;
	private JButton btnCancel;
	private JButton btnSave, btnTemizle;
	private JTextField txtID;
	private JTextField txtDepName;
	private JTextField txtManagerID;
	private JTextField txtLocationID;
	private JLabel lblDepID;
	private JLabel lblDepName;
	private JLabel lblManagerID;
	private JLabel lblLocationID;
	private int islem;
	private Boolean sonuc;

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
		setResizable(false);
		setModal(true);
		this.setTitle("Departments");
		this.setBounds(200, 200, 655, 507);
		getContentPane().setLayout(null);

		// JPanel panelLeft = new JPanel();
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
		btnTemizle.setBounds(538, 430, 97, 30);
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
		btnInsert.setBounds(20, 430, 89, 30);
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
					JOptionPane.showMessageDialog(new JFrame(), "Listeden Department Seçimi Yapýnýz. ", "Dialog",
							JOptionPane.YES_NO_CANCEL_OPTION);

				}

			}
		});
		btnUpdate.setBounds(119, 430, 95, 30);
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
		btnDelete.setBounds(228, 430, 91, 30);
		btnList.add(btnDelete);
		getContentPane().add(btnDelete);

		btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.LEFT);
		btnSave.setIcon(new ImageIcon(this.getClass().getResource("icon/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				connection = DbConnection.getConnection();

				if (islem == 0) { // ýnsert iþlemi
					btnInsert.setEnabled(true);
					d.setDepartment_id(Integer.valueOf(txtID.getText().toString()));
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals("")) && bosDolu == true) {
						if (uzunKontrol == true) {

							Boolean IDnumbercontrol = numberControl(txtID.getText().toString());
							Boolean ManagerNumbercontrol = numberControl(txtManagerID.getText().toString());
							Boolean Locationcontrol = numberControl(txtLocationID.getText().toString());

							if (IDnumbercontrol == true && ManagerNumbercontrol == true && Locationcontrol == true) {
								d.setDepartment_id(Integer.valueOf(txtID.getText().toString()));
								degerGirisi();

								depDao.setDepartment(d);
								sonuc = depDao.insert();

								if (sonuc == true) {
									JOptionPane.showMessageDialog(new JFrame(), "Bilgiler Kaydedildi. ", "Sonuç",
											JOptionPane.YES_NO_CANCEL_OPTION);

								} else {

									JOptionPane.showMessageDialog(new JFrame(), "Bir hata oluþtu.", "Hata!",
											JOptionPane.ERROR_MESSAGE);

								}
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Geçersiz deðer girdiniz.", "Hata!",
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
					d.setDepartment_id(Integer.valueOf(txtID.getText().toString()));
					degerGirisi();
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();

					if (bosDolu = true) {
						if (uzunKontrol == true) {

							depDao.setDepartment(d);
							sonuc = depDao.update();

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

						sonuc = depDao.delete(Integer.valueOf(txtID.getText().toString()));

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(),
									txtID.getText().toString() + " ID'ye Ait Departments Bilgileri Silindi. ", "Sonuç",
									JOptionPane.YES_NO_CANCEL_OPTION);

						} else {
							JOptionPane.showMessageDialog(new JFrame(), " Bir hata oluþtu. ", "Sonuç",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), " Departments Seçimi Yapýnýz ", "Hata!",
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
		btnSave.setBounds(330, 430, 91, 30);
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
		btnCancel.setBounds(431, 430, 97, 30);
		btnList.add(btnCancel);
		btnCancel.setEnabled(false);
		getContentPane().add(btnCancel);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		DepartmentsFrame depFrame = new DepartmentsFrame();

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

		d.setDepartment_name(txtDepName.getText().toString());
		d.setLocation_id(Integer.valueOf(txtLocationID.getText().toString()));
		d.setManager_id(Integer.valueOf(txtManagerID.getText().toString()));

	}

	public Boolean uzunlukKontrol() {
		if (txtDepName.getText().length() <= 30 && txtLocationID.getText().toString().length() <= 6
				&& txtManagerID.getText().toString().length() <= 4) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean bosDoluKontrol() {
		if (!(txtDepName.getText().equals(""))) {
			return true;
		} else {
			return false;
		}
	}

}
