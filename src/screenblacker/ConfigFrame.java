package screenblacker;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConfigFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public ConfigFrame() {
		setTitle("Config");
		//setSize(600, 600);
		setLocationByPlatform(true);
		JPanel panel = new JPanel(new BorderLayout());
		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		
		panel.add(b1,BorderLayout.WEST);
		panel.add(b2,BorderLayout.CENTER);
		panel.add(b3,BorderLayout.EAST);
		getContentPane().add(panel);
		pack();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
