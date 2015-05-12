package Calc;

import java.util.ArrayList;
import java.util.List;

public class calcMDL extends calcLL {
	
	public double MDL (int [][] Data, int [][] mat_adj, int [] r){
		
		double score=0;
		
		int B = calculateB(mat_adj, r);
		
		score = LL(Data , mat_adj, r) - 0.5 * (Math.log10(r.length)/Math.log10(2)) * B;
		
		return score;
	}

	
	public int calculateB (int [][] mat_adj, int [] r){
		int n, q, B=0;
		List<Integer> index_parents = new ArrayList<Integer>();
		
		for(n=0;n<r.length;n++){
			if (n<(r.length)/2) {
				 index_parents.add(n);
			 }else {
				 index_parents =  calc.indexesToCompare(mat_adj, n-(r.length)/2, r.length/2);
				 
			 }
			q =  calc.getq (index_parents, r);
			B = B + (r[n]-1)*(q+1);
		}
		
		//System.out.println("B é: " + B);
		return Math.abs(B);
	}
	
}
