package MarsRover;

public class East extends Direction {
    @Override
    public void moveForward(Position pos) {
        pos.advanceX();
    }

    @Override
    public void moveBack(Position pos) {
        pos.backX();
    }

    @Override
    public void rotateLeft(Position pos) {
        pos.setDir(new North());
    }

    @Override
    public void rotateRight(Position pos) {
        pos.setDir(new South());
    }

    @Override
    public String toString() {
        return "East";
    }
}