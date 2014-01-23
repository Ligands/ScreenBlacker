package screenblacker;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public WindowFrame() {
		setTitle("Screen Niggifier");
	    setSize(600, 600);
	    setLocationRelativeTo(null);
	    setAlwaysOnTop(true);
	    setUndecorated(true);
	    setExtendedState(JFrame.ICONIFIED);
	    JPanel jPanel = new JPanel();
	    jPanel.setBackground(Color.black);
	    MouseListener ml = new MouseAdapter (){
	        public void mouseClicked (MouseEvent event){
	            //Put JFrame close code here
	            dispose();
	            System.exit(0);
	        }
	    };
	    jPanel.addMouseListener (ml);
	    getContentPane().add(jPanel);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
