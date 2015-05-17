package Inference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Calc.*;



public class infer {

	public int composeJ (int [] r, List <Integer> index_parents, List <Integer> j_values){ //parents_value tem o valor dos pais ordenados de ordem decrescente de acordo com o indice dos mesmos
		int j=0,nr_parents,i;
		nr_parents=index_parents.size()-1;
		
		if(nr_parents==0)return j;
		
		i=nr_parents-1;
		/*
		Iterator<Integer> it = j_values.iterator();
		j=it.next();
		*/
		j=j_values.get(nr_parents-1);
		nr_parents--;
		/*
		Iterator<Integer> it2 = index_parents.iterator();
		it2.next(); // para passar o primeiro e segundo
		it2.next();
		*/
		
		while (nr_parents>0){
		
			j = j * r[index_parents.get(i)];
			j=j+j_values.get(nr_parents-1);
			i--;
			nr_parents--;
		}
		
		return j;
	}
	
	public int inf (int [][] test, int [][] mat_train, int [] r , int son, List<double[]> tetas, int linha){
		int most_likely=-1, j; 
		calculate calc = new calculate();
		List<Integer> j_values = new ArrayList<Integer>();
		List<Integer> index_parents = calc.indexesToCompare(mat_train, son -r.length/2 , r.length/2);
		
		if(index_parents.size()==1)return most_likely;
			
		
		int ind_parent;
		
			Iterator<Integer> it = index_parents.iterator();
			it.next(); // para passar o primeiro
			
			while(it.hasNext()){	//ve para todos os pais
				ind_parent = it.next();
				if(ind_parent < r.length/2){
					j_values.add(test[linha][ind_parent]);
				}else{
					j_values.add(inf ( test, mat_train,  r , ind_parent,  tetas, linha));
				}	
			}
			
			double pmax=0, teta;
			
			for(int k=0; k<r[index_parents.get(0)]; k++){
				
				j = composeJ (r,  index_parents,  j_values);
								
				teta = tetas.get(son)[(j*r[son])+k]; //meter iterador??? só vai buscar um valor, nao e suposto percorrer			
				
				if(teta>pmax){
					pmax=teta;
					most_likely = k;
				}
			}
					
		return most_likely;
	}
	
}
