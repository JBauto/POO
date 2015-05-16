package poo_interface;

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
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import Calc.calcMDL;
import Calc.calcTeta;
import Files.NetInfo;
import Files.Test;
import Files.Train;
import Grafo.grafo;
import Inference.infer;

public class runGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String b1, b2;
	private boolean n1, n2 = false;
	private int nrest,ntabu, nvar;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;

	
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
				b1 = frame1.filepath;
				label_1.setText(b1);
				n1 = true;
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				browse frame2 = new browse();
				b2 = frame2.filepath;
				label.setText(b2);
				n2 = true;
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

		final JRadioButton rdbtnLl = new JRadioButton("LL");
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
							start(b1, b2, 0, nrest, ntabu,nvar);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						try {
							start(b1, b2, 1, nrest,ntabu,nvar);
						} catch (IOException e1) {
							e1.printStackTrace();
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
	

	private void start(String train, String test, int mode, int restarts, int ntabu, int var)throws IOException {


	    long startTime = System.currentTimeMillis();
		
		grafo graph = new grafo();
		NetInfo teste = new Train();
		NetInfo file = new Test();
		((Train) teste).readTrain(train);	
		((Test) file).readTest(test);
		int i,j;
		int Data[][] = teste.matrix_data;

		int nr_rdm = restarts; 
		String [] names = file.VariableNames;
		
		if (var >= names.length/2){
			JOptionPane.showMessageDialog(contentPane,"Variable to infer doesn't exist", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}

		int[] r = teste.vectorR;
		//System.out.println("1");
		calcMDL scmdl = new calcMDL();
		calcTeta tt = new calcTeta();
		//System.out.println("2");
		int [][] mat1= new int[r.length][(r.length)/2];
		double score_llmax, score_mdlmax;
		List<double[]> tetas = new ArrayList<double[]>();
		//System.out.println("3");
		mat1 = graph.createGrafo(Data, r, mode, nr_rdm, ntabu);
		//System.out.println("4");
		score_llmax = scmdl.LL(Data, mat1, r);
//		System.out.println(score_llmax);
		//System.out.println("5");
		score_mdlmax = scmdl.MDL(Data, mat1, r);
		//System.out.println("6");		
		long stopTime = System.currentTimeMillis();
	    long elapsedTimeDBN = stopTime - startTime;
	    
	    startTime = System.currentTimeMillis();
		tetas = tt.tetas(Data, mat1, r);
		
		int [][] fut_values = new int [file.matrix_test.length][r.length/2];
		int var_to_guess = var; 
		
		infer guess = new infer();
		
		if (var_to_guess == -1) {
			for (j = 0; j < r.length / 2; j++) {
				for (i = 0; i < file.matrix_test.length; i++) {
					fut_values[i][j] = guess.inf(file.matrix_test, mat1, r, j+ r.length / 2, tetas, i);
				}
			}
		} else {
			for (i = 0; i < file.matrix_test.length; i++) {
				fut_values[i][0] = guess.inf(file.matrix_test, mat1, r,var_to_guess + r.length / 2, tetas, i);
			}
		}
		
		System.out.println();
		stopTime = System.currentTimeMillis();
	    long elapsedTimeInfer = stopTime - startTime;


	    Runtime t = Runtime.getRuntime();
	    if(nr_rdm != 0) {
	    	JOptionPane.showMessageDialog(contentPane,"Memory used = "+(t.totalMemory()-t.freeMemory())/1024 + " kB"
	    			+ "\n Execution Time : "+elapsedTimeDBN+" ms (Average of = "+(elapsedTimeDBN/nr_rdm)+" ms)" 
	    			+"\n Inferred with DN: "+ elapsedTimeInfer+" ms",
	    			"Calculation Successful",JOptionPane.INFORMATION_MESSAGE);
	    }else{
	    	JOptionPane.showMessageDialog(contentPane,"Memory used = "+(t.totalMemory()-t.freeMemory())/1024 + " kB"
	    			+ "\n Building DBN : "+elapsedTimeDBN+" ms (Average of = "+(elapsedTimeDBN)+" ms)"
	    			+"\n Inferred with DN: "+elapsedTimeInfer+" ms"
	    			,"Calculation Successful",JOptionPane.INFORMATION_MESSAGE);
	    }
	    showResults end = new showResults (mat1,r.length,score_mdlmax, score_llmax,names,fut_values,var);
	    end.setVisible(true);
  
	    

	   }
	}


