package Interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class browse extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String filepath;
	boolean gotfile;

	/** Constructor that builds a window with a browser of files, that will set values
	 * to the fields filepath (string with full path of the file chosen) and gotfile
	 * that is true if a file has chosen and false, otherwise.
	 */
	public browse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setVisible(true);
		
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		fileChooser.setFileFilter(filter);
		
	    
		contentPane.add(fileChooser, BorderLayout.NORTH);
		if (result == JFileChooser.APPROVE_OPTION) { 
			try{
				filepath=fileChooser.getSelectedFile().getAbsolutePath();
				gotfile = true;
			}catch(NullPointerException NotCSV){
				JOptionPane.showMessageDialog(contentPane, "File not a CSV","Error", JOptionPane.ERROR_MESSAGE);
				gotfile = false;
			}

		} else if (result == JFileChooser.CANCEL_OPTION) {
			gotfile = false;
			contentPane.setVisible(false);
			fileChooser.setVisible(false);
		}
		
	}
}
