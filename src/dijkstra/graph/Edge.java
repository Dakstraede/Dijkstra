package dijkstra.graph;

public class Edge {
	
	public Edge(int sourceIndex, int destinationIndex,int weight) {
		this.firstNode = sourceIndex;
		this.secondNode = destinationIndex;
		this.weight = weight;
	}
	
	/**
	 * 1 = ligne droite
	 * 2 = ligne diagonale (Si on gère les diagonales ?)
	 */
	private double weight;
	private Node other = null;
	
	
	public Edge(Node other) {
		this.other = other;
	}
	
	public Node getOther() {
		return this.other;
	}

	public double getWeight() {
		return weight;
	}
	
	private int firstNode;
	private int secondNode;
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getFirstNode() {
		return this.firstNode;
	}

	public int getSecondNode() {
		return secondNode;
	}

	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", nodeId=" + other.getId() + "]";
	}

}
