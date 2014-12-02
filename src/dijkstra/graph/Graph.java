package dijkstra.graph;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 06/11/2014.
 */
public class Graph {

	private int height, width;

	private HashMap<Integer, Node> grounds;
	private HashMap<Integer, Node> grass;
    private HashMap<Integer, Node> doors;
    private HashMap<Integer, Node> exits;
	private HashMap<Integer, Node> walls;
	
	private List<Edge> edges;

    public Graph(int width, int height) {
		super();
		grounds = new HashMap<>();
		grass = new HashMap<>();
		doors = new HashMap<>();
		exits = new HashMap<>();
		walls = new HashMap<>();
		this.height = height;
		this.width = width;
	}


    public void addGround(Node node, int key){
		this.grounds.put(key, node);
	}

	public void addDoor(Node node, int key){
		this.doors.put(key, node);
	}

	public void addExit(Node node, int key){
		this.exits.put(key, node);
	}

	public void addWall(Node node, int key){
		this.walls.put(key, node);
	}

	public void addGrass(Node node, int key){
		this.grass.put(key, node);
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public int getNumberOfGrounds(){
		return this.grounds.size();
	}

	public int getNumberOfGrass(){
		return this.grass.size();
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

}

