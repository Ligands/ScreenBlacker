package screenblacker;

import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ConfigFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private final File settingsFile = new File("nigger.ini");
	public boolean[] showOn;

	public ConfigFrame() {
		setTitle("Config");
		//setSize(600, 600);
		setLocationByPlatform(true);
		JPanel panel = new JPanel(new FlowLayout());
		
		GraphicsDevice[] screens = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getScreenDevices();
		showOn = new boolean[screens.length];
		loadSettings(settingsFile);
		for(int i = 0; i < screens.length; i++){
			JToggleButton but = new JToggleButton((i+1)+"", showOn[i]);
			but.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int n = Integer.parseInt(((JToggleButton)e.getItem()).getText())-1;
					boolean tog = (e.getStateChange() == ItemEvent.SELECTED);
					showOn[n] = tog;
					updateSettings(settingsFile);
				}
			});
			panel.add(but);
		}
		
		getContentPane().add(panel);
		pack();
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	void updateSettings(File f){
		PrintWriter writer = null;
		try {writer = new PrintWriter(f, "UTF-8");
		} catch (Exception e) {e.printStackTrace();}
		for(boolean b : showOn){
			writer.print(Boolean.toString(b)+" ");
		}
		writer.close();
	}
	
	void loadSettings(File f){
		try { 
			// load settings
			Scanner sc = null;
			sc = new Scanner(f);
			
			for(int i = 0; i < showOn.length; i++) {
				showOn[i] = sc.nextBoolean();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("settings file not found.");
		}
	}

}
