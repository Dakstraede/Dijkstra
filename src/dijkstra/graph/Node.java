package dijkstra.graph;


/**
 * Created by Alex on 05/11/2014.
 */
public class Node {
	
	public static final String GROUND = "GROUND";
	public static final String GRASS = "GRASS";
	public static final String WALL = "WALL";
	public static final String DOOR = "DOOR";
	public static final String CHEESE = "CHEESE";

	private int id;
	private int coordX, coordY;
	private String type = null;
	private boolean isOccuped = false;

	public Node(int id, String type) {
		super();
		this.id = id;
		this.type = type;
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
	
}
