package stack;

public class EmptyNode extends Node {
	
	@Override
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public int getTraceSize() {
		return 0;
	}
	
	@Override
	public void tryPop() {
		stackError();
	}
	
	@Override
	public void tryTop() {
		stackError();
	}
	
	private void stackError() {
		throw new Error(OOStack.stackEmptyErrorDescription);
	}
}
