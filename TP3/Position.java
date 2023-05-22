package MarsRover;

import java.util.Objects;

public class Position {

	private int x, y;
    private Direction dir;

    public Position(Direction dir, int x, int y) {
    	this.x = x;
    	this.y = y;
        this.dir = dir;
    }

    public void advanceY() {
    	y += 1;
    }

    public void advanceX() {
    	x += 1;
    }

    public void backY() {
    	y -= 1;
    }

    public void backX() {
    	x -= 1;
    }

    public void moveForward() {
        dir.moveForward(this);
    }

    public void moveBack() {
        dir.moveBack(this);
    }

    public void rotateLeft() {
        dir.rotateLeft(this);
    }

    public void rotateRight() {
        dir.rotateRight(this);
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
    
    public int getY() {
    	return y;
    }
    
    public int getX() {
    	return x;
    }
    
    public Direction getDirection() {
    	return dir;
    }
    
    @Override
    public boolean equals( Object obj ) {
        return obj != null && 
              ( this == obj  || 
               ( getClass() == obj.getClass() && 
               ((Position)obj).getX() == x  && 
               ((Position)obj).getY()  == y &&
               ((Position)obj).getDirection().equals(dir)));
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(x,y);
    }

}