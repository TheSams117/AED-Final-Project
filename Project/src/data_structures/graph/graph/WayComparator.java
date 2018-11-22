package graph;



import vertex.Vertex;

public class WayComparator<I,ID extends Comparable<ID>,K,V extends Comparable<V>> implements Comparable<WayComparator<I,ID,K,V>>{
	private Integer weight;
	private	K keySourceVertex;
	private ID edgeId;
	private Vertex<I,ID,K,V> vertexTarget;
	public WayComparator(Integer weight, Vertex<I,ID,K,V> vertexTarget,K keySourceVertex,ID edgeId ) {
		this.weight = weight;
		this.vertexTarget = vertexTarget;
		this.keySourceVertex = keySourceVertex;
		this.edgeId = edgeId;
	}
	
	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * @return the vertexTarget
	 */
	public Vertex<I, ID, K, V> getVertexTarget() {
		return vertexTarget;
	}

	/**
	 * @param vertexTarget the vertexTarget to set
	 */
	public void setVertexTarget(Vertex<I, ID, K, V> vertex) {
		this.vertexTarget = vertex;
	}
	
	/**
	 * @return the keySourceVertex
	 */
	public K getKeySourceVertex() {
		return keySourceVertex;
	}

	/**
	 * @param keySourceVertex the keySourceVertex to set
	 */
	public void setKeySourceVertex(K keySourceVertex) {
		this.keySourceVertex = keySourceVertex;
	}

	/**
	 * @return the edgeId
	 */
	public ID getEdgeId() {
		return edgeId;
	}

	/**
	 * @param edgeId the edgeId to set
	 */
	public void setEdgeId(ID edgeId) {
		this.edgeId = edgeId;
	}

	public int compareTo(WayComparator<I,ID,K,V> arg0) {
		
		return weight-arg0.weight;
	}

}
