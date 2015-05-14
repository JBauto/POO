package poo_interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class showResults extends JFrame {

	private JPanel contentPane;
	
	public showResults(int [][] BN, int size, double sMDL, double sLL, String [] names) {
		int i,j;
		int p,s;
		boolean comma;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(5, 5, 600, 600);
		textArea.append("=== Inter-slice connectivity \n");
		for (i=0;i<size/2;i++){
			textArea.append(names[i] + " : ");
			comma = false;
			for (j=0;j<size/2;j++){
				if (BN[i][j]==1){
					p=j+size/2;
					if (comma){ textArea.append(", "+names[p]);
					}else{ 
						textArea.append(names[p]);
						comma=true;
					}
					
					
				}
			}
			textArea.append("\n");
		}
		textArea.append("\n");
		textArea.append("=== Intra-slice connectivity \n");

		for (i=size/2;i<size;i++){
			textArea.append(names[i] + " : ");
			for (j=0;j<size/2;j++){
				comma=false;
				if (BN[i][j]==1){
					p=j+size/2;
					if (comma){ textArea.append(", "+names[p]);
					}else{
						textArea.append(names[p]);
						comma=true;
					}
					
				}
			}
			textArea.append("\n");
		}
		textArea.append("\n=== Scores \n");
		textArea.append("LL Score : "+ sLL);
	
		textArea.append("\nMDL Score : "+ sMDL);
		
		
		contentPane.add(textArea);
;	}

}
