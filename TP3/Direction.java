package MarsRover;

public abstract class Direction {
	
    public abstract void moveForward(Position pos);
    public abstract void moveBack(Position pos);
    public abstract void rotateLeft(Position pos);
    public abstract void rotateRight(Position pos);
    public abstract String toString();
    
    @Override 
    public boolean equals(Object obj) {
    	return obj != null && 
                ( this == obj  || 
                ( getClass() == obj.getClass() && 
                ((Direction)obj).toString().equals(toString())));
    }
    
}
