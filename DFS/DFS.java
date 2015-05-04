package DFS;

import java.util.Stack;

public class REQ {
	private Stack<Integer> stack;
	int dim;
	public boolean GetParents(int [][] graph, int col) {
		int i, count=0;
		dim=graph.length;

		for (i = 0; i < dim; i++) {
			if (graph[i][col] == 1)
				count++;
				if (count == 3) {
					return true;
				}
			}
		return false;
	}

	public boolean FindCycle(int[][] matrix, int start) {
		stack = new Stack<Integer>();
		
		int visited[] = new int[dim];
		int node = start;
		int finish = start;
		visited[start] = 1;
		stack.push(start);

		while (!stack.isEmpty()) {
			node = stack.peek();
			finish = node;

			while (finish < dim) {
				if (matrix[node][finish] == 1 && visited[finish] == 1) {
					if (stack.contains(finish)) {
						return true;
					}
				}
				if (matrix[node][finish] == 1 && visited[finish] == 0) {
					stack.push(finish);
					visited[finish] = 1;
					matrix[node][finish] = 0;
					node = finish;
					finish = 1;
					continue;
				}
				finish++;
			}
			stack.pop();
		}

		return false;
	}

	public static void main(String... arg) {
		int yolo[][] = { { 0, 0, 0, 0, 0, 0 }, 
						 { 0, 0, 0, 0, 0, 0 },
						 { 0, 0, 0, 1, 0, 0 },
						 { 0, 0, 0, 1, 0, 0 },
						 { 0, 0, 0, 1, 0, 0 },
						 { 0, 0, 0, 0, 0, 0 }};
		REQ teste = new REQ ();
		if(teste.GetParents(yolo,3)){
			System.out.println("3 parents");
		}else{
			System.out.println("<3 parents");
		}
		if(teste.FindCycle(yolo,0)){
			System.out.println("Its Cycle");
		}else{
			System.out.println("Not Cycle");

		}
	}
}
