import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	public MainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("HR Otomasyon");
		this.setBounds(100, 100, 400, 350);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 11, 97, 21);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("TABLES");
		menuBar.add(mnNewMenu);
		
		JMenuItem mnNewMenuItem = new JMenuItem("Employees");
		mnNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeesFrame frm = new EmployeesFrame();
				frm.setVisible(true);
			}
		});
		mnNewMenu.add(mnNewMenuItem);
		
		JMenuItem mnNewMenuItem_1 = new JMenuItem("...");
		mnNewMenu.add(mnNewMenuItem_1);
		
		JMenuItem mnNewMenuItem_2 = new JMenuItem("...");
		mnNewMenu.add(mnNewMenuItem_2);
		
		JMenuItem mnNewMenuItem_3 = new JMenuItem("EXIT");
		mnNewMenu.add(mnNewMenuItem_3);
		this.setVisible(true);
	//			
	}
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}
}
