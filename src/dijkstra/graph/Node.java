package dijkstra.graph;


/**
 * Created by Alex on 05/11/2014.
 */
public class Node {
	
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
	
	public Node(int id, String type) {
		super();
		this.id = id;
		this.type = type;
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
		return "Node [id=" + id + ", coordX=" + coordX + ", coordY=" + coordY
				+ ", type=" + type + ", isOccuped=" + isOccuped + "]";
	}
	
}
