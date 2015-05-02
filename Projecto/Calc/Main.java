package Calc;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		calcMDL scmdl = new calcMDL();
		calcLL scll = new calcLL();
		calcTeta sct = new calcTeta();
		
		List<double[]> nodes = new ArrayList<double[]>();
		
		int [][] mat_adj = new int [3][3];
		int i, j;
		double score, score2;
		for (i=0;i<3;i++){
			for(j=0;j<3;j++){
				mat_adj[i][j]= 0;
			}
		}
		mat_adj[2][0]=1;
		mat_adj[1][0]=1;
		
		System.out.println("Matriz de adjacencias:");
				
		for (i=0;i<3;i++){
			System.out.println(mat_adj[i][0] + " " + mat_adj[i][1] + " " + mat_adj[i][2]);
		}
		
		int[][] Data = new int [7][3];
		
		Data [0][0]= Data [0][1]= Data [0][2]= Data [1][0]= Data [1][2]= Data [4][0]= Data [4][2]= Data [5][2]= Data [6][1]= 0;
		Data [1][1]= Data [1][2]= Data [2][0]= Data [3][0]= Data [3][1]= Data [3][2]= Data [4][1]= Data [5][0]= Data [6][0]= Data [6][2]= 1;
		Data [2][1]= Data [5][1]= 2;
		
		System.out.println("Data inserida:");
		
		for (i=0;i<7;i++){
			System.out.println(Data[i][0] + " " + Data[i][1] + " " + Data[i][2]);
		}
		
		int[] r = new int [3];
		r[0] = r[2] = 2;
		r[1]= 3;
		
		System.out.println("Data.length: " + Data.length + "  mat_adj.length: " + mat_adj.length);
		
		score = scll.LL(Data, mat_adj, r);
		System.out.println("Score LL: " + score);
		
		score2 = scmdl.MDL(Data, mat_adj, r);
		System.out.println("Score MDL: " + score2);
		
		nodes = sct.tetas(Data, mat_adj, r);
		System.out.println(nodes);
	}

}
