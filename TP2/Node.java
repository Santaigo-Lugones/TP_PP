package stack;

public abstract class Node {
	private String element;
	private Node previous;
	
	public abstract boolean isEmpty();
	
	public abstract int getTraceSize();
	
	public void tryPop() {}
	
	public void tryTop() {}
	
	public String getElement() {
		return element;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	public void setElement(String elem) {
		element = elem;
	}
	
	public void setPrevious(Node prev) {
		previous = prev;
	}
}
