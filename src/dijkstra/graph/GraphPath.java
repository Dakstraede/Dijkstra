package dijkstra.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphPath {
	private double weigth = 0;
	private List<Node> path = new ArrayList<>();
	
	public GraphPath(double weigth, List<Node> path) {
		super();
		this.weigth = weigth;
		this.path = path;
	}
	
	public double getWeigth() {
		return weigth;
	}
	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}
	public List<Node> getPath() {
		return path;
	}
	public void setPath(List<Node> path) {
		this.path = path;
	}
}
