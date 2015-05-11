package Calc;

import java.util.ArrayList;
import java.util.List;

public class calcTeta  {

	calculate calc = new calculate();
	
	public List<double[]> tetas(int [][] Data, int [][] mat_adj, int [] r){
		int i, j, n, k, t, l;
		double Nij, Nijk, Tijk;
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
				List<Integer> values_compare =  calc.valuesToCompare ( r, n, i, 0, index_parents ); //no valor de k vai 0, porque ainda nao o sabemos mas ele nao vai ser preciso, melhor soluçao?
				Nij = calc.countNij (Data, index_parents,  values_compare);
				for(k=0;k<r[n];k++){
					values_compare =  calc.valuesToCompare ( r, n, i, k, index_parents );
					Nijk = calc.countNijk (Data, index_parents, values_compare);
					
					Tijk = (Nijk+0.5)/(Nij+0.5*r[n]);
					
					System.out.println("Tijk: " + Tijk);
					tetas[t]=Tijk;
					t++;
				}
			}
			nodes.add(tetas);
		
		}
		return nodes;
	}
	
	public int lengthTeta(List<Integer> index_parents, int [] r){
		int l=r[index_parents.get(0)], i;
		
		for(i=1;i<index_parents.size();i++){
			l=l*r[index_parents.get(i)];	
		}
		
		return l;
	}

	
}
