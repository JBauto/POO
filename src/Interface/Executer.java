package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Calc.calcMDL;
import Calc.calcTeta;
import Files.NetInfo;
import Files.Test;
import Files.Train;
import Grafo.grafo;
import Inference.infer;

public class Executer{
	
	int[][] matrix;
	int [] r;
	double score_mdlmax;
	double score_llmax;
	String[] names;
	int[][] fut_values;
	long elapsedTimeInfer;
	long elapsedTimeDBN;
	Runtime mem;
	
	/** Method that will run the program
	 * 
	 * @param train full path of the chosen train file
	 * @param test full path of the chosen test file
	 * @param mode integer that has the chosen mode to run, MDL or LL
	 * @param restarts integer that has the number of restarts to execute
	 * @param ntabu integer that has the number of tabu to run
	 * @param var integer that has the variable to infer
	 * @throws InvalidVar 
	 * 
	 */
	protected void  run(String train, String test, int mode, int restarts, int ntabu, int var)throws IOException, InvalidVar {


	    long startTime = System.currentTimeMillis();
		
		grafo graph = new grafo();
		NetInfo teste = new Train();
		NetInfo file = new Test();
		((Train) teste).readTrain(train);	
		((Test) file).readTest(test);
		int i,j;
		int Data[][] = teste.matrix_data;

		int nr_rdm = restarts; 
		names = file.VariableNames;
		
		if (var >= names.length/2){
			throw new InvalidVar();
		}

		r = teste.vectorR;
		calcMDL scmdl = new calcMDL();
		calcTeta tt = new calcTeta();
		matrix= new int[r.length][(r.length)/2];
		List<double[]> tetas = new ArrayList<double[]>();
		matrix = graph.createGrafo(Data, r, mode, nr_rdm, ntabu);
		score_llmax = scmdl.LL(Data, matrix, r);
		score_mdlmax = scmdl.MDL(Data, matrix, r);
		long stopTime = System.currentTimeMillis();
	    elapsedTimeDBN = stopTime - startTime;
	    
	    startTime = System.currentTimeMillis();
		tetas = tt.tetas(Data, matrix, r);
		
		fut_values = new int [file.matrix_test.length][r.length/2];
		
		infer guess = new infer();
		
		if (var == -1) {
			for (j = 0; j < r.length / 2; j++) {
				for (i = 0; i < file.matrix_test.length; i++) {
					fut_values[i][j] = guess.inf(file.matrix_test, matrix, r, j+ r.length / 2, tetas, i);
				}
			}
		} else {
			for (i = 0; i < file.matrix_test.length; i++) {
				fut_values[i][0] = guess.inf(file.matrix_test, matrix, r,var + r.length / 2, tetas, i);
			}
		}
		
		System.out.println();
		stopTime = System.currentTimeMillis();
	    elapsedTimeInfer = stopTime - startTime;

	    mem = Runtime.getRuntime();
	    
	   }
}
