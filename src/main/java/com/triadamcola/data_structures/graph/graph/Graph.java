package com.triadamcola.data_structures.graph.graph;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.triadamcola.data_structures.graph.edge.Edge;
import com.triadamcola.data_structures.graph.graph.WayComparator;
import com.triadamcola.data_structures.graph.vertex.Vertex;

public class Graph<I, ID extends Comparable<ID>, K, V extends Comparable<V>> {
	public static final char DIRECTED_GRAPH = 'D';

	public static final char INDIRECTED_GRAPH = 'I';

	private Hashtable<K, Vertex<I, ID, K, V>> vertices;

	private Integer[][] adjacencyMatrixWeight;

	private int numberOfVertices;

	private int numberOfEdges;

	private char typeGraph;

	public Graph(char typeGraph) {
		this.typeGraph = typeGraph;
		vertices = new Hashtable<K, Vertex<I, ID, K, V>>();
		adjacencyMatrixWeight = new Integer[0][0];
		numberOfVertices = 0;
		numberOfEdges = 0;
	}

	public Vertex<I, ID, K, V> getVertex(K key) {
		return vertices.get(key);
	}

	public Edge<I, ID, K, V> getEdge(K keyVertex, ID idEdge) {
		return (getVertex(keyVertex) == null) ? null : getVertex(keyVertex).getEdge(idEdge);
	}

	public boolean existingEdge(K keyVertexSource, K keyVertexTarget) {
		return (getVertex(keyVertexSource) != null && getVertex(keyVertexSource) != null)
				? getVertex(keyVertexSource).existingEdge(getVertex(keyVertexSource))
				: false;
	}

	public boolean contains(Vertex<I, ID, K, V> vertex) {
		return vertices.contains(vertex);
	}

	public boolean containsKey(K key) {
		return vertices.containsKey(key);
	}

	public V getValue(K keyVertex) {
		return (getVertex(keyVertex) != null) ? null : getVertex(keyVertex).getValue();
	}

	public I getInformationEdge(K keyVertex, ID edgeId) {
		return (getEdge(keyVertex, edgeId) != null) ? null : getEdge(keyVertex, edgeId).getInformation();
	}

	public int getWeightEdge(K keyVertex, ID edgeId) {
		return (getEdge(keyVertex, edgeId) != null) ? 0 : getEdge(keyVertex, edgeId).getWeight();
	}

	@SuppressWarnings("unchecked")
	public void addVerticesCollection(Collection<Vertex<I, ID, K, V>> verticesCollection) {
		vertices = (Hashtable<K, Vertex<I, ID, K, V>>) verticesCollection;
	}

	public boolean isDirectedGraph() {
		return typeGraph == DIRECTED_GRAPH;
	}

	/**
	 * @return the vertices
	 */
	public Hashtable<K, Vertex<I, ID, K, V>> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(Hashtable<K, Vertex<I, ID, K, V>> vertices) {
		this.vertices = vertices;
	}

	/**
	 * @return the numberOfVertices
	 */
	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	/**
	 * @param numberOfVertices the numberOfVertices to set
	 */
	public void setNumberOfVertices(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
	}

	/**
	 * @return the numberOfEdges
	 */
	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	/**
	 * @param numberOfEdges the numberOfEdges to set
	 */
	public void setNumberOfEdges(int numberOfEdges) {
		this.numberOfEdges = numberOfEdges;
	}

	/**
	 * @return the typeGraph
	 */
	public char getTypeGraph() {
		return typeGraph;
	}

	/**
	 * @param typeGraph the typeGraph to set
	 */
	public void setTypeGraph(char typeGraph) {
		this.typeGraph = typeGraph;
	}

	/**
	 * @return the adjacencyMatrixWeight
	 */
	public Integer[][] getAdjacencyMatrixWeight() {
		return adjacencyMatrixWeight;
	}

	/**
	 * @param adjacencyMatrixWeight the adjacencyMatrixWeight to set
	 */
	public void setAdjacencyMatrixWeight(Integer[][] adjacencyMatrixWeight) {
		this.adjacencyMatrixWeight = adjacencyMatrixWeight;
	}

	public boolean add(K key, V value) {
		Vertex<I, ID, K, V> vertex = new Vertex<I, ID, K, V>(key, value);
		boolean add = !containsKey(key) && !contains(vertex);
		if (add) {
			vertex.setPosition(numberOfVertices);
			vertices.put(key, vertex);
			numberOfVertices += 1;
			Integer[][] newMatrix = new Integer[numberOfVertices][numberOfVertices];

			for (int i = 0; i < adjacencyMatrixWeight.length; i++) {
				for (int j = 0; j < adjacencyMatrixWeight.length; j++) {
					newMatrix[i][j] = adjacencyMatrixWeight[i][j];
				}
			}

			adjacencyMatrixWeight = newMatrix;
		}
		return add;

	}

