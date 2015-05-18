package Inference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Calc.*;



public class infer {

	/** composeJ gets the value corresponding to all the parents configuration combined
	 * 
	 * @param r contains the number of values each variable can take 
	 * @param index_parents is a list that contains the indexes of a specific node and his parents
	 * @param j_values contains the value of the each of the parents
	 * @return integer corresponding to the configuration of the parents
	 */
	public int composeJ (int [] r, List <Integer> index_parents, List <Integer> j_values){ //parents_value tem o valor dos pais ordenados de ordem decrescente de acordo com o indice dos mesmos
		int j=0,nr_parents,i;
		nr_parents=index_parents.size()-1;
		
		if(nr_parents==0)return j;
		
		i=nr_parents-1;
	
		j=j_values.get(nr_parents-1);
		nr_parents--;
	
		
		while (nr_parents>0){
		
			j = j * r[index_parents.get(i)];
			j=j+j_values.get(nr_parents-1);
			i--;
			nr_parents--;
		}
		
		return j;
	}
	

	/** inf gets the most likely value a certain variable is expected to take, in accordance to the value of the respective parents 
	 * present in one of the lines of the received Data(test).
	 * In case the method can't infer a value, because is independent or none of the parents is in t, it returns "-1"
	 * 
	 * @param test which contains the values from which the method is going to infer a certain value
	 * @param mat_train contains the connections between the variables, in t and t+1, with the highest score
	 * @param r contains the number of values each variable can take 
	 * @param son is the variable to which the value is being inferred 
	 * @param tetas is the list of arrays containing the probability of each son and parent configuration
	 * @return integer corresponding to the value inferred for the variable son
	 */
	public int inf (int [][] test, int [][] mat_train, int [] r , int son, List<double[]> tetas, int line){//son do 7 ao 13
		int most_likely=-1, j, i; 
		calculate calc = new calculate();
		List<Integer> j_values = new ArrayList<Integer>();
		List<Integer> index_parents = calc.indexesToCompare(mat_train, son -r.length/2 , r.length/2);//manda filho do 0 ao 6
		
		if(index_parents.size()==1)return most_likely;
	
		
		int ind_parent;
		
			Iterator<Integer> it = index_parents.iterator();
			it.next(); 
			
			while(it.hasNext()){
				ind_parent = it.next();
				if(ind_parent < r.length/2){
					j_values.add(test[line][ind_parent]);
				}else{
					i=inf ( test, mat_train,  r , ind_parent,  tetas, line);
					if(i!=-1){
						j_values.add(i);
					}else {
						return most_likely;
					}
				}	
			}
			
			double pmax=0, teta;
			
			for(int k=0; k<r[index_parents.get(0)]; k++){
				
				j = composeJ (r,  index_parents,  j_values);
							
				//System.out.println("j é: " + j);
				teta = tetas.get(son)[(j*r[son])+k]; 			
				
				if(teta>pmax){
					pmax=teta;
					most_likely = k;
				}
			}
					
		return most_likely;
	}
	
}
