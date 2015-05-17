package GHC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Calc.calcMDL;
import DFS.REQ;
import Grafo.*;
import Tabu.*;

public class ghc_ll{

	protected double score;
	private double score_viz;
	protected int best_i;
	protected int best_j;
	private tabu tabu_list;

	protected int[][] mat_adj_test;
	public int[][] mat_adj_vizm;
	protected operations operator; 
	protected calcMDL scmdl = new calcMDL();
	
	public ghc_ll(int lin,int col, int tries){
		tabu_list = new tabu(tries);
		mat_adj_vizm = new int[lin][col];
		mat_adj_test = new int[lin][col];
		score = 0;
		best_i = 0;
		best_j = 0;
		operator = new operations();
	}
	
	public void setScoreViz(double x){
		setScore_viz(x);
	}
	
	public void ghc_add(int [][] Data, int[][] mat_adj_max, int[] r, int ALG){
		double score_test;
		for(int i=0;i<r.length;i++){
			
			for (int j = 0;j<(r.length)/2;j++){ // todas as adicoes possiveis
				if(!tabu_list.checkTabu(1, i, j) && mat_adj_max[i][j] == 0)
					mat_adj_test = operator.add(mat_adj_max, i, j);
				else
					continue;
				if(ALG == 0)  score_test = scmdl.LL(Data, mat_adj_test, r);
				else score_test = scmdl.MDL(Data, mat_adj_test, r);				
				//System.out.println("ADD "+ score_test);
				if (score_test > this.score_viz) {
					System.out.println("Add");
					this.best_i = i;
					this.best_j = j;
					this.score_viz = score_test;
					for(int l = 0; l<mat_adj_test.length;l++){
						int[] aMatrix = mat_adj_test[l];
						System.arraycopy(aMatrix, 0, this.mat_adj_vizm[l], 0, (r.length)/2);								
					}					
				}					
			}
		}
	}
	
	public void ghc_remove(int [][] Data, int[][] mat_adj_max, int[] r,int ALG){
		double score_test;
		for(int i = 0;i<r.length;i++){
			for (int j = 0;j<(r.length)/2;j++){ // todas as subtrac�oes possiveis 
				if(!tabu_list.checkTabu(1, i, j) && mat_adj_max[i][j] == 1 )							   // podia so fazer para os que tem filhos
					mat_adj_test = operator.remove(mat_adj_max, i, j);
				else
					continue;
				//System.out.println("A tentar remove "+i+","+j);
				if(ALG == 0)  score_test = scmdl.LL(Data, mat_adj_test, r);
				else score_test = scmdl.MDL(Data, mat_adj_test, r);
				//System.out.println("REMOVE "+ score_test);
				if (score_test > this.score_viz) {
					System.out.println("Remove");
					best_i = i;
					best_j = j;
					this.score_viz = score_test;
					for(int l = 0; l<mat_adj_test.length;l++){
						int[] aMatrix = mat_adj_test[l];
						System.arraycopy(aMatrix, 0, this.mat_adj_vizm[l], 0, (r.length)/2);								
					}
				}
			}
		}
	}

	public void ghc_flip(int [][] Data, int[][] mat_adj_max, int[] r, int ALG){
		double score_test;
		for(int i = 0;i<r.length;i++){
			for (int j = 0;j<(r.length)/2;j++){ // todos os flips possiveis, para o exemplo ele nunca vai fazer
				int n = (mat_adj_max.length)/2;
				if(!tabu_list.checkTabu(1, i, j) && mat_adj_max[i][j] == 0 && (i-n)>0)							   // podia so fazer para os que tem filhos
					mat_adj_test = operator.flip(mat_adj_max, i, j);
				else
					continue;
				//System.out.println("A tentar flip "+i+","+j);
				if(ALG == 0)  score_test = scmdl.LL(Data, mat_adj_test, r);
				else score_test = scmdl.MDL(Data, mat_adj_test, r);				
				//System.out.println("FLIP "+ score_test);
				if (score_test>this.score_viz) {
					System.out.println("Flip");
					best_i = i;
					best_j = j;
					this.score_viz = score_test;
					for(int l = 0; l<mat_adj_test.length;l++){
						int[] aMatrix = mat_adj_test[l];
						System.arraycopy(aMatrix, 0, this.mat_adj_vizm[l], 0, (r.length)/2);								
					}
				}
			}
		}
	}
	
	public int [][] createRestart(int[] r){
		Random rand = new Random();
		Random coord = new Random();
		REQ req = new REQ ();
		int [][] mat_cycle = new int[r.length][(r.length)/2];
		int[][] mat_adj_max;
		while(true){
			
			int b=0;
			int counter = rand.nextInt((3*(r.length)/2 - 5) + 1) + 5;
			int parents[] = new int[r.length/2];
				//System.out.println(r.length);	
			
			mat_adj_max = new int[r.length][r.length/2];
			
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
			
			int i;
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
		for( int i = 0; i<mat_adj_max.length;i++){
			System.out.println(Arrays.toString(mat_adj_max[i]));
		}
		return mat_adj_max;
	}
	
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
	
	public double getScore_viz() {
		return score_viz;
	}

	public void setScore_viz(double score_viz) {
		this.score_viz = score_viz;
	}
	
}
