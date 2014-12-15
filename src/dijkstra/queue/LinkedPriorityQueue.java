package dijkstra.queue;

public class LinkedPriorityQueue<V> {
	
	private Link<V> list;
	
	public void add(V value) {
		Link<V> element = new Link<V>();
		element.setValue(value);
		
		if (list == null) {
			list = element;
		} else {
			Link<V> pivot = list;
			while(pivot != null) {
				
				
				pivot.setNext(pivot.getNext());
			}
		}
	}
	
	public boolean remove(Link<V> element) {
		if (list != null) {
			Link<V> pivot = list;
			while(pivot != null) {
				if (pivot == element) {
					
				}
				pivot.setNext(pivot.getNext());
			}
			return true;
		} else {
			return true;
		}
	}
	
	public V peek() {
		return list.getValue();
	}
	
	public V poll() {
		Link<V> element = list;
		V value = element.getValue();
		remove(element);
		return value;
	}
	
	public boolean isEmpty() {
		return list == null;
	}
}