	public void addEdgeBetweenVertices(K keyVertexSource, K keyVertexTarget, int edgeWeight, I edgeInformation,
			ID edgeId) {
		Vertex<I, ID, K, V> vertexSource = getVertex(keyVertexSource);
		Vertex<I, ID, K, V> vertexTarget = getVertex(keyVertexTarget);
		if (vertexSource != null && vertexTarget != null) {

			addEdgeBetweenVertices(vertexSource, vertexTarget, edgeWeight, edgeInformation, edgeId);
		}

	}

	public void addEdgeBetweenVertices(Vertex<I, ID, K, V> vertexSource, Vertex<I, ID, K, V> vertexTarget,
			int edgeWeight, I edgeInformation, ID edgeId) {

		if (typeGraph == DIRECTED_GRAPH) {
			adjacencyMatrixWeight[vertexSource.getPosition()][vertexTarget.getPosition()] = edgeWeight;
			vertexSource.addAdjacentVertex(edgeWeight, edgeId, edgeInformation, vertexTarget);
			numberOfEdges += 1;
		} else {
			adjacencyMatrixWeight[vertexSource.getPosition()][vertexTarget.getPosition()] = edgeWeight;
			adjacencyMatrixWeight[vertexTarget.getPosition()][vertexSource.getPosition()] = edgeWeight;
			vertexSource.addAdjacentVertex(edgeWeight, edgeId, edgeInformation, vertexTarget);
			vertexTarget.addAdjacentVertex(edgeWeight, edgeId, edgeInformation, vertexSource);
			numberOfEdges += 1;
		}
	}

	public void removeEdgeBetweenVertices(K keyVertexSource, ID edgeId) {
		Vertex<I, ID, K, V> vertexSource = getVertex(keyVertexSource);

		if (vertexSource != null && getEdge(keyVertexSource, edgeId) != null) {
			removeEdgeBetweenVertices(vertexSource, edgeId);
		}
	}

	public void removeEdgeBetweenVertices(Vertex<I, ID, K, V> vertexSource, ID edgeId) {
		if (typeGraph == DIRECTED_GRAPH) {
			Vertex<I, ID, K, V> vertexTarget = vertexSource.getEdge(edgeId).getVertexTarget();
			adjacencyMatrixWeight[vertexSource.getPosition()][vertexTarget.getPosition()] = null;
			vertexSource.deleteEdgeBetweenVertex(edgeId);

			numberOfEdges -= 1;
		} else {
			Vertex<I, ID, K, V> vertexTarget = vertexSource.getEdge(edgeId).getVertexTarget();
			adjacencyMatrixWeight[vertexSource.getPosition()][vertexTarget.getPosition()] = null;
			adjacencyMatrixWeight[vertexTarget.getPosition()][vertexSource.getPosition()] = null;
			vertexSource.deleteEdgeBetweenVertex(edgeId);
			vertexTarget.deleteEdgeBetweenVertex(edgeId);
			numberOfEdges -= 1;

		}
	}

	public Vertex<I, ID, K, V> removeVertex(K key) {
		Vertex<I, ID, K, V> vertex = vertices.remove(key);
		vertex.setAdjacentEdges(null);
		int numberI = vertex.getPosition();
		if (vertex != null) {
			Collection<Vertex<I, ID, K, V>> collection = vertices.values();

			for (Iterator<Vertex<I, ID, K, V>> iterator = collection.iterator(); iterator.hasNext();) {
				Vertex<I, ID, K, V> vertexItera = iterator.next();
				numberOfEdges -= vertexItera.deleteEdgeBetweenVertex(vertex);
				if (numberI < vertexItera.getPosition()) {
					vertexItera.setPosition(vertexItera.getPosition() - 1);
				}

			}
			numberOfVertices -= 1;
		}
		generateMatrix();
		return vertex;
	}

	public void generateMatrix() {
		adjacencyMatrixWeight = new Integer[numberOfVertices][numberOfVertices];
		Collection<Vertex<I, ID, K, V>> collection = vertices.values();
		for (Iterator<Vertex<I, ID, K, V>> iterator = collection.iterator(); iterator.hasNext();) {
			Vertex<I, ID, K, V> vertex1 = iterator.next();
			Collection<Edge<I, ID, K, V>> collection2 = vertex1.getAdjacentEdges().values();
			for (Iterator<Edge<I, ID, K, V>> iterator2 = collection2.iterator(); iterator2.hasNext();) {
				Edge<I, ID, K, V> edge = iterator2.next();
				adjacencyMatrixWeight[vertex1.getPosition()][edge.getVertexTarget().getPosition()] = edge.getWeight();
			}
		}

	}

