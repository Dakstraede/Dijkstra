package dijkstra.graph;

public class Edge {
	
	/**
	 * 1 = ligne droite
	 * 2 = ligne diagonale (Si on gère les diagonales ?)
	 */
	private int weight;
	
	private Node source;
	private Node destination;
	
	public Edge(Node source, Node destination,int weight) {
		this.setSource(source);
		this.setDestination(destination);
		this.setWeight(weight);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}
	
}
