package Grafo;

public class operations {
	

	
	
	public int [][] add(int [][] mat_adj, int parent, int son){
			
		//if(o filho ja tem tres pais)return mat_adj;//ve se o filho ja tem 3 pais
		
		//int [][] mat_adj_ini = mat_adj;
		
		if (mat_adj[parent][son]==0) mat_adj[parent][son]=1;
		else System.out.println((parent+1) + "already is a parent of " + (son+1));
		
		//if(mat_adj for ciclico) return mat_adj_ini;//ve se é DAG
		
		return mat_adj;
	}
	
	public int [][] remove(int [][] mat_adj, int parent, int son){
			
			if (mat_adj[parent][son]==1) mat_adj[parent][son]=0;
			else System.out.println((parent+1) + " is not parent of " + (son+1));
			
			return mat_adj;
	}
	
	public int [][] flip(int [][] mat_adj, int parent, int son){
		
		//if(se o pai ja tem tres pais)return mat_adj;//ve se o pai ja tem 3 pais
		
		//int [][] mat_adj_ini = mat_adj;
		
		if (mat_adj[parent][son]==1) {
			mat_adj[son][parent]=1;
			mat_adj[parent][son]=0;
		} else System.out.println((parent+1) + " is not parent of " + (son+1));
		
		
		
		//if(mat_adj for ciclico) return mat_adj_ini;//ve se é DAG
		
		return mat_adj;
	}


}
