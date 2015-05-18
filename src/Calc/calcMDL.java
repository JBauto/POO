package Calc;

import java.util.ArrayList;
import java.util.List;

public class calcMDL extends calcLL {
	
	/** MDL provides the score of a given graph using the MDL model 
	 * 
	 * @param Data which contains the values from which the score is going to calculated
	 * @param mat_adj contains the connections between the variables in t and t+1
	 * @param r contains the number of values each variable can take 
	 * @return double with score using the MDL model
	 */
	public double MDL (int [][] Data, int [][] mat_adj, int [] r){
		
		double score=0;
		
		int B = calculateB(mat_adj, r);
		
		score = LL(Data , mat_adj, r) - 0.5 * (Math.log10(Data.length))/(Math.log10(2)) * B;
		
		return score;
	}

	/** calculateB provides the parameter associated with the complexity of the graph needed to the MDL model 
	 * 
	 * @param mat_adj contains the connections between the variables in t and t+1
	 * @param r contains the number of values each variable can take 
	 * @return absolute Integer with B value
	 */
	public int calculateB (int [][] mat_adj, int [] r){
		int n, q, B=0;
		//List<Integer> index_parents = new ArrayList<Integer>();
		
		for(n=0;n<r.length;n++){
			List<Integer> index_parents = new ArrayList<Integer>();
			if (n<(r.length)/2) {
				 index_parents.add(n);
			 }else {
				 index_parents =  calc.indexesToCompare(mat_adj, n-(r.length)/2, r.length/2);
				 
			 }
			q =  calc.getq (index_parents, r);
			B = B + (r[n]-1)*(q+1);
		}
		
		return Math.abs(B);
	}
	
}
