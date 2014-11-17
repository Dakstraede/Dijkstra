package dijkstra.graph;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 06/11/2014.
 */
public class Graph {
	
	HashMap<Integer, Node> nodes;
    HashMap<Integer, Node> doors;
    HashMap<Integer, Node> exits;
	
	private List<Edge> edges;
	private List<Node> vertexes;

    public Graph(List<Edge> edges, List<Node> vertexes) {
		super();
		this.setEdges(edges);
		this.setVertexes(vertexes);
	}
    
	public List<Edge> getEdges() {
		return edges;
	}
	
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public List<Node> getVertexes() {
		return vertexes;
	}
	
	public void setVertexes(List<Node> vertexes) {
		this.vertexes = vertexes;
	}
}

