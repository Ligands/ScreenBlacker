package screenblacker;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenBlacker {

	public ScreenBlacker() {
		final WindowFrame frame = new WindowFrame();
		frame.setVisible(true);
		
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported.");
            return;
        }
		
	    PopupMenu popup = new PopupMenu();
	    // create menu item for the default action
	    MenuItem item = new MenuItem("Configure");
	    ActionListener listener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String c = e.getActionCommand();
	        	if(c == null){
	        		// execute default action
	        	} else if(c.equals("Configure")){
	        		new ConfigFrame();
	        	} else if(c.equals("Exit")){
	        		frame.dispose();
	        		System.exit(0);
	        	}
	        }
	    };
	    item.addActionListener(listener);
	    popup.add(item);
	    item = new MenuItem("Exit");
	    item.addActionListener(listener);
	    popup.add(item);
	    
		// load an image
	    Image image = Toolkit.getDefaultToolkit().getImage("icon.png");
		TrayIcon trayIcon = new TrayIcon(image, "Screen Niggifier", popup);
		trayIcon.addActionListener(listener);
		
		try { SystemTray.getSystemTray().add(trayIcon);
		} catch (AWTException e) { e.printStackTrace(); }
		
	}
	
	public static void main(String[] args) {
		new ScreenBlacker();
	}

}
