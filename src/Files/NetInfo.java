package Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetInfo {

	protected HashMap<Integer,List<Integer>> file_data;
	protected List<List<Integer>> paired_data;
	public int[][] matrix_data;
	public int[][] matrix_test;
	public int[] vectorR;
	public String[] VariableNames;
		
	protected int graph_size;
	protected FileIO io;
    protected int number_var;
	/** Initialize object of type NetInfo
	 * 
	 * 
	 */
	protected NetInfo(){
		file_data = new HashMap<Integer,List<Integer>>(); //nome-tempo-valor
		paired_data = new ArrayList<List<Integer>>();
		io = new FileIO();
		number_var = -1;
		graph_size = 0;
	}
	
	/** Creates a temporary list to store the BN data
	 * @param x the number variables of the train file
	 * 
	 */
	public void createBN(Integer x){
		for(int i = 0 ;i<x; i++){
			paired_data.add(new ArrayList<Integer>());
		}
	}
	/** Creates the matrix from where the dynamic network would be learned
	 
	 * @param lines number of "t" and "t+1" pairs
	 * @param col number of variables twice two 
	 *
	 */
	public void createMatrix(int lines, int col){
		matrix_data = new int [lines][col];
	}
	
	/** Creates a matrix to store the data read from the file test
	 * 
	 * @param lines number of lines minus 1 of the file test
	 * @param col number of variables of the file test
	 * 
	 */
	public void createTest(int lines,int col){
		matrix_test = new int[lines][col];
	}
	
	/** Creates and initializes the vector for the vector R
	 * 
	 * @param x number of variables 
	 * 
	 */
	public void createRvector(Integer x){
		vectorR = new int[2*x];
		for(int i = 0; i<2*x; i++)
			vectorR[i] = 0;
		
	}
	/** Verifies which is the biggest value for each variable 
	 * 
	 * @param x number of variables
	 * @param index number of variables 
	 * 
	 */
	public void calculateR(int x,int index){
		if(vectorR[index] < x){
			vectorR[index] = x + 1;
			vectorR[number_var+index] = x + 1;
		}
	}
	
	public void nodeNames(String line){
		String[] tmp = line.split(",");
		VariableNames = new String[tmp.length*2];
		for(int i = 0; i<tmp.length;i++){
			VariableNames[i] = tmp[i] + "_t";
			VariableNames[i+tmp.length] = tmp[i] + "_t+1";
		}
	}
	
	
}
