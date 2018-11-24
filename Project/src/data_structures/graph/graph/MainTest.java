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

		graph.add(1, "1");
		graph.add(2, "2");
		graph.add(3, "3");
		graph.add(4, "4");
		graph.addEdgeBetweenVertices(1, 1, 0, "", 9);
		graph.addEdgeBetweenVertices(2, 2, 0, "", 8);
		graph.addEdgeBetweenVertices(3, 3, 0, "", 7);
		graph.addEdgeBetweenVertices(4, 4, 0, "", 6);
		graph.addEdgeBetweenVertices(5, 5, 0, "", 4);
		
		graph.addEdgeBetweenVertices(1, 2, 250, "",1);
		graph.addEdgeBetweenVertices(1, 3, 1300, "",2);
		graph.addEdgeBetweenVertices(1, 4, 330, "",3);
		graph.addEdgeBetweenVertices(2, 3, 110, "", 1);
		graph.addEdgeBetweenVertices(2, 4, 580, "", 2);
		graph.addEdgeBetweenVertices(4, 3, 1300, "", 1);
		
//		for (int i = 1; i < 6; i++) {
//			graph.add(i, i+"");
//		}
//		graph.addEdgeBetweenVertices(1, 2, 800, "", 1);
//		graph.addEdgeBetweenVertices(1, 3, 1000, "", 2);
//		graph.addEdgeBetweenVertices(1, 4, 1600, "", 3);
//		graph.addEdgeBetweenVertices(1, 5, 2000, "", 4);
//		
//		graph.addEdgeBetweenVertices(2, 3, 700, "", 5);
//		graph.addEdgeBetweenVertices(2, 4, 1400, "", 6);
//		graph.addEdgeBetweenVertices(2, 5, 2200, "", 7);
//		
//		graph.addEdgeBetweenVertices(3, 4, 1300, "", 8);
//		graph.addEdgeBetweenVertices(3, 5, 1200, "", 9);
//		
//		graph.addEdgeBetweenVertices(4, 5, 900, "", 10);
		
		
		
		Graph<String, Integer, Integer, String> graph2 = graph.PRIM(1);
		Integer[][] matrix1 = graph2.floydWarshall();
		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1.length; j++) {
				System.out.print(matrix1[i][j]+" ");
			}
			System.out.println("");
		}
		 
		 System.out.println("");
		 System.out.println("");
		 
//		Integer[][] matrix = graph2.floydWarshall();
//		 for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix.length; j++) {
//				System.out.print(matrix[i][j]+" ");
//			}
//			System.out.println("");
//		}
		 
		
	}

}
