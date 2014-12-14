package dijkstra.graph;

public class Souris {
	
	private Node position = null;
	
	private int id;
	
	public Souris(int id) {
		this.id = id;
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
		if (position != null) {
			this.position.setOccupation(false);
		}
		this.position = next;
		this.position.setOccupation(true);
	}
}
