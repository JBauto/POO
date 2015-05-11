package Grafo;

import Calc.*;

import java.io.IOException;

import java.util.Random;

import Calc.calcLL;
import DFS.REQ;
import Files.*;

public class Main_grafo {

	public static void main(String[] args) throws IOException {

		
	    long startTime = System.currentTimeMillis();
		
		grafo graph = new grafo();
		Train teste = new Train();		
		teste.readTrain("train-data.csv");	
		int i,j;
		int Data[][] = teste.matrix_data;
		int nr_rdm = 10;
				
				/*{{ 1, 1, 1, 0 },
						{ 1 ,0, 0, 1 },
						{ 0 ,1, 1, 2 },
						{ 1 ,0, 1, 1 },
						{ 0 ,2, 0, 1 },
						{ 0 ,0, 1, 2 },
						{ 1, 2, 1, 0 }};*/
		
//		Data [0][0]= Data [0][1]= Data [0][2]= Data[0][3]= Data [1][0]= Data [1][2]= Data [4][0]= Data [4][2]= Data [5][2]= Data [6][1]=  0;
//		Data [1][1]= Data [1][2]= Data [2][0]= Data [3][0]= Data [3][1]= Data [3][2]= Data [4][1]= Data [5][0]= Data [6][0]= Data [6][2]= 1;
//		Data [2][1]= Data [5][1]= Data[4][3]= 2 ;

		int[] r = teste.vectorR;
				
				/*new int [4];
		r[0] = r[2] = 2;
		r[1]= r[3] = 3;
		*/
		REQ req = new REQ ();
		calcLL scll = new calcLL();
		calcMDL scmdl = new calcMDL();
		//calcTeta tt = new calcTeta();
		
		int [][] mat2= new int[r.length][(r.length)/2];
		//int [][] mat3= new int[r.length][(r.length)/2];
		int [][] mat_cycle= new int[r.length][(r.length)/2];
		int [][] mat_maxll= new int[r.length][(r.length)/2];
		int [][] mat_maxmdl= new int[r.length][(r.length)/2];
		double score_llmax, score_mdlmax, score_ll, score_mdl;
		
		score_llmax = scll.LL(Data, mat_maxll, r);
		score_mdlmax = scmdl.MDL(Data, mat_maxmdl, r);
		//mat3[0][0] = mat3[0][1] = mat3[0][2] = mat3[1][0] = mat3[2][1]= mat3[2][2] = mat3[3][1]= mat3[3][2] =1;
		//System.out.println("Mat3 LL com score:" + scll.LL(Data, mat3, r));
	
		
		Random rand = new Random();
		
		for(int rd =0; rd< nr_rdm; rd++){
				int [][] mat1= new int[r.length][(r.length)/2];
			while(true){
				
				int k=0;
				
				for (i=0;i < mat1.length/2;i++){
					for(j=0;j<mat1[0].length ;j++){
						mat1[i][j]= rand.nextInt(2);
					}
				}
			
				for( i =0; i<mat1.length;i++){
					for( j=0; j<mat1[0].length;j++){
						mat_cycle[i][j] = mat1[i][j];
					}
				}
				for( i =0; i<mat1.length/2;i++){
					 for (j=0; j<mat1[0].length;j++){
						 if ( req.GetParents(mat_cycle, j) ) k++;
					 }
					 if (  req.FindCycle(mat_cycle,i)) k++;
				}
				
				if (k==0) 	break;
	
			}
	
			//System.out.println(Arrays.deepToString(Data));
			for (i=0;i < mat1.length/2;i++){
						for(j=0;j<mat1[0].length;j++){
							mat2[i][j]= mat1[i][j];
						}
					}
			
			mat1=graph.createGrafo(Data, r, 0, mat1);
			mat2=graph.createGrafo(Data, r, 1, mat2);
			
			score_ll = scll.LL(Data, mat1, r);
			
			if(score_ll > score_llmax){
				score_llmax=score_ll;
				for( i =0; i<mat1.length;i++){
					for( j=0; j<mat1[0].length;j++){
						mat_maxll[i][j] = mat1[i][j];
					}
				}
			}
	/*
			System.out.println("Mat1 LL com score:" + scll.LL(Data, mat1, r));
			
			for (i=0;i<r.length;i++){
				for (j=0;j<(r.length)/2;j++){
				System.out.print(mat1[i][j] + " ");
				}
				System.out.println();
			}
	*/		
			
			score_mdl = scmdl.MDL(Data, mat2, r);
			
			if(score_mdl > score_mdlmax){
				score_mdlmax=score_mdl;
				for( i =0; i<mat2.length;i++){
					for( j=0; j<mat2[0].length;j++){
						mat_maxmdl[i][j] = mat2[i][j];
					}
				}
			}
			/*
			System.out.println("Mat2 MDL com score:" + scmdl.MDL(Data, mat2, r));
			for (i=0;i<r.length;i++){
				for (j=0;j<(r.length)/2;j++){
				System.out.print(mat2[i][j] + " ");
				}
				System.out.println();
			}
			
			*/
		
		}
		
		
		System.out.println("Mat LL com score:" + score_llmax);
		
		for (i=0;i<r.length;i++){
			for (j=0;j<(r.length)/2;j++){
			System.out.print(mat_maxll[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Mat MDL com score:" + score_mdlmax);
		for (i=0;i<r.length;i++){
			for (j=0;j<(r.length)/2;j++){
			System.out.print(mat_maxmdl[i][j] + " ");
			}
			System.out.println();
		}
		
		//tt.tetas(Data, mat1, r);
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Execution Time : "+elapsedTime+" ms");	
	}

}
