package Grafo;

import java.util.Random;

import Tabu.tabu;
import Calc.calcMDL;
import DFS.REQ;

public class grafo {

	int [][] mat_adj_test;
	int [][] mat_adj_vizm;
	double score_max, score_test,score_MAX;
	private int tries = 21;
	
	public int [][] createGrafo (int [][] Data, int [] r, int type_score, int nr_rdm){ //type_score==0 --> LL, type_score==1 --> MDL
		int i, j, rdm, sum_rdm, mat_rdm=0;
		tabu tabu_list_LL = new tabu(tries);
		tabu tabu_list_MDL = new tabu(tries);
		operations operator = new operations();
		mat_adj_vizm = new int[r.length][(r.length)/2];
		mat_adj_test = new int[r.length][(r.length)/2];
		int [][] mat_MAX = new int[r.length][(r.length)/2];
		int [][] mat_adj_max= new int[r.length][(r.length)/2];
		int [][] mat_cycle = new int[r.length][(r.length)/2];
		REQ req = new REQ ();
		Random rand = new Random();
		int best_i = -1, best_j = -1;
		calcMDL scmdl = new calcMDL();
		
		while(true){
			
			int k=0;
			
			mat_adj_max = new int[r.length][(r.length)/2];
			
			for (j=0;j<mat_adj_max[0].length ;j++){
				sum_rdm=0;
				for(i=0;i<mat_adj_max.length ;i++){
					rdm = rand.nextInt(2);
					mat_adj_max[i][j]= rdm;
					sum_rdm = sum_rdm + rdm;
					if(sum_rdm==3)break;
				}
			}
		
			for( i =0; i<mat_adj_max.length;i++){
				int[] aMatrix = mat_adj_max[i];
				System.arraycopy(aMatrix, 0, mat_cycle[i], 0, mat_adj_max[0].length);		
			}
			for( i =0; i<mat_adj_max.length;i++){
				 for (j=0; j<mat_adj_max[0].length;j++){
					 if ( req.GetParents(mat_cycle, j) ) k++;
				 }
				 if (  req.FindCycle(mat_cycle,i)) k++;

			}
			
			if (k==0) 	break;
			
		}

		
		if(type_score==0){

			score_MAX=score_max=scmdl.LL(Data, mat_adj_max, r);
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1� caso
				
				double score_viz_max=scmdl.LL(Data, mat_adj_vizm, r);
			
				@SuppressWarnings("unused")
				int score_op = 0;

				for(i=0;i<r.length;i++){
					
					for (j=0;j<(r.length)/2;j++){ // todas as adi�oes possiveis
						if(!tabu_list_LL.checkTabu(1, i, j) && mat_adj_max[i][j] == 0)
							mat_adj_test = operator.add(mat_adj_max, i, j);
						else
							continue;
						score_test = scmdl.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_op = 1;
							best_i = i;
							best_j = j;
							score_viz_max = score_test;
							for(int l = 0; l<mat_adj_test.length;l++){
								int[] aMatrix = mat_adj_test[l];
								System.arraycopy(aMatrix, 0, mat_adj_vizm[l], 0, (r.length)/2);								
							}
							
						}
							
					}
				}
				
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todas as subtrac�oes possiveis 
						if(!tabu_list_LL.checkTabu(1, i, j) && mat_adj_max[i][j] == 1 )							   // podia so fazer para os que tem filhos
							mat_adj_test = operator.remove(mat_adj_max, i, j);
						else
							continue;
						score_test = scmdl.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_op = 1;
							best_i = i;
							best_j = j;
							score_op = 2;
							score_viz_max = score_test;
							for(int l = 0; l<mat_adj_test.length;l++){
								int[] aMatrix = mat_adj_test[l];
								System.arraycopy(aMatrix, 0, mat_adj_vizm[l], 0, (r.length)/2);								
							}
						}
					}
				}

				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todos os flips possiveis, para o exemplo ele nunca vai fazer
						int n = (mat_adj_max.length)/2;
						if(!tabu_list_LL.checkTabu(1, i, j) && mat_adj_max[i][j] == 0 && (i-n)>0)							   // podia so fazer para os que tem filhos
							mat_adj_test = operator.flip(mat_adj_max, i, j);
						else
							continue;
						score_test = scmdl.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_op = 1;
							best_i = i;
							best_j = j;
							score_op = 3;
							score_viz_max = score_test;
							for(int l = 0; l<mat_adj_test.length;l++){
								int[] aMatrix = mat_adj_test[l];
								System.arraycopy(aMatrix, 0, mat_adj_vizm[l], 0, (r.length)/2);								
							}
						}
					}
				}

				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					score_max = score_viz_max;
					tabu_list_LL.addTabu(1, best_i, best_j);
					for(int l =0; l<mat_adj_vizm.length;l++){
						int[] aMatrix = mat_adj_vizm[l];
						System.arraycopy(aMatrix, 0, mat_adj_max[l], 0, mat_adj_vizm[0].length);								
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
						
						while(true){
							
							int k=0;
							mat_adj_max= new int[r.length][(r.length)/2];
							for (j=0;j<mat_adj_max[0].length ;j++){
								sum_rdm=0;
								for(i=0;i<mat_adj_max.length ;i++){
									rdm = rand.nextInt(2);
									mat_adj_max[i][j]= rdm;
									sum_rdm = sum_rdm + rdm;
									if(sum_rdm==3)break;
								}
							}
						
							for( i =0; i<mat_adj_max.length;i++){
								int[] aMatrix = mat_adj_max[i];
								System.arraycopy(aMatrix, 0, mat_cycle[i], 0, mat_adj_max[0].length);
							}
							for( i =0; i<mat_adj_max.length;i++){
								 for (j=0; j<mat_adj_max[0].length;j++){
									 if ( req.GetParents(mat_cycle, j) ) k++;
								 }
								 if (  req.FindCycle(mat_cycle,i)) k++;

							}
							
							if (k==0) 	break;
							
						}
						mat_adj_vizm = new int[r.length][(r.length)/2];
						score_max=scmdl.LL(Data, mat_adj_vizm, r);
						mat_rdm++;
					}else{
						return mat_MAX;
					}
				}
				
			}
			
		}
		
		if(type_score==1){

			score_MAX=score_max=scmdl.MDL(Data, mat_adj_max, r);
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1� caso
				

				double score_viz_max=scmdl.MDL(Data, mat_adj_vizm, r);
				
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todas as adi�oes possiveis
						if(!tabu_list_MDL.checkTabu(1, i, j) && mat_adj_max[i][j] == 0)
							mat_adj_test = operator.add(mat_adj_max, i, j);
						else
							continue;
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							best_i = i;
							best_j = j;
							score_viz_max = score_test;
							for(int l = 0; l<mat_adj_test.length;l++){
								int[] aMatrix = mat_adj_test[l];
								System.arraycopy(aMatrix, 0, mat_adj_vizm[l], 0, (r.length)/2);								
							}
							
							}
						}
					}
				

				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todas as subtrac�oes possiveis 
													   // podia so fazer para os que tem filhos
						if(!tabu_list_MDL.checkTabu(1, i, j) && mat_adj_max[i][j] == 1)
							mat_adj_test = operator.remove(mat_adj_max, i, j);
						else
							continue;
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							best_i = i;
							best_j = j;
							score_viz_max = score_test;
							for(int l = 0; l<mat_adj_test.length;l++){
								int[] aMatrix = mat_adj_test[l];
								System.arraycopy(aMatrix, 0, mat_adj_vizm[l], 0, (r.length)/2);								
							}
						}
					}
				}
				
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todos os flips possiveis, para o exemplo ele nunca vai fazer
						int n = (mat_adj_max.length)/2;
						if(!tabu_list_MDL.checkTabu(1, i, j) && mat_adj_max[i][j] == 0 && (i-n)>0)
							mat_adj_test = operator.flip(mat_adj_max, i, j);
						else
							continue;
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							best_i = i;
							best_j = j;
							score_viz_max = score_test;
							for(int l = 0; l<mat_adj_test.length;l++){
								int[] aMatrix = mat_adj_test[l];
								System.arraycopy(aMatrix, 0, mat_adj_vizm[l], 0, (r.length)/2);								
							}
						}
					}
				}

				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					tabu_list_MDL.addTabu(1, best_i, best_j);
					score_max = score_viz_max;
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
						while(true){							
							int k=0;
							mat_adj_max= new int[r.length][(r.length)/2];
							for (j=0;j<mat_adj_max[0].length ;j++){
								sum_rdm=0;
								for(i=0;i<mat_adj_max.length ;i++){
									rdm = rand.nextInt(2);
									mat_adj_max[i][j]= rdm;
									sum_rdm = sum_rdm + rdm;
									if(sum_rdm==3)break;
								}
							}
						
							for( i =0; i<mat_adj_max.length;i++){
								int[] aMatrix = mat_adj_max[i];
								System.arraycopy(aMatrix, 0, mat_cycle[i], 0, mat_adj_max[0].length);
							}
							for( i =0; i<mat_adj_max.length;i++){
								 for (j=0; j<mat_adj_max[0].length;j++){
									 if ( req.GetParents(mat_cycle, j) ) k++;
								 }
								 if (  req.FindCycle(mat_cycle,i)) k++;

							}
							
							if (k==0) 	break;
							
						}
						mat_adj_vizm = new int[r.length][(r.length)/2];
						score_max=scmdl.MDL(Data, mat_adj_vizm, r);
						mat_rdm++;
					}else
						return mat_MAX;
				}
			}	
		}
		return mat_adj_max;
	}
	
	
}
