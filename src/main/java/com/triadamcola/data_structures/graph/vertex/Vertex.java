package com.triadamcola.data_structures.graph.vertex;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import com.triadamcola.data_structures.graph.edge.Edge;
import com.triadamcola.data_structures.graph.graph.WayComparator;

/**
 * @author Sergio Andrés Lozada Sánchez
 * @author Iván Camilo Goez Palacio
 * @author Cristhian Eduardo Castillo Meneses
 * 
 * @param <I> Information of the edge
 * @param <ID> Identification of edge
 * @param <KV> Identification of vertex
 * @param <V> The value contained in the vertex
 */
public class Vertex<I, ID extends Comparable<ID>, K, V extends Comparable<V>>
		implements Comparable<Vertex<I, ID, K, V>> {
	/**
	 * Integer that represent the position of the this vertex
	 */
	private int position;

	/**
	 * Value saved at the vertex
	 */
	private V value;

	/**
	 * Identification key of the vertex
	 */
	private K key;

	/**
	 * List of edges that point to the vertices adjacent to the current vertex
	 */
	private Hashtable<ID, Edge<I, ID, K, V>> adjacentEdges;

	/**
	 * Vertex Builder
	 * @param value Value to save in the vertex
	 * @param key   Identification key of the vertex
	 */
	public Vertex(K key, V value) {
		position = 0;
		this.value = value;
		this.key = key;
		this.adjacentEdges = new Hashtable<ID, Edge<I, ID, K, V>>();

	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	public Edge<I, ID, K, V> getEdge(ID edgeId){
		return adjacentEdges.get(edgeId);
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the adjacentEdges
	 */
	public Hashtable<ID, Edge<I, ID, K, V>> getAdjacentEdges() {
		return adjacentEdges;
	}

	/**
	 * @param adjacentEdges the adjacentEdges to set
	 */
	public void setAdjacentEdges(Hashtable<ID, Edge<I, ID, K, V>> adjacentEdges) {
		this.adjacentEdges = adjacentEdges;
	}

	/**
	 * Create an edge that relates the current vertex to the one that arrives by
	 * parameter post: A new one is added to the list of edges
	 * 
	 * @param weight      Integer that represents the weight of the relationship
	 *                    between the vertices
	 * @param id          Object that identifies the edge
	 * @param information Object that contains additional information on the
	 *                    relationship between vertices
	 * @param newVertex   The new vertex to relate to the current
	 */
	public void addAdjacentVertex(int weight, ID id, I information, Vertex<I, ID, K, V> newVertex) {
		adjacentEdges.put(id,new Edge<I, ID, K, V>(weight, id, information, newVertex));
	}

	/**
	 * Find the first edge that relates to the current node with the one that arrives by
	 * parameter
	 * 
	 * @param vertexToSearch The vertex that is sought
	 * @return The first edge relates the current vertex and the search, if it exists, in
	 *         the opposite case it returns null
	 */
	public Edge<I,ID, K, V> getEdgeBetweenVertices(Vertex<I, ID, K, V> vertexToSearch) {
		Edge<I,ID, K, V> edge = null;
		Collection<Edge<I,ID, K, V>> collection = adjacentEdges.values();
		for (Iterator<Edge<I, ID, K, V>> iterator = collection.iterator(); iterator.hasNext() && edge == null;) {
			Edge<I,ID, K, V> edgeItera = iterator.next(); 
			edge = (edgeItera.getVertexTarget().compareTo(vertexToSearch) == 0) ? edgeItera	: null;
			
		}
		
		return edge;
	}

	/**
	 * 
	 * @param vertexToSearch
	 * @return
	 */
	public Edge<I, ID, K, V> deleteEdgeBetweenVertex(ID edgeId) {
		return adjacentEdges.remove(edgeId);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteEdgeBetweenVertex(Vertex<I,ID,K,V> vertex) {
		Collection<Edge<I,ID,K,V>> collection = ((Hashtable<K, Edge<I, ID, K, V>>) adjacentEdges.clone()).values();
		int edgesDeleted = 0;
		for (Iterator<Edge<I,ID,K,V>> iterator = collection.iterator(); iterator.hasNext();) {
			Edge<I,ID,K,V> edge = iterator.next();
			if(edge.getVertexTarget().compareTo(vertex) == 0) {
				adjacentEdges.remove(edge.getId());
				collection = adjacentEdges.values();
				edgesDeleted+=1;
			}
			
		}
		return edgesDeleted;
	}
	/**
	 * 
	 * @param vertexToSearch
	 * @return
	 */
	public boolean existingEdge(Vertex<I, ID, K, V> vertexToSearch) {
		return (getEdgeBetweenVertices(vertexToSearch) == null) ? false : true;
	}
	/**
	 * 
	 * @param stack
	 */
	public void enqueueAdjacencies(Queue<Vertex<I,ID,K,V>> queue, boolean[] visited) {
		Collection<Edge<I,ID,K,V>> edges = adjacentEdges.values();
		for (Iterator<Edge<I, ID, K, V>> iterator = edges.iterator(); iterator.hasNext();) {
			Vertex<I, ID, K, V> vertex = iterator.next().getVertexTarget();
			if(!visited[vertex.getPosition()]) {
				queue.add(vertex);
				
				visited[vertex.getPosition()] = true;
			}
			
			
		}
	}
	/**
	 * 
	 * @param stack
	 */
	public void pushAdjacencies(Stack<Vertex<I,ID,K,V>> stack, boolean[] visited) {
		Collection<Edge<I,ID,K,V>> edges = adjacentEdges.values();
		for (Iterator<Edge<I, ID, K, V>> iterator = edges.iterator(); iterator.hasNext();) {
			Vertex<I, ID, K, V> vertex = iterator.next().getVertexTarget();
			if(!visited[vertex.getPosition()]) {
				stack.push(vertex);
				
				visited[vertex.getPosition()] = true;
			}
		}
		
	}
	/**
	 * 
	 * @param pq
	 * @param distance
	 * @param visited
	 */
	public void enqueueAdjacenciesPq(PriorityQueue<WayComparator<I, ID, K, V>> pq, boolean[] visited) {
		Collection<Edge<I,ID,K,V>> edges = adjacentEdges.values();
		for (Iterator<Edge<I, ID, K, V>> iterator = edges.iterator(); iterator.hasNext();) {
			Edge<I, ID, K, V> edge = iterator.next();
			Vertex<I, ID, K, V> vertex =  edge.getVertexTarget();
			if(!visited[vertex.getPosition()]) {
		
				pq.add(new WayComparator<I, ID, K, V>(edge.getWeight(), vertex,key,edge.getId()));
				
			}
		}
	}
	public void relaxEdge(PriorityQueue<WayComparator<I, ID, K, V>> pq,Integer[] distance, boolean[] visited) {
		Collection<Edge<I,ID,K,V>> edges = adjacentEdges.values();
		for (Iterator<Edge<I, ID, K, V>> iterator = edges.iterator(); iterator.hasNext();) {
			Edge<I, ID, K, V> edge = iterator.next();
			Vertex<I, ID, K, V> vertex =  edge.getVertexTarget();
			if(!visited[vertex.getPosition()]) {
				if(distance[vertex.getPosition()] == null) {
					distance[vertex.getPosition()] = distance[position]+edge.getWeight();
				}else if(distance[vertex.getPosition()]>distance[position]+edge.getWeight()){
					distance[vertex.getPosition()] = distance[position]+edge.getWeight();
				}
				pq.add(new WayComparator<I, ID, K, V>(edge.getWeight(), vertex,null,null));
				
				visited[vertex.getPosition()] = true;
			}
		}
	}
	/**
	 * Compare the vertex who arrives by parameter with the current one according to his value
	 * @param vertex Vertex to compare
	 * @return Return a integer positive if current vertex is greater, a integer negative if current vertex is less or zero if both vertex are equal.
	 */
	public int compareTo(Vertex<I, ID, K, V> vertex) {
		return value.compareTo(vertex.getValue());
	}

}
