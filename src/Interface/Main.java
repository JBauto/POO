package Interface;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

public class Main {

	public static void main(final String[] args) {
		/** Main is only used to check if the program is needed to run in the console
		 * or in the interface
		 * 
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(args.length!=0){
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
				}else{
					System.out.println("Too few arguments.");
					System.out.println("Usage: jar -jar <<JAR-NAME>>.jar <<TRAIN-NAME>>.csv <<TEST-NAME>>.csv <<MDL/LL>> <<NUMBER-OF-RANDOM-RESTARTS>> <<VAR-TO-INFER>> ");
					System.exit(-1);
				}
			}
		});
	}
}