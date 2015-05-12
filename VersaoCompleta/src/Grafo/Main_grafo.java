package Grafo;

import Calc.*;
import Inference.infer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Files.*;

public class Main_grafo {

	public static void main(String[] args) throws IOException {


	    long startTime = System.currentTimeMillis();
		
		grafo graph = new grafo();
		Train teste = new Train();
		Test file = new Test();
		teste.readTrain("train-data.csv");	// adaptar pa receber da UI?
		file.readTest("test-data.csv");
		int i,j;
		int Data[][] = teste.matrix_data;
		int nr_rdm = 100; //parametro a receber
				

		int[] r = teste.vectorR;
		calcMDL scmdl = new calcMDL();
		calcTeta tt = new calcTeta();
		
		int [][] mat2= new int[r.length][(r.length)/2];
		int [][] mat1= new int[r.length][(r.length)/2];
		double score_llmax, score_mdlmax;
		List<double[]> tetas = new ArrayList<double[]>();

		mat1 = graph.createGrafo(Data, r, 0, nr_rdm);

		mat2=graph.createGrafo(Data, r, 1, nr_rdm);

		
		score_llmax = scmdl.LL(Data, mat1, r);
				
		score_mdlmax = scmdl.MDL(Data, mat2, r);
				
		System.out.println("Mat LL com score:" + score_llmax);
		
		for (i=0;i<r.length;i++){
			for (j=0;j<(r.length)/2;j++){
			System.out.print(mat1[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Mat MDL com score:" + score_mdlmax);
		for (i=0;i<r.length;i++){
			for (j=0;j<(r.length)/2;j++){
			System.out.print(mat2[i][j] + " ");
			}
			System.out.println();
		}
		
		tetas = tt.tetas(Data, mat1, r);
		
		
		int [] fut_values = new int [file.matrix_test.length];
		int var_to_guess = 2; // parametro a receber e a verificar se esta correcto
		
		infer guess = new infer();
		
		for(i=0; i< file.matrix_test.length; i++){
			fut_values[i]= guess.inf (file.matrix_test, mat1, r , var_to_guess + r.length/2, tetas, i);
			
		}
		for (i=0;i<file.matrix_test.length;i++){
			
			System.out.print(fut_values[i] + " ");
			
		}
		System.out.println();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    Runtime t = Runtime.getRuntime();
	    System.out.println("Memory used = "+(t.totalMemory()-t.freeMemory())/1024);
	    System.out.println("Execution Time : "+elapsedTime+" ms (Average of = "+(elapsedTime/nr_rdm)+" ms)");
	}

}
