package Grafo;
import java.util.Random;

import Calc.*;
import DFS.REQ;

public class grafo {

	public int [][] createGrafo (int [][] Data, int [] r, int type_score, int nr_rdm){ //type_score==0 --> LL, type_score==1 --> MDL
		
		int i, j, rdm, sum_rdm, mat_rdm;
		//int [][] mat_adj_max = new int[r.length][(r.length)/2]; //inicialisa logo tudo a zero
		int [][] mat_adj_test = new int[r.length][(r.length)/2];
		int [][] mat_max = new int[r.length][(r.length)/2];
		int [][] mat_MAX = new int[r.length][(r.length)/2];
		//int [][] mat_adj_vizm = new int[r.length][(r.length)/2];
		int [][] mat_cycle = new int[r.length][(r.length)/2];
		double score_max, score_test, score_MAX;
		operations operator = new operations();
		Random rand = new Random();
		int [][] mat_adj_max= new int[r.length][(r.length)/2];
		REQ req = new REQ ();
		int [][] mat_adj_vizm = new int[r.length][(r.length)/2];
		
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
				for( j=0; j<mat_adj_max[0].length;j++){
					mat_cycle[i][j] = mat_adj_max[i][j];
				}
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
			mat_rdm=0;
			calcLL scll = new calcLL();
			
			score_MAX=score_max=scll.LL(Data, mat_adj_max, r);
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1º caso
				
				double score_viz_max=scll.LL(Data, mat_adj_vizm, r);
				
				
				for(i=0;i<r.length;i++){
					
					for (j=0;j<(r.length)/2;j++){ // todas as adiçoes possiveis
						
						mat_adj_test = operator.add(mat_adj_max, i, j);
						
						score_test = scll.LL(Data, mat_adj_test, r);
						//System.out.println((i+1) + " is now a parent of " + (j+1) + " SCORE: " +score_test + " SCORE VIZ MAX "+ score_viz_max);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							for(int l =0; l<mat_adj_test.length;l++){
								for (int k=0;k<(r.length)/2;k++){
									mat_adj_vizm[l][k] = mat_adj_test[l][k];
									}								
								}
							
							}
							
						}
					}
				
				//System.out.println("SCOREviz MAX apos adiçoes: " + score_viz_max);
				
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todas as subtracçoes possiveis 
													   // podia so fazer para os que tem filhos
						mat_adj_test = operator.remove(mat_adj_max, i, j);
						score_test = scll.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							for(int l =0; l<mat_adj_test.length;l++){
								for (int k=0;k<(r.length)/2;k++){
									mat_adj_vizm[l][k] = mat_adj_test[l][k];
									}
								
							}
							}
					}
				}
				
				//System.out.println("SCOREviz MAX apos remoçoes: " + score_viz_max);
				/*
				for (i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){
					System.out.print(mat_adj_max[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println();
				*/
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todos os flips possiveis, para o exemplo ele nunca vai fazer
						mat_adj_test = operator.flip(mat_adj_max, i, j);
						score_test = scll.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							for(int l =0; l<mat_adj_test.length;l++){
								for (int k=0;k<(r.length)/2;k++){
									mat_adj_vizm[l][k] = mat_adj_test[l][k];
									}
								
							}
							}
					}
				}
				//System.out.println("SCOREviz MAX apos flips: " + score_viz_max);
				
				//System.out.println("SCOREviz MAX " + score_viz_max + " SCORE MAX actual " + score_max);
				
				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					score_max = score_viz_max;
					for(int l =0; l<mat_adj_vizm.length;l++){
						for(int k=0;k<mat_adj_vizm[0].length;k++){
						mat_adj_max[l][k] = mat_adj_vizm[l][k];
						}
					}
					
					
					
				} else {
					if(score_max > score_MAX){ //se o viz for maior que o max actual
						score_MAX = score_max;
						for(int l =0; l<mat_adj_max.length;l++){
							for(int k=0;k<mat_adj_max[0].length;k++){
							mat_MAX[l][k] = mat_adj_max[l][k];
							}
						}
					}
					/*for (i=0;i<r.length;i++){
						for (j=0;j<(r.length)/2;j++){
						System.out.print(mat_adj_max[i][j] + " ");
						}
						System.out.println();
					}*/
					
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
								for( j=0; j<mat_adj_max[0].length;j++){
									mat_cycle[i][j] = mat_adj_max[i][j];
								}
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
						score_max=scll.LL(Data, mat_adj_vizm, r);
						mat_rdm++;
					}else{
						return mat_MAX;
					}
				
					
					
				}
				
			}
			
		}
		
		if(type_score==1){
			//copiado de cima
			mat_rdm=0;
			calcMDL scmdl = new calcMDL();
			
			score_MAX=score_max=scmdl.MDL(Data, mat_adj_max, r);
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1º caso
				
				double score_viz_max=scmdl.MDL(Data, mat_adj_vizm, r);
				
				
				for(i=0;i<r.length;i++){
					
					for (j=0;j<(r.length)/2;j++){ // todas as adiçoes possiveis
						
						mat_adj_test = operator.add(mat_adj_max, i, j);
						
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						//System.out.println((i+1) + " is now a parent of " + (j+1) + " SCORE: " +score_test + " SCORE VIZ MAX "+ score_viz_max);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							for(int l =0; l<mat_adj_test.length;l++){
								for (int k=0;k<(r.length)/2;k++){
									mat_adj_vizm[l][k] = mat_adj_test[l][k];
									}								
								}
							
							}
							
						}
					}
				
				//System.out.println("SCOREviz MAX apos adiçoes: " + score_viz_max);
				
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todas as subtracçoes possiveis 
													   // podia so fazer para os que tem filhos
						mat_adj_test = operator.remove(mat_adj_max, i, j);
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							for(int l =0; l<mat_adj_test.length;l++){
								for (int k=0;k<(r.length)/2;k++){
									mat_adj_vizm[l][k] = mat_adj_test[l][k];
									}
								
							}
							}
					}
				}
				
				//System.out.println("SCOREviz MAX apos remoçoes: " + score_viz_max);
				/*
				for (i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){
					System.out.print(mat_adj_max[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println();
				*/
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todos os flips possiveis, para o exemplo ele nunca vai fazer
						mat_adj_test = operator.flip(mat_adj_max, i, j);
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							for(int l =0; l<mat_adj_test.length;l++){
								for (int k=0;k<(r.length)/2;k++){
									mat_adj_vizm[l][k] = mat_adj_test[l][k];
									}
								
							}
							}
					}
				}
				//System.out.println("SCOREviz MAX apos flips: " + score_viz_max);
				
				//System.out.println("SCOREviz MAX " + score_viz_max + " SCORE MAX actual " + score_max);
				
				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					score_max = score_viz_max;
					for(int l =0; l<mat_adj_vizm.length;l++){
						for(int k=0;k<mat_adj_vizm[0].length;k++){
						mat_adj_max[l][k] = mat_adj_vizm[l][k];
						}
					}
					
					
					
				} else {
					if(score_max > score_MAX){ //se o viz for maior que o max actual
						score_MAX = score_max;
						for(int l =0; l<mat_adj_max.length;l++){
							for(int k=0;k<mat_adj_max[0].length;k++){
							mat_MAX[l][k] = mat_adj_max[l][k];
							}
						}
					}
					/*for (i=0;i<r.length;i++){
						for (j=0;j<(r.length)/2;j++){
						System.out.print(mat_adj_max[i][j] + " ");
						}
						System.out.println();
					}*/
					
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
								for( j=0; j<mat_adj_max[0].length;j++){
									mat_cycle[i][j] = mat_adj_max[i][j];
								}
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
					}else	return mat_MAX;
					
				}
				
					
					
			}
				
		}
		
	
	return mat_MAX;
	}
	
}
