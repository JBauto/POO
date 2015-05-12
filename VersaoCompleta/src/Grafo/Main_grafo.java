package Grafo;

import Calc.*;
import Inference.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Calc.calcLL;
import DFS.REQ;
import Files.*;

public class Main_grafo {

	public static void main(String[] args) throws IOException {

		long time4 = 0 ;
	    long startTime = System.currentTimeMillis();
		
		grafo graph = new grafo();
		Train teste = new Train();
		//Test file = new Test();
		teste.readTrain("train-data.csv");	
		//file.readTest("test-data.csv");
		int i,j;
		int Data[][] = teste.matrix_data;
		int nr_rdm = 100;
				

		int[] r = teste.vectorR;
		REQ req = new REQ ();
		//calcLL scll = new calcLL();
		calcMDL scmdl = new calcMDL();
		calcTeta tt = new calcTeta();
		
		int [][] mat2= new int[r.length][(r.length)/2];
		int [][] mat1= new int[r.length][(r.length)/2];
		int [][] mat_cycle= new int[r.length][(r.length)/2];
		int [][] mat_maxll= new int[r.length][(r.length)/2];
		int [][] mat_maxmdl= new int[r.length][(r.length)/2];
		double score_llmax, score_mdlmax, score_ll, score_mdl;
		List<double[]> tetas = new ArrayList<double[]>();
		
		score_llmax = scmdl.LL(Data, mat_maxll, r);
		score_mdlmax = scmdl.MDL(Data, mat_maxmdl, r);

	
		long start1 = System.currentTimeMillis();
		mat1 = graph.createGrafo(Data, r, 0, nr_rdm);
		long finish1 = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		mat2=graph.createGrafo(Data, r, 1, nr_rdm);
		long finish2 = System.currentTimeMillis();
		long time1 = finish1 - start1;
		long time2 = finish2 - start2;
		
		//System.out.println("Time LL = " + time1 + " ms");
		//System.out.println("Time MDL = " + time2 + " ms");
		
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
		
		tetas = tt.tetas(Data, mat_maxll, r);
		
		// LE TESTE E CRIA MATRIZ DE INTEIROS COM O MESMO CHAMADA "test"
		
		
		int [] fut_values = new int [test.length];
		int var_to_guess = 0; // parametro a receber
		
		infer guess = new infer();
		
		for(i=0; i< Data.length; i++){
			fut_values[i]= guess.inf (test, mat_maxll, r , var_to_guess + r.length/2, tetas, i);
			
		}
		
		for (i=0;i<Data.length;i++){
			
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
