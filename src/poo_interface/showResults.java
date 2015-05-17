package poo_interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class showResults extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/** Creates a window, which will show the results of the executed program
	 *  
	 * @param BN matrix with the dynamic bayesian network
	 * @param size twice the number of variables
	 * @param sMDL score with MDL
	 * @param sLL score with LL
	 * @param names vector with names of the variables, in t and t+1
	 * @param infer inferred values
	 * @param var number of variables that the graph was inferred
	 */
	public showResults(int[][] BN, int size, double sMDL, double sLL,
			String[] names, int[][] infer, int var) {
		int i, j;
		int p;
		boolean comma;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 0;

		textArea.append("=== Inter-slice connectivity \n");
		for (j = 0; j < size / 2; j++) {
			p = j + size / 2;
			textArea.append(names[p] + " : ");
			comma = false;
			for (i = size / 2; i < size; i++) {
				if (BN[i][j] == 1) {
					if (comma) {
						textArea.append(", " + names[i]);
					} else {
						textArea.append(names[i]);
						comma = true;
					}

				}
			}
			textArea.append("\n");
		}
		
		textArea.append("\n");
		textArea.append("=== Intra-slice connectivity \n");
		
		for (j = 0; j < size / 2; j++) {
			p = j + size / 2;
			textArea.append(names[p] + " : ");
			comma = false;
			for (i = 0; i < size / 2; i++) {
				if (BN[i][j] == 1) {
					if (comma) {
						textArea.append(", " + names[i]);
					} else {
						textArea.append(names[i]);
						comma = true;
					}

				}
			}
			textArea.append("\n");
		}
		textArea.append("\n=== Scores \n");
		textArea.append("LL Score : " + sLL);
		textArea.append("\nMDL Score : " + sMDL + "\n");

		if (var == -1) {
			textArea.append("\n=== Inferred Values of all variables");
			for (i = 0; i < size / 2; i++) {
				textArea.append("\nInferred Value for " + i + " : ");
				for (j = 0; j < infer.length ; j++) {
					textArea.append(infer[j][i] + ", ");
				}
			}

		} else {
			textArea.append("\n=== Inferred Values for variable" + names[var]);
			for (i = 0; i < infer.length; i++) {
				textArea.append("Inferred Value for " + i + " : " + infer[i][0]);

			}
		}
		contentPane.add(sp, gbc_textArea_1);

		;
	}

}
