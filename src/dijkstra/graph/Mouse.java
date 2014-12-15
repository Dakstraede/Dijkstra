package dijkstra.graph;

public class Mouse {
	
	private Node position = null;
	
	private int id;
	
	private int steps = 0;
	
	private int door = 0;
	
	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public Mouse(int id, int door) {
		this.id = id;
		this.door = door;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Souris [position=" + ((position == null) ? null : position.getId()) + ", id=" + id + "]";
	}

	public Node getPosition() {
		return position;
	}
	
	public void move(Node next) {
		if (next.isOccuped() == false) {
			if (position != null) position.setOccupation(false);
			position = next;
			steps++;
			if (! position.type.equals(Node.CHEESE)) position.setOccupation(true);
		}
	}
}
