package Calc;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

public class calcLL  {

	calculate calc = new calculate();

	public double LL(int [][] Data, int [][] mat_adj, int [] r){
		int i, j, n, k;
		double Nij, Nijk, score=0;
		
		
		for(n=0;n<r.length;n++){
			
			List<Integer> index_parents = new ArrayList<Integer>();
			 if (n<(r.length)/2) {
				 index_parents.add(n);
			 }else {
				 index_parents = calc.indexesToCompare(mat_adj, n-(r.length)/2, r.length/2);
				 
			 }
			
			j = calc.getq(index_parents, r);
			for(i=0;i<j+1;i++){
				List<Integer> values_compare =   calc.valuesToCompare ( r, n, i, 0, index_parents ); //no valor de k vai 0, porque ainda nao o sabemos mas ele nao vai ser preciso, melhor solu�ao?
				Nij =  calc.countNij (Data, index_parents,  values_compare);
				//System.out.println("N " + n+ " " +i+ "  :  " + Nij);
				for(k=0;k<r[n];k++){
					values_compare =   calc.valuesToCompare ( r, n, i, k, index_parents );
					Nijk =  calc.countNijk (Data, index_parents, values_compare);
					//System.out.println("N " + n+ " " +i+ " " +k+ "  :  " + Nijk);
					if(Nijk!=0 && Nij!=0){
						score = score + Nijk*(Math.log10(Nijk/Nij));
						//System.out.println("Score: " + score);
					}
				}
			}
		
		}
		return score;
	}
	
}
