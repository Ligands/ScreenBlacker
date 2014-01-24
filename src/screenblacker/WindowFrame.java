package screenblacker;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JWindow;

public class WindowFrame extends JWindow{
	private static final long serialVersionUID = 1L;
	private ScreenBlacker app;

	public WindowFrame(ScreenBlacker sb) {
		app = sb;
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(),
				GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight());
	    setLocationRelativeTo(null);
	    setAlwaysOnTop(true);
	    JPanel jPanel = new JPanel();
	    jPanel.setBackground(Color.black);
	    MouseListener ml = new MouseAdapter (){
	        public void mouseClicked (MouseEvent event){
	            app.toggleBlack();
	        }
	    };
	    jPanel.addMouseListener (ml);
	    getContentPane().add(jPanel);
	}

}
