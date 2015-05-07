package Grafo;
import Calc.*;

public class grafo {

	public int [][] createGrafo (int [][] Data, int [] r, int type_score){ //type_score==0 --> LL, type_score==1 --> MDL
		int i, j;
		int [][] mat_adj_max = new int[2*r.length][r.length]; //inicialisa logo tudo a zero
		int [][] mat_adj_test = mat_adj_max;
		int [][] mat_adj_vizm = mat_adj_max;
		double score_max, score_test;
		operations operator = new operations();
		
		
		if(type_score==0){
			
			calcLL scll = new calcLL();
			
			score_max=0;
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1º caso
				
				double score_viz_max=scll.LL(Data, mat_adj_vizm, r);
				
				for(i=0;i<2*r.length;i++){
					for (j=0;j<r.length;j++){ // todas as adiçoes possiveis
						mat_adj_test = operator.add(mat_adj_max, i, j);
						score_test = scll.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							mat_adj_vizm = mat_adj_test;
							}
						}
					}
				
				
				for(i=0;i<2*r.length;i++){
					for (j=0;j<r.length;j++){ // todas as subtracçoes possiveis 
													   // podia so fazer para os que tem filhos
						mat_adj_test = operator.remove(mat_adj_max, i, j);
						score_test = scll.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							mat_adj_vizm = mat_adj_test;
							}
					}
				}
				
				for(i=0;i<2*r.length;i++){
					for (j=0;j<r.length;j++){ // todas as adiçoes possiveis
						mat_adj_test = operator.flip(mat_adj_max, i, j);
						score_test = scll.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							mat_adj_vizm = mat_adj_test;
							}
					}
				}
				
				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					score_max = score_viz_max;
					mat_adj_max=mat_adj_vizm;
				} else break;
				
			}
			
		}
		
		if(type_score==1){
			//copiado de cima

			calcMDL scmdl = new calcMDL();
			
			score_max=0;
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1º caso
				
				double score_viz_max=scmdl.LL(Data, mat_adj_vizm, r);
				
				for(i=0;i<2*r.length;i++){
					for (j=0;j<r.length;j++){ // todas as adiçoes possiveis
						mat_adj_test = operator.add(mat_adj_max, i, j);
						score_test = scmdl.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							mat_adj_vizm = mat_adj_test;
							}
						}
					}
				
				
				for(i=0;i<2*r.length;i++){
					for (j=0;j<r.length;j++){ // todas as subtracçoes possiveis 
													   // podia so fazer para os que tem filhos
 						mat_adj_test = operator.remove(mat_adj_max, i, j);
						score_test = scmdl.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							mat_adj_vizm = mat_adj_test;
							}
					}
				}
				
				for(i=0;i<2*r.length;i++){
					for (j=0;j<r.length;j++){ // todas as adiçoes possiveis
						mat_adj_test = operator.flip(mat_adj_max, i, j);
						score_test = scmdl.LL(Data, mat_adj_test, r);
						if (score_test>score_viz_max) {
							score_viz_max = score_test;
							mat_adj_vizm = mat_adj_test;
							}
					}
				}
				
				if(score_viz_max > score_max){ //se o viz for maior que o max actual
					score_max = score_viz_max;
					mat_adj_max=mat_adj_vizm;
				} else break;
				
			}
			
		}
		
		return mat_adj_max;
	}
	
}
