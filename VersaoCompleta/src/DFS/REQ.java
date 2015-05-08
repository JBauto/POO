package DFS;

import java.util.Stack;

public class REQ {
	private Stack<Integer> stack;
	int row,col;
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

	public boolean FindCycle(int[][] matrix, int start) {
		stack = new Stack<Integer>();
		
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

		return false;
	}
	private int	correctindex (int i){
		if (i < col) {
			return i+col;
		}else{
			return i-col;
		}
	}
	public static void main(String[] arg) {
		int yolo[][] = {{ 0, 1, 0, 1}, 
						{ 0, 0, 0, 0}, 
						{ 1, 0, 0, 0}, 
						{ 0, 0, 0, 0}, 
						{ 0, 0, 0, 1}, 
						{ 0, 0, 1, 0},
						{ 1, 0, 0, 0}, 
						{ 0, 0, 1, 0}
						};
						
 
	
		REQ teste = new REQ ();
		if(teste.GetParents(yolo,0)){
			System.out.println("3 parents");
		}else{
			System.out.println("<3 parents");
		}
		if(teste.FindCycle(yolo,3)){
			System.out.println("Its Cycle");
		}else{
			System.out.println("Not Cycle");

		}
	}
}