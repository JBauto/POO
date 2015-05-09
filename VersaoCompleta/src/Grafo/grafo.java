package Grafo;
import Calc.*;

public class grafo {

	public int [][] createGrafo (int [][] Data, int [] r, int type_score, int [][] mat_adj_max){ //type_score==0 --> LL, type_score==1 --> MDL
		int i, j;
		//int [][] mat_adj_max = new int[r.length][(r.length)/2]; //inicialisa logo tudo a zero
		int [][] mat_adj_test = new int[r.length][(r.length)/2];
		int [][] mat_adj_vizm = new int[r.length][(r.length)/2];
		double score_max, score_test;
		operations operator = new operations();
		
		
		if(type_score==0){
			
			calcLL scll = new calcLL();
			
			score_max=scll.LL(Data, mat_adj_max, r);
			
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
					/*for (i=0;i<r.length;i++){
						for (j=0;j<(r.length)/2;j++){
						System.out.print(mat_adj_max[i][j] + " ");
						}
						System.out.println();
					}*/
					return mat_adj_max;
				}
				
			}
			
		}
		
		if(type_score==1){
			//copiado de cima

			calcMDL scmdl = new calcMDL();
			
			score_max=scmdl.MDL(Data, mat_adj_max, r);;
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1º caso
				

				double score_viz_max=scmdl.MDL(Data, mat_adj_vizm, r);
				
				for(i=0;i<r.length;i++){
					for (j=0;j<(r.length)/2;j++){ // todas as adiçoes possiveis
						
						mat_adj_test = operator.add(mat_adj_max, i, j);
						
						score_test = scmdl.MDL(Data, mat_adj_test, r);
						//System.out.println((i+1) + " is now a parent of " + (j+1) + " SCORE MDL: " +score_test + " SCORE VIZ MAX "+ score_viz_max);
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
				
				//System.out.println("SCOREviz MDL MAX apos adiçoes: " + score_viz_max);
				
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
				
				//System.out.println("SCOREviz MDL MAX apos remoçoes: " + score_viz_max);
				
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
				//System.out.println("SCOREviz MDL MAX apos flips: " + score_viz_max);
				
				//System.out.println("SCOREviz MDL MAX " + score_viz_max + " SCORE MAX actual " + score_max);
				
				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					score_max = score_viz_max;
					for(int l =0; l<mat_adj_vizm.length;l++){
						for(int k=0;k<mat_adj_vizm[0].length;k++){
						mat_adj_max[l][k] = mat_adj_vizm[l][k];
						}
					}
					
					
					
				} else {
					return mat_adj_max;
				}
			}	
		}
		return mat_adj_max;
	}
	
	
}
