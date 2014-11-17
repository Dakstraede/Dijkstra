package dijkstra.graph;


/**
 * Created by Alex on 05/11/2014.
 */
public class Node {
	
	public static final String GROUND = "GROUND";
	public static final String GRASS = "GRASS";
	public static final String WALL = "WALL";
	public static final String DOOR = "DOOR";
	public static final String SLOT_EMPTY = "SLOT_EMPTY";
	public static final String SLOT_CHEESE = "SLOT_CHEESE";
	public static final String SLOT_SANDSHREW = "SLOT_SANDSHREW";
	
	private int id;
	private String type = null;
	private String slot = SLOT_EMPTY;
	
    Node[] sides;
    int key;
    boolean isOccuped;
    
	public Node(int id, String type, String slot) {
		super();
		setId(id);
		setType(type);
		setSlot(slot);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	public boolean isOccuped() {
		if (slot.equals(SLOT_EMPTY)) {
			return false;
		} else {
			return true;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
