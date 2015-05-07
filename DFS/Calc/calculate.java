package Calc;

import java.util.ArrayList;
import java.util.List;



public class calculate {


	public List<Integer>  indexesToCompare (int [][] mat_adj, int ind_son){ // o primeiro valor é o seu index, e os outros vêm por ordem inversa, ou seja, se o elemento 2 tem como pai 3 e 5, devolve: 1 4 2
		int i;
		List<Integer> ind_parents = new ArrayList<Integer>();
		
		ind_parents.add(ind_son);
		
		for(i=mat_adj.length-1;i>-1;i--){ //de notar que mat_adj é semque quadrada logo o lenght é igual ao nr de colunas (e linhas)
			if (mat_adj[i][ind_son] == 1){
				
				ind_parents.add(i); //se o pai for X2 fica com o indice 1, etc
				
			}
			
		}
		
		return ind_parents;
		
	}
	
	public int getq (List<Integer> ind_parents, int [] r){ // assume que j pode ser zero, ou seja, se houver 2 possibilidades fica j=0 ou j=1 (como no exemplo dos apontamentos), e nao j=1 ou j=2 (como no exemplo do enunciado)
		int j=0,i, ji;
		
		if(ind_parents.size()==1)return j; //nao tem pais
		
		j=r[ind_parents.get(1)]-1;// o indice ta ao contrario (começa pelo ultimo pai) mas j final, que é o que interessa, deve ser igual independentemente da ordem
		
		for(i=2;i<ind_parents.size();i++){ //passa o primeiro da lista que é ele proprio, ver apontamentos da professora		
			ji=(j+1)*(r[ind_parents.get(i)]-1);//esquisito mas bate certo para os que testei ate agora
			j=j+ji;
		}
		
		return j;
	}
	
	public List<Integer>  valuesToCompare (int [] r, int ind_son, int j, int k, List<Integer> ind_parents ){ //na minha cabeça funciona, ta um bocado confuso, por testar!!
		List<Integer> values = new ArrayList<Integer>();  //ind_son nao parece ser preciso
		int nr_parents =ind_parents.size() -1;
		int l;
		
		values.add(k);
		
		if (nr_parents==0) return values;
		
		
		for(l=1;l<nr_parents;l++){ // quanto temos j e queremos j1, j2 etc, ver apontamentos da professora, mete primeiro o do ultimo pai
			values.add(j%r[ind_parents.get(l)]);
			j=(j-(j%r[ind_parents.get(l)]))/(r[ind_parents.get(l)]);
			
		}
	
			values.add(j%r[ind_parents.get(l)]); //por testar!!! (mais que um pai)
		
		return values;
	}
	
	public int countNijk (int [][] Data, List<Integer> index_compare, List<Integer> values_compare){
		int Nijk=0, i, j, equal;
		
		for(i=0; i< Data.length; i++){
			equal=0;
			for(j=0; j<index_compare.size(); j++){
				
				if(Data[i][index_compare.get(j)]==values_compare.get(j)) equal++;
				
			}
			if(index_compare.size()==equal)Nijk++;
			
		}
		
		
		return Nijk;
	}
	
	public int countNij (int [][] Data, List<Integer> index_compare, List<Integer> values_compare){ //provavel que esteja mal
		int Nij=0, i, j, t;
		
		if(index_compare.size()==1)return Data.length;
		
		for(i=0; i< Data.length; i++){
			t=0;
			for(j=1; j<index_compare.size(); j++){		//passa ele proprio	
				if(Data[i][index_compare.get(j)]==values_compare.get(j)) t++;			
			}
			if (t==index_compare.size()-1) Nij++;
		}
		
		return Nij;
	}
	

		

}
