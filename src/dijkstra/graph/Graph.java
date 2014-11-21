package dijkstra.graph;

import dijkstra.model.content.Content;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 06/11/2014.
 */
public class Graph {

	private int height, width;

	private HashMap<Integer, Content> nodes;
    private HashMap<Integer, Content> doors;
    private HashMap<Integer, Content> exits;
	private HashMap<Integer, Content> walls;
	
	private List<Edge> edges;
	private List<Node> vertexes;

    public Graph(int width, int height) {
		super();
		nodes = new HashMap<>();
		doors = new HashMap<>();
		exits = new HashMap<>();
		walls = new HashMap<>();
		this.height = height;
		this.width = width;
	}


    public void addNode(Content node, int key){
		this.nodes.put(key, node);
	}

	public void addDoor(Content node, int key){
		this.doors.put(key, node);
	}

	public void addExit(Content node, int key){
		this.exits.put(key, node);
	}

	public void addWall(Content node, int key){
		this.walls.put(key, node);
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public int getNumberOfGrounds(){
		return this.nodes.size();
	}

	public int getNumberOfDoors(){
		return this.doors.size();
	}

	public int getNumberOfExits(){
		return this.exits.size();
	}

	public int getNumberOfWalls(){
		return this.walls.size();
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

