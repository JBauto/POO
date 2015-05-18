package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class runGUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String train, test;
	private boolean n1, n2 = false;
	private int nrest,ntabu, nvar;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;

	/**Constructor that initializes the interface, that will get the necessary values 
	 * to run the program
	 * 
	 */
	public runGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final JLabel label = new JLabel("Path Unknown");
		label.setBounds(20, 200, 384, 23);
		contentPane.add(label);

		final JLabel label_1 = new JLabel("Path Unknown");
		label_1.setBounds(20, 77, 384, 23);
		contentPane.add(label_1);

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setBounds(299, 56, 125, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browse frame1 = new browse();
				train = frame1.filepath;
				label_1.setText(train);
				n1 = frame1.gotfile;
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				browse frame2 = new browse();
				test = frame2.filepath;
				label.setText(test);
				n2 = frame2.gotfile;
			}
		});
		button.setBounds(299, 180, 125, 23);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("Select Test:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 180, 70, 19);
		contentPane.add(lblNewLabel);

		JLabel lblSelectTrain = new JLabel("Select Train:");
		lblSelectTrain.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectTrain.setBounds(10, 58, 70, 19);
		contentPane.add(lblSelectTrain);

		JLabel lblWelcomeToThis = new JLabel(
				"Welcome to this amazing POO program!");
		lblWelcomeToThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThis.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeToThis.setBounds(10, 9, 414, 36);
		contentPane.add(lblWelcomeToThis);

		JLabel lblSelectMode = new JLabel("Select Mode:");
		lblSelectMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectMode.setBounds(10, 100, 82, 19);
		contentPane.add(lblSelectMode);

		JLabel lblNumberOfRandoms = new JLabel("Number of randoms restarts:");
		lblNumberOfRandoms.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumberOfRandoms.setBounds(170, 95, 173, 19);
		contentPane.add(lblNumberOfRandoms);

		textField_1 = new JTextField();
		textField_1.setBounds(350, 95, 41, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblVariableToIfer = new JLabel("Variable to Inferred:");
		lblVariableToIfer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVariableToIfer.setBounds(218, 145, 114, 19);
		contentPane.add(lblVariableToIfer);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(350, 145, 41, 20);
		contentPane.add(textField_2);

		// buttons
		JRadioButton rdbtnMdl = new JRadioButton("MDL");
		rdbtnMdl.setToolTipText("Minimum Description Length");
		rdbtnMdl.setSelected(true);
		rdbtnMdl.setBounds(20, 121, 109, 23);
		contentPane.add(rdbtnMdl);

		JRadioButton rdbtnLl = new JRadioButton("LL");
		rdbtnLl.setToolTipText("Log-Likelihood");
		rdbtnLl.setBounds(20, 144, 109, 23);
		contentPane.add(rdbtnLl);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMdl);
		group.add(rdbtnLl);

		JLabel lblNumberOfTries = new JLabel("Number of tries Tabu:");
		lblNumberOfTries.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumberOfTries.setBounds(211, 120, 125, 19);
		contentPane.add(lblNumberOfTries);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(350, 120, 41, 20);
		contentPane.add(textField);

		JButton btnLaunch = new JButton("LAUNCH!!!");
		btnLaunch.setForeground(Color.BLACK);
		btnLaunch.setBackground(Color.GRAY);
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (n1 & n2) {
					try {
						nrest = Integer.parseInt(textField_1.getText());
					} catch (NumberFormatException NotANumber) {
						JOptionPane.showMessageDialog(contentPane,"Total restarts not a number", "Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						
						ntabu=Integer.parseInt(textField.getText());
					}catch (NumberFormatException NotANumber){
						JOptionPane.showMessageDialog(contentPane,"Total tries not a number", "Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						if (textField_2.getText().length()!=0){
							nvar=Integer.parseInt(textField_2.getText());
						}else{
							nvar=-1;
						}
					}catch (NumberFormatException NotANumber){
						JOptionPane.showMessageDialog(contentPane,"Variable is not a number", "Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if (rdbtnLl.isSelected()) {
						try {
							Executer starter = new Executer();
							starter.run(train, test, 0, nrest, ntabu,nvar);
						    results(starter);
						} catch (IOException e1) {
						} catch (InvalidVar e1) {
							JOptionPane.showMessageDialog(contentPane,"Variable to infer doesn't exist", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						try {
							Executer starter = new Executer();
							starter.run(train, test, 1, nrest, ntabu,nvar);
						    results(starter);
						} catch (IOException e1) {
						} catch (InvalidVar e1) {
							JOptionPane.showMessageDialog(contentPane,"Variable to infer doesn't exist", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}

				}else{
					JOptionPane.showMessageDialog(contentPane,"Files aren't chosen", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLaunch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLaunch.setBounds(122, 227, 184, 23);
		contentPane.add(btnLaunch);
		
	}
	/** Method that displays the results in the interface
	 * 
	 * @param starter class with all the necessary values to print
	 */
	private void results (Executer starter){
		if(nrest != 0) {
	    	JOptionPane.showMessageDialog(contentPane,"Memory used = "+(starter.mem.totalMemory()-starter.mem.freeMemory())/1024 + " kB"
	    			+ "\n Execution Time : "+starter.elapsedTimeDBN+" ms (Average of = "+(starter.elapsedTimeDBN/nrest)+" ms)" 
	    			+"\n Inferred with DN: "+ starter.elapsedTimeInfer+" ms",
	    			"Calculation Successful",JOptionPane.INFORMATION_MESSAGE);
	    }else{
	    	JOptionPane.showMessageDialog(contentPane,"Memory used = "+(starter.mem.totalMemory()-starter.mem.freeMemory())/1024 + " kB"
	    			+ "\n Building DBN : "+starter.elapsedTimeDBN+" ms (Average of = "+(starter.elapsedTimeDBN)+" ms)"
	    			+"\n Inferred with DN: "+starter.elapsedTimeInfer+" ms"
	    			,"Calculation Successful",JOptionPane.INFORMATION_MESSAGE);
	    }
	    showResults end = new showResults (starter.matrix,starter.r.length,starter.score_mdlmax, starter.score_llmax,starter.names,starter.fut_values,nvar);
	    end.setVisible(true);
  
	}
	
}


