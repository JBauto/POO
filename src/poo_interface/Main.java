package poo_interface;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		/** Main is only used to check if the program is needed to run in the console
		 * or in the interface
		 * 
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (args[0].equals("-gui")){
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						runGUI frame = new runGUI();
						frame.setVisible(true);
						frame.setResizable(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						@SuppressWarnings("unused")
						runConsole runner = new runConsole(args);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}	
				
			}
		});
	}
}