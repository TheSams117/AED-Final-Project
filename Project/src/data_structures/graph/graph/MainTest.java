package graph;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Queue;
import vertex.Vertex;

public class MainTest {

	public MainTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException {
		Graph<String, Integer, Integer, String> graph = new Graph<String, Integer, Integer, String>(Graph.INDIRECTED_GRAPH);
//		graph.add(0, "S");
//		graph.add(1, "1");
//		graph.add(2, "2");
//		graph.add(3, "3");
//		graph.add(4, "4");
//		graph.add(5, "5");
//		graph.addEdgeBetweenVertices(0, 2, 5, "", 0);
//		graph.addEdgeBetweenVertices(0, 1, 1, "", 1);
//		graph.addEdgeBetweenVertices(1, 3, 10, "", 0);
//		graph.addEdgeBetweenVertices(2, 3, 7, "", 0);
//		graph.addEdgeBetweenVertices(2, 4, 9, "", 1);
//		graph.addEdgeBetweenVertices(3, 5, 2, "", 0);
//		Queue<Vertex<String, Integer, Integer, String>> a =graph.DFS(0);
//		while (!a.isEmpty()) {
//			System.out.println(a.remove().getValue());
//		}
		
//		graph.add(1, "1");
//		graph.add(2, "2");
//		graph.add(3, "3");
//		graph.add(4, "4");
//		graph.add(5, "5");
//		graph.addEdgeBetweenVertices(1, 2, -4, "", 0);
//		graph.addEdgeBetweenVertices(1, 3, 1, "", 1);
//		graph.addEdgeBetweenVertices(2, 3, -10, "", 2);
//		graph.addEdgeBetweenVertices(2, 4, 6, "", 3);
//		graph.addEdgeBetweenVertices(4, 3, -8, "", 4);
//		graph.addEdgeBetweenVertices(4, 5, 5, "", 5);
//		graph.addEdgeBetweenVertices(5, 3, -6, "", 6);
//		
//		 Integer[] array = graph.Dijkstra(3);
//		 for (int i = 0; i < array.length; i++) {
//			System.out.println(array[i]+"");
//		}
//		Integer[][] matrix1 = graph.getAdjacencyMatrixWeight();
//		 for (int i = 0; i < matrix1.length; i++) {
//			for (int j = 0; j < matrix1.length; j++) {
//				System.out.print(matrix1[i][j]+" ");
//			}
//			System.out.println("");
//		}
//
//		 
//		 Integer[][] matrix = graph.floydWarshall();
//		 for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix.length; j++) {
//				System.out.print(matrix[i][j]+" ");
//			}
//			System.out.println("");
//		}

		
		graph.add(1, "1");
		graph.add(2, "2");
		graph.add(3, "3");
		graph.add(4, "4");
		
//		graph.addEdgeBetweenVertices(1, 1, 0, "", 0);
//		graph.addEdgeBetweenVertices(2, 2, 0, "", 0);
//		graph.addEdgeBetweenVertices(3, 3, 0, "", 0);
//		graph.addEdgeBetweenVertices(4, 4, 0, "", 0);
		
		graph.addEdgeBetweenVertices(1, 2, 3, "", 1);
		graph.addEdgeBetweenVertices(1, 3, 2, "", 2);
		graph.addEdgeBetweenVertices(1, 4, 1, "", 3);
		graph.addEdgeBetweenVertices(2, 3, 4, "", 1);
		graph.addEdgeBetweenVertices(2, 4, 1, "", 2);
		graph.addEdgeBetweenVertices(4, 3, 6, "", 1);
		Graph<String, Integer, Integer, String> graph2 = graph.PRIM(1);
		Integer[][] matrix1 = graph2.getAdjacencyMatrixWeight();
		 for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1.length; j++) {
				System.out.print(matrix1[i][j]+" ");
			}
			System.out.println("");
		}
		 
		 System.out.println("");
		 System.out.println("");
		 
		Integer[][] matrix = graph.floydWarshall();
		 for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
		
	}

}
