package dijkstra.queue;

public class Link<V> {
	
	private V value = null;
	private Link<V> next = null;
	
	public Link() {}
	
	public Link(V value) {
		this.value = value;
	}
	
	public Link<V> getNext() {
		return next;
	}

	public V getValue() {
		return value;
	}

	public void setNext(Link<V> next) {
		this.next = next;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
