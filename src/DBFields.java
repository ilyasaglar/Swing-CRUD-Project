import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class DBFields extends JTextComponent{
	
	JTextField jt = new JTextField();
	
	
	String name;
	List<JTextField> list = new ArrayList<>();
		
	

	public List<JTextField> getFieldList() {
		
		return list;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
