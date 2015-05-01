package DFS;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;


public class DFS {
	private int[][] graph;
	private int dim;
	private Stack<Integer> stack;

	DFS(int[][] graph) {
		dim = graph.length;
		this.graph = graph;
		stack = new Stack<Integer>();
	}

	public boolean GraphCycle() {
		int i;
		for (i = 0; i < dim; i++) {
			if (FindCycle(graph, i)) {
				System.out.println("Cycle" +" "+ i);
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
		int yolo [][]={{0,1,0,0,0,0,0,0},
					   {0,0,1,1,1,0,0},
					   {0,0,0,0,0,1,0},
					   {0,0,0,0,1,0,0},
					   {0,0,0,0,1,0,1},
					   {0,0,0,0,0,1,0},
					   {0,0,0,0,0,0,1},
					   {0,0,0,0,0,0,0}
	   };
		DFS teste = new DFS(yolo);
		teste.GraphCycle();
	}
}
