package stack;

public class NonemptyNode extends Node {
	
	public NonemptyNode(String elem) {
		setElement(elem);
		setPrevious(new EmptyNode());
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public int getTraceSize() {
		return getPrevious().getTraceSize() + 1;
	}
}
