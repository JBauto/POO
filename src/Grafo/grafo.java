package Grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Tabu.tabu;
import Calc.calcMDL;
import DFS.REQ;
import GHC.*;

public class grafo {

	int [][] mat_adj_test;
	int [][] mat_adj_vizm;		
	int [][] mat_adj_max;
	int [][] mat_equals;
	double score_max, score_test,score_MAX;
	
	
	
	public int [][] createGrafo (int [][] Data, int [] r, int type_score, int nr_rdm, int tries){ //type_score==0 --> LL, type_score==1 --> MDL
		int i, j, mat_rdm=0;
		//tabu tabu_list_LL = new tabu((tries/3)*3+3);
		//tabu tabu_list_MDL = new tabu((tries/3)*3+3);
		tabu tabu_list_LL = new tabu(0);
		tabu tabu_list_MDL = new tabu(0);
		operations operator = new operations();
		
		int [][] mat_MAX = new int[r.length][(r.length)/2];
		mat_adj_max= new int[r.length][(r.length)/2];
		mat_equals = new int[r.length][(r.length)/2];

		Random rand = new Random();
		Random coord = new Random();
		int best_i = -1, best_j = -1;
		calcMDL scmdl = new calcMDL();
		
		
		ghc_ll GHC = new ghc_ll(r.length,r.length/2, tries);

		
		int parents_teste [] = new int[r.length/2];

		for(int m = 0;m<parents_teste.length;m++)
			parents_teste[m] = 3;
		
		mat_adj_max = GHC.createRestart(r);
				
//		System.out.println("Starting...");
		if(type_score==0){

			score_MAX=score_max=scmdl.LL(Data, mat_adj_max, r);
			
			for( i =0; i<mat_adj_max.length;i++){
				int[] aMatrix = mat_adj_max[i];
				System.arraycopy(aMatrix, 0, mat_MAX[i], 0, mat_adj_max[0].length);		
			}
			
			GHC.setScoreViz(scmdl.LL(Data, GHC.mat_adj_vizm, r));

			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1� caso
				
				System.out.println(score_max);

				GHC.ghc_add(Data, mat_adj_max, r,type_score);	
				GHC.ghc_remove(Data, mat_adj_max, r,type_score);				
				GHC.ghc_flip(Data, mat_adj_max, r, type_score);				

				

				if(GHC.getScore_viz() > score_max){ //se o viz for maior que o max actual
					score_max = GHC.getScore_viz();
					tabu_list_LL.addTabu(1, best_i, best_j);
					for(int l =0; l<GHC.mat_adj_vizm.length;l++){
						int[] aMatrix = GHC.mat_adj_vizm[l];
						System.arraycopy(aMatrix, 0, mat_adj_max[l], 0, GHC.mat_adj_vizm[0].length);								
					}
					
				} else {
					if(score_max > score_MAX){ //se o max desta matriz for maior que o max das matrizes vistas ate agora
						score_MAX = score_max;
						for(int l = 0; l<mat_adj_max.length;l++){
							int[] aMatrix = mat_adj_max[l];
							System.arraycopy(aMatrix, 0, mat_MAX[l], 0, mat_adj_max[0].length);								
						}
					}
					if(mat_rdm<nr_rdm){
						System.out.println(mat_rdm);
						mat_adj_max = new int[r.length][(r.length)/2];
						GHC = new ghc_ll(r.length, r.length/2, tries);
						mat_adj_max = GHC.createRestart(r);				
						GHC.mat_adj_vizm = new int[r.length][(r.length)/2];
						GHC.setScoreViz(scmdl.LL(Data, GHC.mat_adj_vizm, r));
						mat_rdm++;
					}else{
						return mat_MAX;
					}
				}
				
			}
			
		}
		
		if(type_score==1){

			score_MAX=score_max=scmdl.MDL(Data, mat_adj_max, r);
			for( i =0; i<mat_adj_max.length;i++){
				int[] aMatrix = mat_adj_max[i];
				System.arraycopy(aMatrix, 0, mat_MAX[i], 0, mat_adj_max[0].length);		
			}
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1� caso
				

				GHC.setScoreViz(scmdl.LL(Data, GHC.mat_adj_vizm, r));
				//System.out.println(score_max);

				GHC.ghc_add(Data, mat_adj_max, r,type_score);				
				GHC.ghc_remove(Data, mat_adj_max, r,type_score);				
				GHC.ghc_flip(Data, mat_adj_max,r, type_score);		

				if(GHC.getScore_viz() > score_max){ //se o viz for maior que o max actual
					tabu_list_MDL.addTabu(1, best_i, best_j);
					score_max = GHC.getScore_viz();
					for(int l =0; l<mat_adj_vizm.length;l++){
						int[] aMatrix = mat_adj_vizm[l];
						System.arraycopy(aMatrix, 0, mat_adj_max[l], 0, mat_adj_vizm[0].length);								
					}
				}else{
					if(score_max > score_MAX){ //se o viz for maior que o max actual
						score_MAX = score_max;
						for(int l =0; l<mat_adj_max.length;l++){
							int[] aMatrix = mat_adj_max[l];
							System.arraycopy(aMatrix, 0, mat_MAX[l], 0, mat_adj_max[0].length);								
						}
					}
					if(mat_rdm<nr_rdm){
						mat_adj_max = new int[r.length][(r.length)/2];
						mat_adj_max = GHC.createRestart(r);				
						//mat_adj_vizm = new int[r.length][(r.length)/2];
						score_max=scmdl.MDL(Data, mat_adj_max, r);
						mat_rdm++;
						tabu_list_MDL = new tabu((tries/3)*3+3);
					}else
						return mat_MAX;
				}
			}	
		}
		return mat_adj_max;
	}	
}
