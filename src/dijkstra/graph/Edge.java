package dijkstra.graph;

public class Edge {

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
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", nodeId=" + other.getId() + "]";
	}

}
