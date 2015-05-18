package Calc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class calculate {

	/** indexesToCompare provides the indexes of the variable being evaluated and his parents, if any.
	 * In the first index we have the son and then the parents in descending order.
	 * Ex.: if variable 3, has as parents the variables 1, 4 and 7 -> indexesToCompare = [2 6 3 0]
	 * 
	 * @param mat_adj contains the connections between the variables in t and t+1
	 * @param ind_son is the index of the variable in t+1 being evaluated
	 * @param n contains the number of variables in t
	 * @return list of integers with the indexes of a variable and his parents
	 */
	public List<Integer>  indexesToCompare (int [][] mat_adj, int ind_son, int n){ // o primeiro valor � o seu index, e os outros v�m por ordem inversa, ou seja, se o elemento 2 tem como pai 3 e 5, devolve: 1 4 2
		int i;
		List<Integer> ind_parents = new ArrayList<Integer>();
		
		ind_parents.add(ind_son+n);
		
		for(i=mat_adj.length-1;i>-1;i--){ 
			if (mat_adj[i][ind_son] == 1){				
				ind_parents.add(i); //se o pai for X2 fica com o indice 1, etc				
			}
			
		}
		
		return ind_parents;
		
	}
	

	/** getq provides the maximum number of possible parent combinations of a specific variable (present in the first index of 
	 * ind_parents).
	 * 
	 * @param index_parents is a list that contains the indexes of a specific node and his parents
	 * @param r contains the number of values each variable can take 
	 * @return integer representing the number of possible parent configurations
	 */
	int getq (List<Integer> ind_parents, int [] r){ 
		int j=0, ji;
		
		if(ind_parents.size()==1)return j; 
		
		j=r[ind_parents.get(1)]-1;
		Iterator<Integer> it = ind_parents.iterator();
		//Iterator<Integer> it2;
		
		
			it.next();
			it.next();
		
		for(;it.hasNext();){	
			int poss_parent = it.next();
			ji=(j+1)*(r[poss_parent]-1);
			j=j+ji;
		}
		
		return j;
	}
	
	/** valuesToCompare calculates the values of the node being evaluated and his parents, by decomposing the parameter j.
	 * The order in which the values of the parents are returned is in decreasing order according to their indexes, being compatible 
	 * with indexesToCompare.
	 * 
	 * @param r contains the number of values each variable can take 
	 * @param j configuration of parents, to be decomposed in this method
	 * @param k value of the variable being evaluated
	 * @return list of integers with the values of a variable and his parents
	 */
	List<Integer>  valuesToCompare (int [] r, int j, int k, List<Integer> ind_parents ){ 
		List<Integer> values = new ArrayList<Integer>();  
		int nr_parents =ind_parents.size() -1;
		int l = 1;
		
		values.add(k);
		
		if (nr_parents==0) return values;
		
		Iterator<Integer> it = ind_parents.iterator();
		it.hasNext();
		int poss_parent = it.next();
		Iterator<Integer> it2;
		
		for(it2 = it;l<nr_parents;l++){ 
			poss_parent = it2.next();
			values.add(j%r[poss_parent]);
			j=(j-(j%r[poss_parent]))/(r[poss_parent]);
		}
			poss_parent = it2.next();
			values.add(j%r[poss_parent]);
		
		return values;
	}
	
	/** countNijk counts the number of times a specific set of numbers, those being the values of a node and his parents, is present 
	 * in a certain Data in the correct position.
	 * 
	 * @param Data which contains the values from which the Nijk is going to calculated
	 * @param index_parents is a list that contains the indexes of a specific node and his parents
	 * @param values_compare contains the values of a specific node and his parents
	 * @return integer with the number of times the set of numbers received, in the respective position, is present in Data
	 */
	double countNijk (int [][] Data, List<Integer> index_compare, List<Integer> values_compare){
		int  i, equal;
		double Nijk=0;
		for(i=0; i< Data.length; i++){
			equal=0;
			Iterator<Integer> it2 = values_compare.iterator();
			for(Iterator<Integer> it = index_compare.iterator(); it.hasNext();){
				int i_compare = it.next();
				int v_compare = it2.next();
				if(Data[i][i_compare] != v_compare){
					break;
				}else equal++;
				
			}
			if(index_compare.size()==equal) Nijk++;
			
		}
		
		
		return Nijk;
	}

}
