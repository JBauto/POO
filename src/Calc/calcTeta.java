package Calc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class calcTeta  {

	calculate calc = new calculate();
	
	/** tetas provides the list containing arrays of doubles with values of "teta" to all possible combinations for a specific graph 
	 * and received Data.
	 * Each "teta" represents the probability of a variable have a certain value given the specific configuration of their parents.
	 * 
	 * @param Data which contains the values from which the "teta" is going to be calculated
	 * @param mat_adj contains the connections between the variables in t and t+1
	 * @param r contains the number of values each variable can take 
	 * @return list of arrays of doubles each array having all the possible "teta"'s of a certain variable 
	 */
	public List<double[]> tetas(int [][] Data, int [][] mat_adj, int [] r){
		int i, j, n, k, t, l;
		double Nij, Nijk;
		List<double[]> nodes = new ArrayList<double[]>();

		
		for(n=0;n<r.length;n++){
			List<Integer> index_parents = new ArrayList<Integer>();
			if (n<(r.length)/2) {
				 index_parents.add(n);
			 }else {
				 index_parents = calc.indexesToCompare(mat_adj, n-(r.length)/2, r.length/2);
				 
			 }
			
			
			j = calc.getq(index_parents, r);
			l = lengthTeta(index_parents, r);
			double[] tetas = new double[l];
			t=0;
			for(i=0;i<j+1;i++){
				List <Double> Nijk_val = new ArrayList<Double>();
				for(k=0;k<r[n];k++){
					List<Integer> values_compare =   calc.valuesToCompare ( r, i, k, index_parents );
					Nijk_val.add(calc.countNijk (Data, index_parents, values_compare));
				}
				Nij = 0;
				for(Iterator<Double> it = Nijk_val.iterator();it.hasNext();){
					Nij = Nij + it.next();
				}
				for(Iterator<Double> it = Nijk_val.iterator(); it.hasNext(); ){
					Nijk = it.next();
					tetas[t]=(Nijk+0.5)/(Nij+0.5*r[n]);
					t++;
				}
			}
			nodes.add(tetas);
		
		}
		return nodes;
	}
	
	/** lengthTeta calculates the length of the array of "teta" to a specific variable in order to create that array
	 * 
	 * @param index_parents is a list that contains the indexes of a specific node and his parents
	 * @param r contains the number of values each variable can take 
	 * @return integer with the length to be used to create the array
	 */
	public int lengthTeta(List<Integer> index_parents, int [] r){
		int l=r[index_parents.get(0)], i;
		
		for(i=1;i<index_parents.size();i++){
			l=l*r[index_parents.get(i)];	
		}
		
		return l;
	}

	
}
