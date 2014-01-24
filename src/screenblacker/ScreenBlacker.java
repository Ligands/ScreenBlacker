package screenblacker;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JWindow;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class ScreenBlacker {
	ConfigFrame config = new ConfigFrame();
	WindowFrame[] blacks;
	private JIntellitype jint;
	
	public ScreenBlacker() {
		GraphicsDevice[] screens = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getScreenDevices();
		blacks = new WindowFrame[screens.length]; 
		for(int i = 0; i < screens.length; i++){
			blacks[i] = new WindowFrame(this);
			showOnScreen(i, blacks[i]);
		}
		
		// register global hotkey
		jint = JIntellitype.getInstance();
		jint.registerHotKey(1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int)'K');
		jint.addHotKeyListener(new HotkeyListener() {
			public void onHotKey(int key) {if(key == 1) toggleBlack();}});
		
		createTrayIcon();
	}
	
	private boolean state = false;
	public void toggleBlack(){
		state = !state;
		for(int i = 0; i < blacks.length; i++){
			if(config.showOn[i])
				blacks[i].setVisible(state);
		}
	}
	
 	public static void main(String[] args) {
		new ScreenBlacker();
	}

 	public static void showOnScreen(int screen, JWindow black) {
 	    GraphicsDevice[] gd = GraphicsEnvironment
 	    		.getLocalGraphicsEnvironment().getScreenDevices();
 	    if(screen > -1 && screen < gd.length) {
 	    	black.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, black.getY());
 	    } else if(gd.length > 0) {
 	    	black.setLocation(gd[0].getDefaultConfiguration().getBounds().x, black.getY());
 	    } else {
 	        throw new RuntimeException("No Screens Found");
 	    }
 	}
 	
 	public void exit(){
 		for(WindowFrame window : blacks)
 			window.dispose();
 		jint.cleanUp();
 		System.exit(0);
 	}
 	
 	public void createTrayIcon(){
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
	        		toggleBlack();
	        	} else if(c.equals("Configure")){
	        		config.setVisible(true);
	        	} else if(c.equals("Exit")){
	        		exit();
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
}
