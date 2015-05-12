package Calc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class calcTeta  {

	calculate calc = new calculate();
	
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
				List<Integer> values_compare =   calc.valuesToCompare ( r, n, i, 0, index_parents ); //no valor de k vai 0, porque ainda nao o sabemos mas ele nao vai ser preciso, melhor solu�ao?
				//Nij =  calc.countNij (Data, index_parents,  values_compare);
				//System.out.println("N " + n+ " " +i+ "  :  " + Nij);
				List <Double> Nijk_val = new ArrayList<Double>();
				for(k=0;k<r[n];k++){
					values_compare =   calc.valuesToCompare ( r, n, i, k, index_parents );
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
				/*List<Integer> values_compare =  calc.valuesToCompare ( r, n, i, 0, index_parents ); //no valor de k vai 0, porque ainda nao o sabemos mas ele nao vai ser preciso, melhor solu�ao?
				Nij = calc.countNij (Data, index_parents,  values_compare);
				for(k=0;k<r[n];k++){
					values_compare =  calc.valuesToCompare ( r, n, i, k, index_parents );
					Nijk = calc.countNijk (Data, index_parents, values_compare);
					
					Tijk = (Nijk+0.5)/(Nij+0.5*r[n]);
					
					//System.out.println("Tijk: " + Tijk);
					tetas[t]=Tijk;
					t++;
				}*/
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
