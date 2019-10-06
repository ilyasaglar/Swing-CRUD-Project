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

public class RegionsFrame extends JDialog {

	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Regions> regionList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private RegionsDAO regionDao = new RegionsDAO();
	private Regions r = new Regions();
	private JList<Regions> jlist;
	private DefaultListModel<Regions> model;
	private JTextField txtID;
	private JTextField txtRegionName;
	private JLabel lblRegionID;
	private JLabel lblRegionName;
	private JScrollPane scrollPane;
	private JButton btnTemizle;
	private JButton btnCancel;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnInsert;
	private int islem;
	private Boolean sonuc;

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
		btnTemizle.setBounds(533, 430, 98, 30);
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
		btnInsert.setBounds(16, 430, 89, 30);
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
					JOptionPane.showMessageDialog(new JFrame(), "Listeden Region Seçimi Yapýnýz. ", "Dialog",
							JOptionPane.YES_NO_CANCEL_OPTION);

				}

			}
		});
		btnUpdate.setBounds(115, 430, 95, 30);
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
		btnDelete.setBounds(223, 430, 91, 30);
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
					r.setRegion_id(Integer.valueOf(txtID.getText().toString()));
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals(""))) {
						if (uzunKontrol == true) {

							Boolean IDnumbercontrol = numberControl(txtID.getText().toString());

							if (IDnumbercontrol == true) {
								r.setRegion_id(Integer.valueOf(txtID.getText().toString()));
								degerGirisi();

								regionDao.setJob(r);
								sonuc = regionDao.update();

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
					r.setRegion_id(Integer.valueOf(txtID.getText().toString()));
					degerGirisi();
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals(""))) {
						if (uzunKontrol == true) {

							regionDao.setJob(r);
							sonuc = regionDao.update();

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

						sonuc = regionDao.delete(Integer.valueOf(txtID.getText().toString()));

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(),
									txtID.getText().toString() + " ID'ye Ait REgions Bilgileri Silindi. ", "Sonuç",
									JOptionPane.YES_NO_CANCEL_OPTION);

						} else {
							JOptionPane.showMessageDialog(new JFrame(), " Bir hata oluþtu. ", "Sonuç",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), " Region Seçimi Yapýnýz ", "Hata!",
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
		btnSave.setBounds(325, 430, 91, 30);
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
		btnCancel.setBounds(426, 430, 95, 30);
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

		r.setRegion_id(Integer.valueOf(txtID.getText().toString()));
		r.setRegion_name(txtRegionName.getText());

	}

	public Boolean uzunlukKontrol() {
		if (txtRegionName.getText().toString().length() <= 25) {
			return true;
		} else {
			return false;
		}
	}

}
