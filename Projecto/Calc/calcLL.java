package Calc;

//import java.util.ArrayList;
import java.util.List;

public class calcLL extends calculate {


	public double LL(int [][] Data, int [][] mat_adj, int [] r){
		int i, j, n, k;
		double Nij, Nijk, score=0;
		
		for(n=0;n<r.length;n++){
			List<Integer> index_parents = indexesToCompare(mat_adj, n);
			j = getq(index_parents, r);
			for(i=0;i<j+1;i++){
				List<Integer> values_compare =  valuesToCompare ( r, n, i, 0, index_parents ); //no valor de k vai 0, porque ainda nao o sabemos mas ele nao vai ser preciso, melhor soluçao?
				Nij = countNij (Data, index_parents,  values_compare);
				//System.out.println("Nij: " + Nij);
				for(k=0;k<r[n];k++){
					values_compare =  valuesToCompare ( r, n, i, k, index_parents );
					Nijk = countNijk (Data, index_parents, values_compare);
				//	System.out.println("Nijk: " + Nijk);
					if(Nijk!=0 && Nij!=0){
						score = score + Nijk*(Math.log10(Nijk/Nij));
					}
				}
			}
		
		}
		return Math.abs(score);
	}
	
}
