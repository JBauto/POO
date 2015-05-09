package Grafo;
import DFS.*;


public class operations {
	

	REQ req = new REQ ();
	
	public int [][] add(int [][] mat_adj, int parent, int son){
		
		if(req.GetParents(mat_adj, son)) return mat_adj; //ve se o filho ja tem 3 pais
		
		int [][] mat_adj_ini = new int [mat_adj.length][mat_adj[0].length];
		int [][] mat_cycle = new int [mat_adj.length][mat_adj[0].length];
		
		
		for(int i =0; i<mat_adj.length;i++){
			for(int j=0; j<mat_adj[0].length;j++){
				mat_adj_ini[i][j] = mat_adj[i][j];
			}
		}
		
		if (mat_adj_ini[parent][son]==0) {
			mat_adj_ini[parent][son]=1;
			//System.out.println((parent+1) + " is now a parent of " + (son+1));
		
		} else return mat_adj;
		//else //System.out.println((parent+1) + "already is a parent of " + (son+1));
		for(int i =0; i<mat_adj.length;i++){
			for(int j=0; j<mat_adj[0].length;j++){
				mat_cycle[i][j] = mat_adj_ini[i][j];
			}
		}
	
		if(req.FindCycle(mat_cycle, parent)) {
			/*for (int i=0;i<mat_adj.length;i++){
				for (int j=0;j<(mat_adj[0].length);j++){
				System.out.print(mat_adj_ini[i][j] + " ");
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.println((parent+1) + " is now a parent of " + (son+1) + " but it's cycle");*/
			return mat_adj;//ve se é DAG
		}
		
		/*for (int i=0;i<mat_adj.length;i++){
			for (int j=0;j<(mat_adj[0].length);j++){
			System.out.print(mat_adj_ini[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();*/
		return mat_adj_ini;
	}
	
	public int [][] remove(int [][] mat_adj, int parent, int son){
		
		int [][] mat_adj_ini = new int [mat_adj.length][mat_adj[0].length];
		for(int i =0; i<mat_adj.length;i++){
			for(int j=0; j<mat_adj[0].length;j++){
				mat_adj_ini[i][j] = mat_adj[i][j];
			}
		}
			if (mat_adj[parent][son]==1) {
				mat_adj_ini[parent][son]=0;
				/*for (int i=0;i<mat_adj.length;i++){
					for (int j=0;j<(mat_adj[0].length);j++){
					System.out.print(mat_adj_ini[i][j] + " ");
					}
					System.out.println();
				}
				
				System.out.println();*/
				return mat_adj_ini;
			}else {
				//System.out.println((parent+1) + " is not parent of " + (son+1));
				return mat_adj;
			}
					
	}
	
	public int [][] flip(int [][] mat_adj, int parent, int son){
		int n = (mat_adj.length)/2;
		
		
		if (parent-n<0)return mat_adj;
		
		if (mat_adj[parent][son]==0) return mat_adj;
	
		
		
		if(req.GetParents(mat_adj, parent-n)) return mat_adj;//ve se o pai ja tem 3 pais
		
		int [][] mat_adj_ini = new int [mat_adj.length][mat_adj[0].length];
		int [][] mat_cycle = new int [mat_adj.length][mat_adj[0].length];
		
		for(int i =0; i<mat_adj.length;i++){
			for(int j=0; j<mat_adj[0].length;j++){
				mat_adj_ini[i][j] = mat_adj[i][j];
			}
		}
		/*
		System.out.println("Antes do flip:");
		for (int i=0;i<mat_adj.length;i++){
			for (int j=0;j<(mat_adj[0].length);j++){
			System.out.print(mat_adj_ini[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		*/
		if (mat_adj_ini[parent][son]==1) {
			mat_adj_ini[son+n][parent-n]=1;
			mat_adj_ini[parent][son]=0;
		} //else //System.out.println((parent+1) + " is not parent of " + (son+1));
		
		for(int i =0; i<mat_adj.length;i++){
			for(int j=0; j<mat_adj[0].length;j++){
				mat_cycle[i][j] = mat_adj_ini[i][j];
			}
		}
		
		if(req.FindCycle(mat_cycle, son)) return mat_adj; //ve se é DAG
		/*
		System.out.println("Depois do flip e de ver se ciclica:");
		for (int i=0;i<mat_adj.length;i++){
			for (int j=0;j<(mat_adj[0].length);j++){
			System.out.print(mat_adj_ini[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		*/
		return mat_adj_ini;
	}


}
