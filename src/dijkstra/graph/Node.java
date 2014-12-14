package dijkstra.graph;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	
	public static final String GROUND = "ground";
	public static final String GRASS = "grass";
	public static final String WALL = "wall";
	public static final String DOOR = "door";
	public static final String CHEESE = "cheese";
	public static final String SANDSHREW = "sandshrew";

	private int id;
	private int coordX, coordY;
	public String type = null;
	private boolean isOccuped = false;
	public Mouse souris = null;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Node previous = null;
	
	/**
	 * Liste de edges
	 */
	private ArrayList<Edge> edges = new ArrayList<>();
	
	public Node(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	/**
	 * Permet de rajouter un edge, verifie si il n'est pas deja present
	 * @param other
	 */
	public void addEdge(Node other, int weight) {
		Edge edge = new Edge(other); // On crée un edge
		boolean isExist = false; // Sert a savoir si le neud existe deja dans la liste
		for (Edge e : edges) {
			if (e.getOther().equals(other)) {
				isExist = true;
			}
		}
		// Si le neud n'est pas dans la liste on l'ajoute
		if(isExist == false) {
			edge.setWeight(weight);
			edges.add(edge);
		}
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public Node(int id, String type, int coordX, int coordY) {
		super();
		this.id = id;
		this.type = type;
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOccupation(boolean occupation){
		this.isOccuped = occupation;
	}

	public boolean isOccuped() {
		return this.isOccuped;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Node [" + id);
		//str.append(", xy=" + coordX + "," + coordY);
		//str.append(", " + type);
		//str.append(", ocup=" + isOccuped);
		str.append(", min=" + minDistance);
		str.append(", prev=" + ((previous != null)? previous.getId() : "null") );
		//str.append(", edges={\n");
		//for (Edge edge : edges) {
			//str.append("\t\t" + edge.toString() + "\n");
		//}
		//str.append("}");
		
		str.append("]");
		return str.toString();
	}
	
	@Override
	public int compareTo(Node o) {
		return Double.compare(minDistance, o.minDistance);
	}
	
}
