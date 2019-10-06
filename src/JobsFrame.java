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

public class JobsFrame extends JDialog {

	private static Connection connection = DbConnection.getConnection();
	private List<JButton> btnList = new ArrayList<>();
	private List<JTextField> jtList = new ArrayList<>();
	private List<Jobs> jobList = new ArrayList<>();
	private JPanel panelLeft = new JPanel();
	private JobsDAO jobDao = new JobsDAO();
	private JList<Jobs> jlist;
	private DefaultListModel<Jobs> model;
	private Jobs j = new Jobs();

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
	private int islem;
	private Boolean sonuc;

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

		JButton btnTemizle = new JButton("Temizle");
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
		btnInsert.setBounds(10, 432, 89, 30);
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
				btnInsert.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);

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

					j.setJob_id(txtID.getText());
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();

					if (!(txtID.getText().equals("")) && bosDolu == true) {
						if (uzunKontrol == true) {

							
							Boolean minSalarycontrol = numberControl(txtMinSalary.getText().toString());
							Boolean maxSalarycontrol = numberControl(txtMaxSalary.getText().toString());

							if (minSalarycontrol == true && maxSalarycontrol == true ) {
								j.setJob_id(txtID.getText());
								degerGirisi();

								jobDao.setJob(j);
								sonuc = jobDao.insert();

								if (sonuc == true) {
									JOptionPane.showMessageDialog(new JFrame(), "Ýþ Bilgileri Kaydedildi. ", "Sonuç",
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

					j.setJob_id(txtID.getText());
					degerGirisi();
					Boolean bosDolu = bosDoluKontrol();
					Boolean uzunKontrol = uzunlukKontrol();
					if (bosDolu = true) {
						if (uzunKontrol == true) {

							jobDao.setJob(j);
							sonuc = jobDao.update();

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
					if (!txtID.getText().equals("")) {

						sonuc = jobDao.delete(Integer.valueOf(txtID.getText()));

						if (sonuc == true) {
							JOptionPane.showMessageDialog(new JFrame(),
									txtID.getText().toString() + " ID'ye Ait Jobs Bilgileri Silindi. ",
									"Sonuç", JOptionPane.YES_NO_CANCEL_OPTION);

						} else {
							JOptionPane.showMessageDialog(new JFrame(), " Bir hata oluþtu. ", "Sonuç",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(), " Jobs Seçimi Yapýnýz ", "Hata!",
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

		j.setJob_title(txtJobTitle.getText());
		j.setMin_salary(Integer.valueOf(txtMinSalary.getText().toString()));
		j.setMax_salary(Integer.valueOf(txtMaxSalary.getText().toString()));

	}

	public Boolean uzunlukKontrol() {
		if (txtJobTitle.getText().length() <= 35) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean bosDoluKontrol() {
		if (!(txtJobTitle.getText().equals(""))) {
			return true;
		} else {
			return false;
		}
	}

}
