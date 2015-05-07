package Grafo;
import DFS.*;
public class operations {
	

	REQ req = new REQ ();
	
	public int [][] add(int [][] mat_adj, int parent, int son){
		
		if(req.GetParents(mat_adj, son)) return mat_adj; //ve se o filho ja tem 3 pais
		
		int [][] mat_adj_ini = mat_adj;
		
		if (mat_adj[parent][son]==0) mat_adj[parent][son]=1;
		//else //System.out.println((parent+1) + "already is a parent of " + (son+1));
		
		if(req.FindCycle(mat_adj, parent)) return mat_adj_ini;//ve se é DAG
		
		return mat_adj;
	}
	
	public int [][] remove(int [][] mat_adj, int parent, int son){
			
			if (mat_adj[parent][son]==1) mat_adj[parent][son]=0;
			//else //System.out.println((parent+1) + " is not parent of " + (son+1));
			
			return mat_adj;
	}
	
	public int [][] flip(int [][] mat_adj, int parent, int son){
		int n = mat_adj[0].length;
		int parent_aux,son_aux;
	
		if (parent-n<0)
			return mat_adj;
		else
			son_aux=son-n/2;
			parent_aux=parent+n/2;	
		if(req.GetParents(mat_adj, parent_aux)) return mat_adj;//ve se o pai ja tem 3 pais
		
		int [][] mat_adj_ini = mat_adj;
		
		if (mat_adj[parent][son]==1) {
			mat_adj[son][parent]=1;
			mat_adj[parent][son]=0;
		} //else //System.out.println((parent+1) + " is not parent of " + (son+1));
		
		
		
		if(req.FindCycle(mat_adj, son)) return mat_adj_ini; //ve se é DAG
		
		return mat_adj;
	}


}