	public Queue<Vertex<I, ID, K, V>> BFS(K keySourceVertex) {
		Queue<Vertex<I, ID, K, V>> adjacencyQueue = new LinkedList<Vertex<I, ID, K, V>>();
		Queue<Vertex<I, ID, K, V>> visitedQueue = new LinkedList<Vertex<I, ID, K, V>>();
		boolean[] visited = new boolean[numberOfVertices];
		Vertex<I, ID, K, V> vertex = getVertex(keySourceVertex);
		adjacencyQueue.add(vertex);
		visitedQueue.add(vertex);
		visited[vertex.getPosition()] = true;
		while (!adjacencyQueue.isEmpty()) {
			vertex = adjacencyQueue.remove();
			visitedQueue.add(vertex);
			vertex.enqueueAdjacencies(adjacencyQueue, visited);
		}

		return visitedQueue;

	}

	public Queue<Vertex<I, ID, K, V>> DFS(K keySourceVertex) {
		Stack<Vertex<I, ID, K, V>> adjacencystack = new Stack<Vertex<I, ID, K, V>>();
		Queue<Vertex<I, ID, K, V>> visitedQueue = new LinkedList<Vertex<I, ID, K, V>>();
		boolean[] visited = new boolean[numberOfVertices];
		Vertex<I, ID, K, V> vertex = getVertex(keySourceVertex);
		adjacencystack.push(vertex);
		visitedQueue.add(vertex);
		visited[vertex.getPosition()] = true;
		while (!adjacencystack.isEmpty()) {
			vertex = adjacencystack.pop();
			visitedQueue.add(vertex);
			vertex.pushAdjacencies(adjacencystack, visited);

		}

		return visitedQueue;

	}

	public Integer[] Dijkstra(K keySourceVertex) {
		Integer[] distance = new Integer[numberOfVertices];
		boolean[] visited = new boolean[numberOfVertices];
		Vertex<I, ID, K, V> vertex = getVertex(keySourceVertex);

		PriorityQueue<WayComparator<I, ID, K, V>> pq = new PriorityQueue<WayComparator<I, ID, K, V>>();
		pq.add(new WayComparator<I, ID, K, V>(0, vertex, null, null));
		distance[vertex.getPosition()] = 0;
		visited[vertex.getPosition()] = true;

		while (!pq.isEmpty()) {
			vertex = pq.poll().getVertexTarget();
			vertex.relaxEdge(pq, distance, visited);

		}

		return distance;

	}

	public Integer[][] floydWarshall() {
		Integer[][] matrix = adjacencyMatrixWeight.clone();
		for (int k = 0; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {

					Integer a = matrix[i][j];
					Integer b = matrix[i][k];
					Integer c = matrix[k][j];

					if (a != null && b != null && c != null) {
						matrix[i][j] = (a < b + c) ? a : b + c;
					} else if (a == null && b != null && c != null) {
						matrix[i][j] = b + c;
					}

				}
			}
		}

		return matrix;

	}

	public Graph<I, ID, K, V> startNewGraph() {
		Graph<I, ID, K, V> graph = new Graph<I, ID, K, V>(typeGraph);
		Collection<Vertex<I, ID, K, V>> vertices = this.vertices.values();
		for (Iterator<Vertex<I, ID, K, V>> iterator = vertices.iterator(); iterator.hasNext();) {
			Vertex<I, ID, K, V> vertex = iterator.next();
			graph.add(vertex.getKey(), vertex.getValue());

		}
		return graph;

	}

	public Graph<I, ID, K, V> PRIM(K keyVertex) {
		Graph<I, ID, K, V> graph = startNewGraph();
		boolean[] visited = new boolean[numberOfVertices];
		Vertex<I, ID, K, V> vertex = getVertex(keyVertex);
		WayComparator<I, ID, K, V> wC;
		PriorityQueue<WayComparator<I, ID, K, V>> pq = new PriorityQueue<WayComparator<I, ID, K, V>>();

		while (graph.numberOfEdges != numberOfVertices - 1) {
			visited[vertex.getPosition()] = true;
			vertex.enqueueAdjacenciesPq(pq, visited);

			wC = pq.remove();
			if (!visited[wC.getVertexTarget().getPosition()]) {
				graph.addEdgeBetweenVertices(wC.getKeySourceVertex(), wC.getVertexTarget().getKey(), wC.getWeight(),
											 vertices.get(wC.getKeySourceVertex()).getEdge(wC.getEdgeId()).getInformation(), wC.getEdgeId());
			}

			vertex = wC.getVertexTarget();
		}

		return graph;
	}

	public Graph<I, ID, K, V> KRUSKAL() {
		Graph<I, ID, K, V> graph = new Graph<I, ID, K, V>(typeGraph);

		return graph;
	}
}
