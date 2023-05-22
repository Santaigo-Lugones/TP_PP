package MarsRover;

public class North extends Direction {

    @Override
    public void moveForward(Position pos) {
        pos.advanceY();
    }

    @Override
    public void moveBack(Position pos) {
        pos.backY();
    }

    @Override
    public void rotateLeft(Position pos) {
        pos.setDir(new West());
    }

    @Override
    public void rotateRight(Position pos) {
        pos.setDir(new East());
    }

    @Override
    public String toString() {
        return "North";
    }

}
