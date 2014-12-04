package dijkstra.graph;

public class Edge {
	
	/**
	 * 1 = ligne droite
	 * 2 = ligne diagonale (Si on gère les diagonales ?)
	 */
	private int weight;
	
	private int firstNode;
	private int secondNode;
	
	public Edge(int sourceIndex, int destinationIndex,int weight) {
		this.firstNode = sourceIndex;
		this.secondNode = destinationIndex;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getFirstNode() {
		return this.firstNode;
	}

	public int getSecondNode() {
		return secondNode;
	}


}
