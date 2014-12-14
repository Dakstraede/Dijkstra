package dijkstra.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

	private int height, width;
	private ArrayList<Node> nodes = new ArrayList<Node>();

    public Graph(int width, int height) {
		this.height = height;
		this.width = width;
	}
    
    /**
     * Ajoute un node au graph
     * @param node
     */
    public void registerNode(Node node) {
    	this.nodes.add(node);
    }
    
    /**
     * Enleve un node au graph
     * @param id
     */
	public void unregisterNode(int id) {
		// TODO inutile ?
	}
	
	public ArrayList<Node> getNodes() {
		return this.nodes;
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Graph [height=" + height + ", width=" + width + ", nodes.size=" + nodes.size() + " nodes{\n");
		for (Node node : nodes) {
			str.append("\t" + node + "\n");
		}
		str.append("}]");
		return str.toString();
	}
	
	/**
	 * Dijkstra
	 * @param source
	 */
	public void computePaths(Node source) {
		resetGraph();
		PriorityQueue<Node> nodeQueue = new PriorityQueue<>();
		nodeQueue.add(source);
		while (! nodeQueue.isEmpty()) {
			Node current = nodeQueue.poll();
			
			for (Edge edge: current.getEdges()) {
				Node next = edge.getOther();
				double weight = (next.isOccuped() == true) ? 3 :   edge.getWeight();
				double distance = (current.minDistance == Double.POSITIVE_INFINITY) ? weight : current.minDistance + weight;
				if (distance < next.minDistance && (!next.type.equals(Node.DOOR))) {
					nodeQueue.remove(next);
					next.minDistance = distance;
					next.previous = current;
					nodeQueue.add(next);
				}
			}
		}
		source.previous = null;
	}
	
	private void resetGraph() {
		for (Node node : nodes) {
			node.minDistance = Double.POSITIVE_INFINITY;
			node.previous = null;
		}
	}
	
	public GraphPath getShortestPathTo(Node target) {
        List<Node> path = new ArrayList<>();
        Node node = target;
        while (node != null) {
        	path.add(node);
        	node = node.previous;
        }
        GraphPath res = new GraphPath(path.get(0).minDistance, path);
        Collections.reverse(path);
        return res;
    }
	
	public List<Node> getDoor() {
		List<Node> doors = new ArrayList<>();
		for (Node node : nodes) {
			if (node.type.equals(Node.DOOR)) {
				doors.add(node);
			}
		}
		return doors;
	}
	
	public List<Node> getCheese() {
		List<Node> cheeses = new ArrayList<>();
		for (Node node : nodes) {
			if (node.type.equals(Node.CHEESE)) {
                cheeses.add(node);
			}
		}
		return cheeses;
	}
}

