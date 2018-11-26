/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.triadamcola.data_structures.graph.edge.Edge;
import com.triadamcola.data_structures.graph.graph.Graph;
import com.triadamcola.data_structures.graph.vertex.Vertex;

import junit.framework.TestCase;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ASUS
 */
public class GraphTest extends TestCase{
	
	private Graph<Integer, Integer, Integer, Integer> graph;
	private Integer[][] matrix;
	private Queue<Vertex<Integer,Integer,Integer,Integer>> visited;
	
	private void setupStage1() {
		graph = new Graph<Integer, Integer, Integer, Integer>(Graph.DIRECTED_GRAPH);
		
		for (int i = 0; i < 7; i++) {
			graph.add(i,i);
			
		}
		int edgeId = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				graph.addEdgeBetweenVertices(i, j, 0, 0, edgeId);
				edgeId += 1;
			}
		}
		
	}
	
	private void setupStage2() {
		graph = new Graph<Integer, Integer, Integer, Integer>(Graph.INDIRECTED_GRAPH);
		
		Integer[][] matrix = {{0 , 12, 40, 12, 32},
							  {12, 0,  32, 12, 42},
							  {40, 32, 0 , 12, 42},
							  {12, 12, 12, 0 , 78},
							  {32, 42, 42, 78, 0 }};
		this.matrix = matrix;
		
		for (int i = 0; i < matrix.length; i++) {
			graph.add(i,i);
			
		}
		
		for (int i = 0; i <matrix.length; i++) {
			for (int j = 0; j != i+1; j++) {
				
				graph.addEdgeBetweenVertices(i, j, matrix[i][j], 0, j );
				
			}
		}
		
	}
	
	private void setupStage3() {
		graph = new Graph<Integer, Integer, Integer, Integer>(Graph.INDIRECTED_GRAPH);
		visited = new LinkedList<>();
		Integer[][] matrix = {{null, 1   , 2   , null, null, null},
							  {1   , null, null ,6   , null, null},
							  {2   , null, null ,7   , 9   , null},
							  {null, 6   , 7    ,null, null, 10  },
							  {null, null, 9    ,null, null, null},
							  {null, null, null ,10  , null, null}};
		this.matrix = matrix;
		
		for (int i = 0; i < matrix.length; i++) {
			graph.add(i,i);
		}
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(0, 0));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(1, 1));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(2, 2));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(3, 3));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(4, 4));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(5, 5));
		int edgeId = 36;
		for (int i = 0; i <matrix.length; i++) {
			for (int j = 0; j != i+1; j++) {
				if(matrix[i][j]!=null) {
					graph.addEdgeBetweenVertices(i, j, matrix[i][j], 0, edgeId );
					edgeId-=1;
				}
				
				
			}
		}
		
	}
	
	
	private void setupStage4() {
		graph = new Graph<Integer, Integer, Integer, Integer>(Graph.INDIRECTED_GRAPH);
		visited = new LinkedList<>();
		Integer[][] matrix = {{null, 1   , 1   , null, null, null},
							  {1   , null, null ,1   , null, null},
							  {1   , null, null ,1   , 1   , null},
							  {null, 1   , 1    ,null, null, 1   },
							  {null, null, 1    ,null, null, null},
							  {null, null, null ,1   , null, null}};
		this.matrix = matrix;
		
		for (int i = 0; i < matrix.length; i++) {
			graph.add(i,i);
		}
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(0, 0));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(2, 2));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(3, 3));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(5, 5));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(4, 4));
		visited.add(new Vertex<Integer, Integer, Integer, Integer>(1, 1));
		int edgeId = 36;
		for (int i = 0; i <matrix.length; i++) {
			for (int j = 0; j != i+1; j++) {
				if(matrix[i][j]!=null) {
					graph.addEdgeBetweenVertices(i, j, matrix[i][j], 0, edgeId );
					edgeId-=1;
				}
				
				
			}
		}	
	}
	
	private void setupStage5() {
		graph = new Graph<Integer, Integer, Integer, Integer>(Graph.INDIRECTED_GRAPH);
		Integer[][] matrix = {{0   , 4   , 1   , null, null},
							  {4   , 0   , 10  , 6   , null},
							  {1   , 10  , 0   , 8   , 6   },
							  {null, 6   , 8   , 0   , 5   },
							  {null, null, 6   , 5   , 0   }};
		
		for (int i = 0; i < matrix.length; i++) {
			graph.add(i,i);
		}
		int edgeId = 0;
		for (int i = 0; i <matrix.length; i++) {
			for (int j = 0; j != i+1; j++) {
				if(matrix[i][j]!=null) {
					graph.addEdgeBetweenVertices(i, j, matrix[i][j], 0, edgeId );
					edgeId+=1;
				}
				
				
			}
		}	
	}
	
	private void setupStage6() {
		graph = new Graph<Integer, Integer, Integer, Integer>(Graph.DIRECTED_GRAPH);
		Integer[][] matrix = {{0   , null, -2  , null},
							  {4   , 0   , 3   , null},
							  {null, null, 0   , 2   },
							  {null, -1  , null, 0   }};
		this.matrix = matrix;
		
		for (int i = 0; i < matrix.length; i++) {
			graph.add(i,i);
		}
		int edgeId = 0;
		for (int i = 0; i <matrix.length; i++) {
			for (int j = 0; j<matrix.length; j++) {
				if(matrix[i][j]!=null) {
					graph.addEdgeBetweenVertices(i, j, matrix[i][j], 0, edgeId );
					edgeId+=1;
				}
				
				
			}
		}	
	}
	
	public void testAdd() {
		setupStage1();
		assertTrue(graph.getNumberOfVertices() == 7);
	}
	
	public void testAddEdgeBetweenVertices() {
		setupStage1();
		assertTrue(graph.getNumberOfEdges() == 49);
	}
	
	public void testExistingEdge() {
		setupStage1();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				assertTrue(graph.getVertex(i).existingEdge(graph.getVertex(j)));
			}
		}
	}
	
	public void testRemoveEdgeBetweenVertices() {
		setupStage1();
		int edgeId = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				graph.removeEdgeBetweenVertices(i, edgeId);
				edgeId += 1;
			}
		}
		assertTrue(graph.getNumberOfEdges() == 0);
	}
	
	public void testRemoveVertex() {
		setupStage1();
		
		for (int i = 0; i < 7; i++) {
			graph.removeVertex(i);
		}
		
		assertTrue(graph.getNumberOfVertices() == 0);
	}
	
	public void testAdjacencyMatrixWeight() {
		setupStage2();
		Integer[][] adjacencyMatrixWeight = graph.getAdjacencyMatrixWeight();
		
		for (int i = 0; i < adjacencyMatrixWeight.length; i++) {
			for (int j = 0; j < adjacencyMatrixWeight.length; j++) {
				assertTrue(adjacencyMatrixWeight[i][j] == matrix[i][j]);
			}
		}
	}

	public void testBFS() {
		setupStage3();
		Queue<Vertex<Integer,Integer,Integer,Integer>> queueBFS = graph.BFS(0);
		assertTrue(visited.size() == queueBFS.size());
		for (int i = 0; i < visited.size(); i++) {
			assertTrue(visited.poll().compareTo(queueBFS.poll()) == 0);
		}
	}
	
	public void testDFS() {
		setupStage4();
		Queue<Vertex<Integer,Integer,Integer,Integer>> queueDFS = graph.DFS(0);
		assertTrue(visited.size() == queueDFS.size());
		for (int i = 0; i < visited.size(); i++) {
			assertTrue(visited.poll().compareTo(queueDFS.poll()) == 0);
		}
	}
	
	public void testDijkstra() {
		setupStage5();
		Integer[] distance = {0,4,1,9,7};
		Integer[] distanceDijktra = graph.Dijkstra(0);
		for (int i = 0; i < distanceDijktra.length; i++) {
			assertTrue(distance[i] == distanceDijktra[i]);
		}
		
	}
	
	public void testFloydWarshall() {
		setupStage6();
		Integer[][] distance = {{0, -1,-2, 0},
								{4,  0, 2, 4},
								{5,  1, 0, 2},
								{3, -1, 1, 0}};
		Integer[][] distanceFloydWarshall = graph.FloydWarshall();
		
		for (int i = 0; i < distanceFloydWarshall.length; i++) {
			for (int j = 0; j < distanceFloydWarshall.length; j++) {
				assertTrue(distance[i][j] == distanceFloydWarshall[i][j]);
			}
		}
	}
	
	public void testPRIM() {
		setupStage3();
		Graph<Integer, Integer, Integer, Integer> graph = this.graph.PRIM(0);
		Integer[] dijktraPrePrim = this.graph.Dijkstra(0);
		Integer[] dijktraPostPrim = graph.Dijkstra(0);
		for (int i = 0; i < dijktraPrePrim.length; i++) {
			assertTrue(dijktraPrePrim[i] == dijktraPostPrim[i]);
		}
	}
	
	public void testKRUSKAL() {
		setupStage3();
		Graph<Integer, Integer, Integer, Integer> graph = this.graph.KRUSKAL();
		int cost = 0;
		Collection<Vertex<Integer,Integer,Integer,Integer>> vertices = graph.getVertices().values();
		for (Iterator<Vertex<Integer, Integer, Integer, Integer>> iterator = vertices.iterator(); iterator.hasNext();) {
			Vertex<Integer, Integer, Integer, Integer> vertex = iterator.next();
			Collection<Edge<Integer,Integer,Integer,Integer>> edges = vertex.getAdjacentEdges().values();
			
			for (Iterator<Edge<Integer, Integer, Integer, Integer>> iterator2 = edges.iterator(); iterator2.hasNext();) {
				Edge<Integer, Integer, Integer, Integer> edge = iterator2.next();
				cost+=edge.getWeight();
			}
			
		}
		
		assertTrue(cost == 28*2);
	}
}
