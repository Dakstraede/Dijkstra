package dijkstra.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphPath implements Comparable<GraphPath> {
	private double minDistance = 0;
	private List<Node> path = new ArrayList<>();
	
	public GraphPath(double weigth, List<Node> path) {
		super();
		this.minDistance = weigth;
		this.path = path;
	}
	
	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public List<Node> getPath() {
		return path;
	}
	public void setPath(List<Node> path) {
		this.path = path;
	}

	@Override
	public int compareTo(GraphPath o) {
		return Double.compare(minDistance, o.minDistance);
	}

	@Override
	public String toString() {
		return "GraphPath [minDistance=" + minDistance + ", path.size=" + path.size() + "]";
	}
	
	
}
