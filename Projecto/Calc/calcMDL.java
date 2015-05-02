package Calc;

import java.util.List;

public class calcMDL extends calcLL {
	
	public double MDL (int [][] Data, int [][] mat_adj, int [] r){
		
		double score=0;
		
		int B = calculateB(mat_adj, r);
		
		System.out.println("B = "+B);
		
		score = LL(Data , mat_adj, r) - 0.5 * Math.log10(r.length) * B;
		
		return score;
	}

	
	public int calculateB (int [][] mat_adj, int [] r){
		int n, q, B=0;
		
		for(n=0;n<r.length;n++){
			List<Integer> index_parents = indexesToCompare(mat_adj, n);	
			q = getq (index_parents, r);
			B = B + (r[n]-1)*(q+1);
		}
		
		return Math.abs(B);
	}
	
}
