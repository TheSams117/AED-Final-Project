package com.triadamcola.data_structures.graph.edge;

import com.triadamcola.data_structures.graph.vertex.Vertex;

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
public class Edge<I,ID extends Comparable<ID>,K, V extends Comparable<V>> implements Comparable<Edge<I,ID,K,V>> {
	/**
	 * Weight of edge
	 */
	private int weight;
	
	/**
	 * Identification of the edge
	 */
	private ID id;
	
	/**
	 * Edge information
	 */
	private I information;
	
	/**
	 * Vertex pointed by the edge
	 */
	private Vertex<I,ID, K, V> vertexTarget;
	
	/**
	 * Edge Builder
	 * @param weight The weight of the edge
	 * @param information Information about the edge
	 * @param vertexTarget Vertex that allows you to reach the edge
	 */
	public Edge(int weight,ID id, I information, Vertex<I,ID, K, V> vertexTarget) {
		this.weight = weight;
		this.id = id;
		this.information = information;
		this.vertexTarget = vertexTarget;
	}
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * @return the information
	 */
	public I getInformation() {
		return information;
	}
	
	/**
	 * @param information the information to set
	 */
	public void setInformation(I information) {
		this.information = information;
	}
	
	/**
	 * @return the vertexTarget
	 */
	public Vertex<I,ID, K, V> getVertexTarget() {
		return vertexTarget;
	}
	
	/**
	 * @param vertexTarget the vertexTarget to set
	 */
	public void setVertexTarget(Vertex<I,ID, K, V> vertexTarget) {
		this.vertexTarget = vertexTarget;
	}

	/**
	 * @return the id
	 */
	public ID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ID id) {
		this.id = id;
	}
	/**
	 * Compare the edge who arrives by parameter with the current one according to his weight
	 * @param edge Edge to compare
	 * @return Return a integer positive if current edge is greater, a integer negative if current edge is less or zero if both edges are equal.
	 */
	public int compareTo(Edge<I, ID, K, V> edge) {
		
		return weight - edge.weight;
	}
}
