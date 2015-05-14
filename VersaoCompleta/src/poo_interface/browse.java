package poo_interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class browse extends JFrame {

	private JPanel contentPane;
	public String filepath;

	
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
			}catch(NullPointerException NotCSV){
				JOptionPane.showMessageDialog(contentPane, "File not a CSV","Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (result == JFileChooser.CANCEL_OPTION) {
			contentPane.setVisible(false);
			fileChooser.setVisible(false);
		}
		
	}
}
