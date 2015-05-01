package DFS;

import java.util.Stack;

public class REQ {
	private int[][] graph;
	private int dim;
	private Stack<Integer> stack;

	REQ(int[][] graph) {
		dim = graph.length;
		this.graph = graph;
		stack = new Stack<Integer>();
	}

	public boolean GraphCycle() {
		int i, j, count;
		for (j = 0; j < dim; j++) {
			count = 0;
			for (i = 0; i < dim; i++) {
				if (graph[i][j] == 1)
					count++;
				if (count == 4) {
					System.out.println("Too many Parents");
					return false;
				}
			}
		}

		for (i = 0; i < dim; i++) {
			if (FindCycle(graph, i)) {
				System.out.println("Cycle" + " " + i);
				return true;
			}
		}
		System.out.println("Not Cycle");
		return false;
	}

	private boolean FindCycle(int[][] matrix, int start) {

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
		int yolo[][] = { { 0, 1, 0, 0, 1 }, 
					     { 0, 0, 0, 0, 0 },
						 { 1, 1, 0, 1 , 1 },
						 { 0, 0, 0, 0, 0 },
						 { 0, 0, 0, 0, 0 } };
		REQ teste = new REQ(yolo);
		teste.GraphCycle();
	}
}
