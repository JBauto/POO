package poo_interface;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(args.length != 0){
					if (args[0].equals("-gui")){
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							runGUI frame = new runGUI();
							frame.setVisible(true);
							frame.setResizable(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
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