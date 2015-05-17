package DFS;

import java.util.Stack;

public class REQ {
	private Stack<Integer> stack;
	
	public long time;
	int row,col;
	/** Checks if the son of the given graph, as already 3 parents
	 * 
	 * @param graph	matrix with the graph that will be analyze
	 * @param son node that will be analyze
	 * @return returns 1, if son has three parents; returns 0 if has less
	 */
	public boolean GetParents(int [][] graph, int son) {
		int i, count=0;
		row=graph.length;
		col=graph[0].length;
		for (i = 0; i < row; i++) {
			if (graph[i][son] == 1)
				count++;
				if (count == 3) {
					return true;
				}
			}
		return false;
	}
	/** Checks if the given graph is cycle or not
	 * 
	 * @param matrix matrix with the graph that will be analyze
	 * @param start node where the program will start to run
	 * @return returns 1, if graph is cycle; returns 0 otherwise
	 */
	public boolean FindCycle(int[][] matrix, int start) {
		stack = new Stack<Integer>();
		
		long tstart = System.nanoTime();
		
		int visited[] = new int[col];
		int node = start;
		int finish = 0;
		if (start>=col)
			visited[correctindex(start)] = 1;
		stack.push(start);

		while (!stack.isEmpty()) {
			node = stack.peek();
			finish = 0;

			while (finish < col) {
				if (matrix[node][finish] == 1 && visited[finish] == 1) {
					if (stack.contains(correctindex(finish))) {
						return true;
					}
				}
				if (matrix[node][finish] == 1 && visited[finish] == 0) {
					stack.push(correctindex(finish));
					visited[finish] = 1;
					matrix[node][finish] = 0;
					node = correctindex(finish);
					finish = 0;
					continue;
				}
				finish++;
			}
			stack.pop();
		}
		long tend = System.nanoTime();
		time = time +(tend-tstart);
		return false;
	}
	private int	correctindex (int i){
		if (i < col) {
			return i+col;
		}else{
			return i-col;
		}
	}
}