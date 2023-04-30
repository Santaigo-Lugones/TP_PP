package stack;

public class OOStack {
	static public String stackEmptyErrorDescription = "Stack is empty";
	private Node lastIn;
	
	public OOStack() {
		lastIn = new EmptyNode();
	}
	public boolean isEmpty() {
		return lastIn.isEmpty();
	}

	public OOStack push(String string) {
		Node newNode = new NonemptyNode(string);
		newNode.setPrevious(lastIn);
		lastIn = newNode;
		return this;
	}

	public Object pop() {
		Object toReturn = lastIn.getElement();
		lastIn.tryPop();
		lastIn = lastIn.getPrevious();
		return toReturn;
	}

	public Object top() {
		lastIn.tryTop();
		return lastIn.getElement();
	}

	public int size() {
		return lastIn.getTraceSize();
	}

}
