package Calc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import DFS.REQ;



public class calculate {


	public List<Integer>  indexesToCompare (int [][] mat_adj, int ind_son, int n){ // o primeiro valor � o seu index, e os outros v�m por ordem inversa, ou seja, se o elemento 2 tem como pai 3 e 5, devolve: 1 4 2
		int i;
		List<Integer> ind_parents = new ArrayList<Integer>();
		
		ind_parents.add(ind_son+n);
		
		for(i=mat_adj.length-1;i>-1;i--){ //de notar que mat_adj � semque quadrada logo o lenght � igual ao nr de colunas (e linhas)
			if (mat_adj[i][ind_son] == 1){
				
				ind_parents.add(i); //se o pai for X2 fica com o indice 1, etc
				
			}
			
		}
		
		return ind_parents;
		
	}
	
	public int getq (List<Integer> ind_parents, int [] r){ // assume que j pode ser zero, ou seja, se houver 2 possibilidades fica j=0 ou j=1 (como no exemplo dos apontamentos), e nao j=1 ou j=2 (como no exemplo do enunciado)
		int j=0,i = 0, ji;
		
		if(ind_parents.size()==1)return j; //nao tem pais
		
		j=r[ind_parents.get(1)]-1;// o indice ta ao contrario (come�a pelo ultimo pai) mas j final, que � o que interessa, deve ser igual independentemente da ordem
		
		Iterator<Integer> it = ind_parents.iterator();
		Iterator<Integer> it2;
		
		do{ 
			it.next();
			i++;
		}while(i<2);
		
		for(it2=it;it2.hasNext();){ //passa o primeiro da lista que � ele proprio, ver apontamentos da professora		
			int poss_parent = it2.next();
			ji=(j+1)*(r[poss_parent]-1);//esquisito mas bate certo para os que testei ate agora
			j=j+ji;
		}
		
		return j;
	}
	
	public List<Integer>  valuesToCompare (int [] r, int ind_son, int j, int k, List<Integer> ind_parents ){ //na minha cabe�a funciona, ta um bocado confuso, por testar!!
		List<Integer> values = new ArrayList<Integer>();  //ind_son nao parece ser preciso
		int nr_parents =ind_parents.size() -1;
		int l = 1;
		
		values.add(k);
		
		if (nr_parents==0) return values;
		
		Iterator<Integer> it = ind_parents.iterator();
		it.hasNext();
		int poss_parent = it.next();
		Iterator<Integer> it2;
		
		for(it2 = it;l<nr_parents;l++){ // quanto temos j e queremos j1, j2 etc, ver apontamentos da professora, mete primeiro o do ultimo pai
			poss_parent = it2.next();
			values.add(j%r[poss_parent]);
			j=(j-(j%r[poss_parent]))/(r[poss_parent]);
		}
			poss_parent = it2.next();
			values.add(j%r[poss_parent]); //por testar!!! (mais que um pai)
		
		return values;
	}
	
	public double countNijk (int [][] Data, List<Integer> index_compare, List<Integer> values_compare){
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
	/*
	public int countNij (int [][] Data, List<Integer> index_compare, List<Integer> values_compare){ //provavel que esteja mal
		int Nij=0, i, t;
		
		if(index_compare.size()==1)return Data.length;
		
		for(i=0; i< Data.length; i++){
			t=0;
			Iterator<Integer> it2 = values_compare.iterator();
			Iterator<Integer> it = index_compare.iterator();
			@SuppressWarnings("unused")
			int tmp = it.next();
			tmp = it2.next();
			while(it.hasNext()){		//passa ele proprio	
				int i_compare = it.next();
				int v_compare = it2.next();
				if(Data[i][i_compare] != v_compare) {
					break;			
				}else t++;			
			}
			if (t==index_compare.size()-1) Nij++;
		}
		
		return Nij;
	}
	
*/
		

}
