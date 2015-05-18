package Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import Tabu.tabu;
import Calc.calcMDL;
import DFS.REQ;

public class grafo {

	int [][] mat_adj_test;
	int [][] mat_adj_vizm;		
	int [][] mat_adj_max;
	int [][] mat_equals;
	double score_max, score_test,score_MAX;
	
	/** check_coord verifies if the current index of the matrix are already in the list.
	 * If it is already there it wont be added to the list otherwise its added.
	 *
	 * @param list contains indexes of connections added to the matrix
	 * @param coord_i index i to test with the list
	 * @param coord_j index j to test with the list
	 * @return true if the list does not contain that pair of indexes otherwise false
	 */
	
	private boolean check_coord(List<int[]> list, int coord_i, int coord_j){
		
		for(Iterator<int[]> it = list.iterator(); it.hasNext();){
			int[] tmp = it.next();
			if(tmp[0] == coord_i && tmp[1] == coord_j)
				return false;
		}
		
		int coord[] = new int[2];
		coord[0] = coord_i;
		coord[1] = coord_j;
		list.add(coord);
		
		return true;
	}
	
	/** createGrafo Create a random and acyclic matrix to start the GHC algorithm. Once it's created it performs all possible adds
	 * removes and flips that aren't present in the tabu list. 
	 * After all operations are done checks the score of the resulting matrix with the current maximum score, if the score is better
	 * the resulting matrix becomes our maximum matrix. Then it starts all over again. 
	 * Once there isn't a higher score it stops the cycle and begins again with a different and random initial matrix, repeats this 
	 * process according to the number o random restarts.
	 * 
	 * @param Data matrix with the information divided by pair of t and t+1
	 * @param r contains the number of values each variable can take 
	 * @param type_score defines which score algorithm it is meant to be used. 0 means LL and 1 MDL
	 * @param nr_rdm number of random restarts that will be done
	 * @param tries number of operations on index i,j that was the best connection that will be ignored. Ex: if 0,0 add was what caused 
	 * the best score the next tries operation on that index pair wont be performed.
	 * @return int[][] matrix representing the graph with best score found, accordingly with the type of score selected
	 */
	public int [][] createGrafo (int [][] Data, int [] r, int type_score, int nr_rdm, int tries){ //type_score==0 --> LL, type_score==1 --> MDL
		int i, j,  mat_rdm=0;//rdm, sum_rdm,
		tabu tabu_list_LL = new tabu((tries/3)*3+3);
		tabu tabu_list_MDL = new tabu((tries/3)*3+3);
		operations operator = new operations();
		mat_adj_vizm = new int[r.length][(r.length)/2];
		mat_adj_test = new int[r.length][(r.length)/2];
		int [][] mat_MAX = new int[r.length][(r.length)/2];
		mat_adj_max= new int[r.length][(r.length)/2];
		int [][] mat_cycle = new int[r.length][(r.length)/2];
		mat_equals = new int[r.length][(r.length)/2];
	//	boolean test_equals = false;
		REQ req = new REQ ();
		Random rand = new Random();
		Random coord = new Random();
		int best_i = -1, best_j = -1;
		calcMDL scmdl = new calcMDL();
		
		int parents_teste [] = new int[r.length/2];

		for(int m = 0;m<parents_teste.length;m++)
			parents_teste[m] = 3;
		
		while(true){
			
			int b=0;
			int counter = rand.nextInt(3*(r.length/2));
			int parents[] = new int[r.length/2];
				//System.out.println(r.length);	
			mat_adj_max = new int[r.length][(r.length)/2];
			
			List<int[]> used_i = new ArrayList<int[]>();
			
			while(counter!=0){
				int teste_i = coord.nextInt(r.length);
				int teste_j = coord.nextInt(r.length/2);
				
				if(parents[teste_j] == 3) continue;
				else{
					if(this.check_coord(used_i, teste_i, teste_j)){
						mat_adj_max[teste_i][teste_j] = 1;
						parents[teste_j]++;
						counter--;
					}
				}
			}			
			
			for( i = 0; i<mat_adj_max.length;i++){
				int[] aMatrix = mat_adj_max[i];
				System.arraycopy(aMatrix, 0, mat_cycle[i], 0, mat_adj_max[0].length);		
			}
			 if ( req.GetParents(mat_cycle, 0) ) b=0;

			 for( i = 0; i<mat_adj_max.length;i++){

				 if (req.FindCycle(mat_cycle,i)) b++;

			}
			if (b==0) 	break;
			
		}
				
		if(type_score==0){

			score_MAX=score_max=scmdl.LL(Data, mat_adj_max, r);
			
			for( i =0; i<mat_adj_max.length;i++){
				int[] aMatrix = mat_adj_max[i];
				System.arraycopy(aMatrix, 0, mat_MAX[i], 0, mat_adj_max[0].length);		
			}
			
			while(true){ // enquanto existir um vizinho com score mais alto que mat_adj_max ou outro criterio de paragem
						 // pode ser melhor um do while por causa do 1� caso
				
				double score_viz_max=scmdl.LL(Data, mat_adj_vizm, r);
				System.out.println(score_max);

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
						if(!tabu_list_LL.checkTabu(1, i, j) && mat_adj_max[i][j] == 1 )	
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
					for (j=0;j<(r.length)/2;j++){ 
						int n = (mat_adj_max.length)/2;
						if(!tabu_list_LL.checkTabu(1, i, j) && mat_adj_max[i][j] == 0 && (i-n)>0)
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
						System.out.println(mat_rdm);
						while(true){
							
							int b=0;
							int counter = rand.nextInt(3*(r.length/2));
							int parents[] = new int[r.length/2];	
							mat_adj_max = new int[r.length][(r.length)/2];
							
							List<int[]> used_i = new ArrayList<int[]>();
							
							while(counter!=0){
								int teste_i = coord.nextInt(r.length);
								int teste_j = coord.nextInt(r.length/2);
								
								if(parents[teste_j] == 3) continue;
								else{
									if(this.check_coord(used_i, teste_i, teste_j)){
										mat_adj_max[teste_i][teste_j] = 1;
										parents[teste_j]++;
										counter--;
									}
								}
							}			
							
							for( i = 0; i<mat_adj_max.length;i++){
								int[] aMatrix = mat_adj_max[i];
								System.arraycopy(aMatrix, 0, mat_cycle[i], 0, mat_adj_max[0].length);		
							}
							 if ( req.GetParents(mat_cycle, 0) ) b=0;

							 for( i = 0; i<mat_adj_max.length;i++){

								 if (req.FindCycle(mat_cycle,i)) b++;

							}
							if (b==0) 	break;
							
						}
						
						mat_adj_vizm = new int[r.length][(r.length)/2];
						score_max=scmdl.LL(Data, mat_adj_vizm, r);
						mat_rdm++;
						tabu_list_LL = new tabu((tries/3)*3+3);
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
							
							int b=0;
							int counter = rand.nextInt(3*(r.length/2));
							int parents[] = new int[r.length/2];
								//System.out.println(r.length);	
							mat_adj_max = new int[r.length][(r.length)/2];
							
							List<int[]> used_i = new ArrayList<int[]>();
							
							while(counter!=0){
								int teste_i = coord.nextInt(r.length);
								int teste_j = coord.nextInt(r.length/2);
								
								if(parents[teste_j] == 3) continue;
								else{
									if(this.check_coord(used_i, teste_i, teste_j)){
										mat_adj_max[teste_i][teste_j] = 1;
										parents[teste_j]++;
										counter--;
									}
								}
							}			
							
							for( i = 0; i<mat_adj_max.length;i++){
								int[] aMatrix = mat_adj_max[i];
								System.arraycopy(aMatrix, 0, mat_cycle[i], 0, mat_adj_max[0].length);		
							}
							 if ( req.GetParents(mat_cycle, 0) ) b=0;

							 for( i = 0; i<mat_adj_max.length;i++){

								 if (req.FindCycle(mat_cycle,i)) b++;

							}
							if (b==0) 	break;
							
						}
						mat_adj_vizm = new int[r.length][(r.length)/2];
						score_max=scmdl.MDL(Data, mat_adj_vizm, r);
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
