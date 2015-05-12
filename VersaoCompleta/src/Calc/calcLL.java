package Calc;

import java.util.ArrayList;
import java.util.Iterator;
//import java.util.ArrayList;
import java.util.List;

public class calcLL  {
	
	long time = 0;
	calculate calc = new calculate();

	public double LL(int [][] Data, int [][] mat_adj, int [] r){
		int i, j, n, k;
		double Nij = 0, Nijk, score=0;
		long count = 0;
		long startTime;  
		long endTime;
		long startTime2;  
		long endTime2;
		
		for(n=0;n<r.length;n++){
			
			List<Integer> index_parents = new ArrayList<Integer>();
			 if (n<(r.length)/2) {
				 index_parents.add(n);
			 }else {
				 index_parents = calc.indexesToCompare(mat_adj, n-(r.length)/2, r.length/2);
				 
			 }
			startTime = System.nanoTime();
			j = calc.getq(index_parents, r);
			endTime = System.nanoTime();
			time = time + endTime - startTime;
			startTime2 = System.nanoTime();
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
					if(Nijk!=0 && Nij!=0){
						score = score + Nijk*(Math.log10(Nijk/Nij)/Math.log10(2));
					}
				}
			}
			endTime2 = System.nanoTime();
			count = count + endTime2-startTime2;
		}

		return score;
	}
	
}
