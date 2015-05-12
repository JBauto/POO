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
		
	protected int graph_size;
	protected FileIO io;
    protected int number_var;
	
	protected NetInfo(){
		file_data = new HashMap<Integer,List<Integer>>(); //nome-tempo-valor
		paired_data = new ArrayList<List<Integer>>();
		io = new FileIO();
		number_var = -1;
		graph_size = 0;
	}
	
	public void createBN(Integer x){
		for(int i = 0 ;i<x; i++){
			paired_data.add(new ArrayList<Integer>());
		}
	}
		
	public void createMatrix(int lines, int col){
		matrix_data = new int [lines][col];
	}
	
	public void createTest(int lines,int col){
		matrix_test = new int[lines][col];
	}
	
	public void createRvector(Integer x){
		vectorR = new int[2*x];
		for(int i = 0; i<2*x; i++)
			vectorR[i] = 0;
		
	}
	
	public void calculateR(int x,int index){
		if(vectorR[index] < x){
			vectorR[index] = x + 1;
			vectorR[number_var+index] = x + 1;
		}
	}
	
	
}
